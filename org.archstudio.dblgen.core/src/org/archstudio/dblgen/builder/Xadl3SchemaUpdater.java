package org.archstudio.dblgen.builder;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.archstudio.dblgen.Xadl3SchemaNature;
import org.archstudio.dblgen.builder.Xadl3SchemaLocation.UpdateFrequency;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/*
 * This class makes a non-standard use of Eclipse extension points. This class lives at the meta-Eclipse level, which
 * means it's part of a plug-in that's installed in the development copy of Eclipse. ArchStudio runs in the next
 * instance. So, here we want to look at some data defined in plug-in extension points. Normally, that data lives in
 * plug-ins installed in the current instance of Eclipse (i.e., that were developed one meta-level higher). But in this
 * case, we want to look at data that's stored in the plug-ins whose code is in the workspace.
 */

public class Xadl3SchemaUpdater {

	private static final Xadl3SchemaUpdater INSTANCE = new Xadl3SchemaUpdater();

	public static Xadl3SchemaUpdater getInstance() {
		return INSTANCE;
	}

	private final ResourceSet resourceSet;
	private final URIConverter uriConverter;

	private Xadl3SchemaUpdater() {
		resourceSet = new ResourceSetImpl();
		uriConverter = resourceSet.getURIConverter();
	}

	public boolean hasXadl3Nature(IProject project) throws CoreException {
		return project.getNature(Xadl3SchemaNature.NATURE_ID) != null;
	}

	public URI getSchemaURI(String urlString) {
		return URI.createURI(urlString);
	}

	public String getContents(InputStream is) throws IOException {
		try {
			return new String(SystemUtils.blt(is));
		}
		finally {
			SystemUtils.closeQuietly(is);
		}
	}

	public String getContents(URI uri) throws IOException {
		return getContents(uriConverter.createInputStream(uri));
	}

	public boolean schemaExists(IProject project, String schemaFileName) {
		IFolder modelFolder = project.getFolder("model");
		if (modelFolder.exists()) {
			IFile schemaFile = modelFolder.getFile(schemaFileName);
			if (schemaFile != null && schemaFile.exists()) {
				return true;
			}
		}
		return false;
	}

	public String getSchemaContents(IProject project, String schemaFileName) throws IOException, CoreException {
		IFolder modelFolder = project.getFolder("model");
		if (modelFolder.exists()) {
			IFile schemaFile = modelFolder.getFile(schemaFileName);
			if (schemaFile != null && schemaFile.exists()) {
				return getContents(schemaFile.getContents());
			}
		}
		throw new FileNotFoundException(schemaFileName);
	}

	/*
	 * Last-updated times for schemas are stored as persistent project properties. The qualified name has two parts: the
	 * qualifier is a distinguished URI and the local name is the filename of the schema. The property value is a
	 * stringified Long representing the last updated time for the schema.
	 */
	private final static String SCHEMA_LAST_UPDATE_TIME_URI = "http://www.archstudio.org/schemaLastUpdateTime/";

	public Map<String, Long> getSchemaLastUpdatedTimes(IProject project) {
		Map<String, Long> lastUpdatedTimes = new HashMap<String, Long>();

		try {
			for (Object element : project.getPersistentProperties().keySet()) {
				QualifiedName qn = (QualifiedName) element;
				String qualifier = qn.getQualifier();
				if (qualifier != null && qualifier.equals(SCHEMA_LAST_UPDATE_TIME_URI)) {
					String schemaName = qn.getLocalName();
					if (schemaName != null) {
						String lastUpdateTimeString = project.getPersistentProperty(qn);
						try {
							lastUpdatedTimes.put(schemaName, new Long(lastUpdateTimeString));
						}
						catch (NumberFormatException nfe) {
						}
					}
				}
			}
		}
		catch (CoreException ce) {
			ce.printStackTrace();
		}

		return lastUpdatedTimes;
	}

	public long getSchemaLastUpdateTime(IProject project, String schemaFileName) {
		QualifiedName qn = new QualifiedName(SCHEMA_LAST_UPDATE_TIME_URI, schemaFileName);
		try {
			return Long.valueOf(project.getPersistentProperty(qn));
		}
		catch (CoreException ce) {
			return -1;
		}
		catch (NumberFormatException nfe) {
			return -1;
		}
	}

	public void setSchemaLastUpdateTime(IProject project, String schemaFileName, long time) {
		QualifiedName qn = new QualifiedName(SCHEMA_LAST_UPDATE_TIME_URI, schemaFileName);
		try {
			project.setPersistentProperty(qn, Long.toString(time));
		}
		catch (CoreException ce) {
			// This is best-effort
			ce.printStackTrace();
		}
	}

	public void updateSchemasIfNecessary(IProject project) throws IOException, CoreException {
		for (Xadl3SchemaLocation schemaLocation : Xadl3SchemaLocation.parse(project)) {
			updateSchemaIfNecessary(project, schemaLocation);
		}
	}

	private boolean updateTimePassed(IProject project, long lastUpdateTime, Xadl3SchemaLocation schemaLocation)
			throws CoreException {
		if (schemaLocation.getAutoUpdateFrequency().equals(UpdateFrequency.NEVER)) {
			// however, if the src folder is missing or empty, then do a build
			IFolder srcFolder = project.getFolder("src");
			if (!srcFolder.exists() || srcFolder.members().length == 0) {
				return true;
			}
			return false;
		}
		else {
			if (lastUpdateTime == -1) {
				// We've never updated this schema before, let's do it.
				return true;
			}
			// See if the difference between the current time and the last
			// update time is greater than the update frequency.
			long currentTime = new java.util.Date().getTime();
			if (currentTime - lastUpdateTime > schemaLocation.getAutoUpdateFrequency().getNumMilliseconds()) {
				return true;
			}
			return false;
		}
	}

	private static final DateFormat df = new SimpleDateFormat("yyyy_MM_dd_HHmmss");

	private String getTimestampForBackupFile() {
		return df.format(new java.util.Date());
	}

	private boolean writeSchema(IProject project, String schemaFileName, String newContents, boolean backupOldFile) {
		try {
			IFolder modelFolder = project.getFolder("model");
			if (!modelFolder.exists()) {
				modelFolder.create(true, true, null);
			}
			if (modelFolder.exists()) {
				IFile schemaFile = modelFolder.getFile(schemaFileName);
				if (schemaFile.exists() && backupOldFile) {
					// Let's be nice and back up the old file.
					IFile backupFile = modelFolder.getFile(schemaFileName + "_" + getTimestampForBackupFile());
					InputStream is = schemaFile.getContents();
					if (!backupFile.exists()) {
						backupFile.create(is, true, null);
					}
					else {
						backupFile.setContents(is, true, true, null);
					}
					SystemUtils.closeQuietly(is);
				}
				if (!schemaFile.exists()) {
					schemaFile.create(new ByteArrayInputStream(newContents.getBytes()), true, null);
				}
				else {
					schemaFile.setContents(new ByteArrayInputStream(newContents.getBytes()), true, true, null);
				}
				return true;
			}
		}
		catch (CoreException ce) {
			ce.printStackTrace();
		}
		return false;
	}

	private void updateSchemaIfNecessary(IProject project, Xadl3SchemaLocation schemaLocation) throws IOException,
			CoreException {
		if (schemaLocation.isCopyLocally()) {
			URI uri = schemaLocation.getUrl();
			String schemaFileName = uri.path().substring(uri.path().lastIndexOf('/') + 1);

			// See if the schema already exists. If so, we may not have to update it.

			String newSchemaContents = null;
			boolean needsUpdate = false;
			if (schemaExists(project, schemaFileName)) {
				// Check when we last updated it.
				long lastUpdateTime = getSchemaLastUpdateTime(project, schemaFileName);
				if (updateTimePassed(project, lastUpdateTime, schemaLocation)) {
					// OK, it's time to check for an update. Let's see if the schema's contents have changed.
					newSchemaContents = getContents(uri);
					if (newSchemaContents != null) {
						// Only process if we successfully read the contents
						String oldSchemaContents = getSchemaContents(project, schemaFileName);
						if (newSchemaContents.equals(oldSchemaContents)) {
							// The contents were equivalent; this counts as a done update.
							needsUpdate = false;
							setSchemaLastUpdateTime(project, schemaFileName, new java.util.Date().getTime());
						}
						else {
							// We have to update.
							needsUpdate = true;
						}
					}
				}
			}
			else {
				// If schema does not exist locally, then it definitely needs an update.
				needsUpdate = true;
				newSchemaContents = getContents(uri);
			}

			if (needsUpdate && newSchemaContents != null) {
				boolean success = writeSchema(project, schemaFileName, newSchemaContents, true);
				if (success) {
					setSchemaLastUpdateTime(project, schemaFileName, new java.util.Date().getTime());
				}
			}
		}
	}

	public List<String> getNonCopiedSchemaURIs(IProject project) {
		return Lists.newArrayList(Iterables.transform(
				Iterables.filter(Xadl3SchemaLocation.parse(project), new Predicate<Xadl3SchemaLocation>() {

					@Override
					public boolean apply(Xadl3SchemaLocation input) {
						return !input.isCopyLocally();
					}
				}), new Function<Xadl3SchemaLocation, String>() {

					@Override
					public String apply(Xadl3SchemaLocation input) {
						return input.getUrl().toString();
					}
				}));
	}
}
