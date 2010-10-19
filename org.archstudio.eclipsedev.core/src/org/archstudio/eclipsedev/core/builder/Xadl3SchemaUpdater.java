package org.archstudio.eclipsedev.core.builder;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.archstudio.eclipsedev.common.EclipseDevConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.pde.core.plugin.IExtensions;
import org.eclipse.pde.core.plugin.IPluginAttribute;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginObject;
import org.eclipse.pde.core.plugin.PluginRegistry;

/*
 * This class makes a non-standard use of Eclipse extension points.  This class
 * lives at the meta-Eclipse level, which means it's part of a plug-in that's 
 * installed in the development copy of Eclipse.  ArchStudio runs in the
 * next instance.  So, here we want to look at some data defined in plug-in
 * extension points.  Normally, that data lives in plug-ins installed in the
 * current instance of Eclipse (i.e., that were developed one meta-level higher).
 * But in this case, we want to look at data that's stored in the plug-ins
 * whose code is in the workspace.  
 */

public class Xadl3SchemaUpdater {

	private static final Xadl3SchemaUpdater INSTANCE = new Xadl3SchemaUpdater();

	private enum UpdateFrequency {
		NEVER("never", -1),
		EVERY_BUILD("everyBuild", 0),
		HOURLY("hourly", 60 * 60 * 1000),
		DAILY("daily", 60 * 60 * 24 * 1000),
		WEEKLY("weekly", 60 * 60 * 24 * 7 * 1000),
		MONTHLY("monthly", 60 * 60 * 24 * 30 * 1000);
		
		private final String stringRepresentation;
		private final long numMilliseconds;
		
		private UpdateFrequency(String stringRepresentation, long numMilliseconds) {
			this.stringRepresentation = stringRepresentation;
			this.numMilliseconds = numMilliseconds;
		}
		
		public String getStringRepresentation() {
			return stringRepresentation;
		}
		
		public long getNumMilliseconds() {
			return numMilliseconds;
		}
		
		public static UpdateFrequency fromString(String stringRepresentation) {
			for (UpdateFrequency f : UpdateFrequency.values()) {
				if (f.getStringRepresentation().equals(stringRepresentation)) {
					return f;
				}
			}
			return null;
		}
		
		public String toString() {
			return getStringRepresentation();
		}
	}
	
	private static class Xadl3SchemaLocation {
		protected final String urlString;
		protected final UpdateFrequency updateFrequency;
		
		public Xadl3SchemaLocation(String urlString, UpdateFrequency updateFrequency) {
			this.urlString = urlString;
			this.updateFrequency = updateFrequency;
		}
		
		public String getUrlString() { 
			return urlString;
		}
		
		public UpdateFrequency getUpdateFrequency() {
			return updateFrequency;
		}
		
		public String toString() {
			return "Xadl3SchemaLocation {urlString=\"" + getUrlString() + "\"; updateFrequency=" + getUpdateFrequency() + "}";
		}
	}
	
	private final ResourceSet resourceSet;
	private final URIConverter uriConverter;
	
	private Xadl3SchemaUpdater() { 
		resourceSet = new ResourceSetImpl();
		uriConverter = resourceSet.getURIConverter();
	}
	
	public boolean hasXadl3Nature(IProject project) throws CoreException {
		return project.getNature(EclipseDevConstants.NATURE_ID) != null;
	}
	
	public List<Xadl3SchemaLocation> getSchemaLocations(IProject project) {
		List<Xadl3SchemaLocation> locationList = new ArrayList<Xadl3SchemaLocation>();
		IPluginModelBase workspacePluginModelBase = PluginRegistry.findModel(project);
		if (workspacePluginModelBase != null) {
			IExtensions pluginExtensions = workspacePluginModelBase.getExtensions();
			if (pluginExtensions != null) {
				for (IPluginExtension pluginExtension : pluginExtensions.getExtensions()) {
					if ((pluginExtension.getPoint() != null) && (pluginExtension.getPoint().equals(EclipseDevConstants.SCHEMALOCATION_EXTENSION_POINT_ID))) {
						IPluginObject[] pluginObjects = pluginExtension.getChildren();
						if ((pluginObjects != null) && (pluginObjects.length > 0)) {
							if (pluginObjects[0] instanceof IPluginElement) {
								IPluginElement pluginElement = (IPluginElement)pluginObjects[0];
								IPluginAttribute urlAttribute = pluginElement.getAttribute("url");
								String url = null;
								if (urlAttribute != null) {
									url = urlAttribute.getValue();
								}
								
								IPluginAttribute autoUpdateFrequencyAttribute = pluginElement.getAttribute("autoUpdateFrequency");
								UpdateFrequency autoUpdateFrequency = null;
								if (autoUpdateFrequencyAttribute != null) {
									autoUpdateFrequency = UpdateFrequency.fromString(autoUpdateFrequencyAttribute.getValue());
								}
								
								locationList.add(new Xadl3SchemaLocation(url, autoUpdateFrequency));
							}
						}
					}
				}
			}
		}
		return locationList;
	}
	
	public URI getSchemaURI(String urlString) {
		return URI.createURI(urlString);
	}
	
	public String getSchemaContents(URI uri) {
		String result = null;
		InputStream is = null;
		try{
			is = uriConverter.createInputStream(uri);
			result = IOUtils.toString(is);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
		finally {
			IOUtils.closeQuietly(is);
		}
		return result;
	}
	
	public boolean schemaExists(IProject project, String schemaFileName) {
		IFolder modelFolder = project.getFolder("model");
		if (modelFolder.exists()) {
			IFile schemaFile = modelFolder.getFile(schemaFileName);
			if((schemaFile != null) && (schemaFile.exists())){
				return true;
			}
		}
		return false;
	}
	
	public String getSchemaContents(IProject project, String schemaFileName) {
		IFolder modelFolder = project.getFolder("model");
		if (modelFolder.exists()) {
			IFile file = modelFolder.getFile(schemaFileName);
			if (file.exists()) {
				InputStream is = null;
				try {
					is = file.getContents();
					return IOUtils.toString(is);
				}
				catch (IOException ioe) {
					return null;
				}
				catch (CoreException ce) {
					return null;
				}
				finally {
					IOUtils.closeQuietly(is);
				}
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public Map<String,Long> getSchemaLastUpdatedTimes(IProject project) {
		/* Last-updated times for schemas are stored as persistent project
		 *  properties.  The qualified name has two parts: the qualifier
		 *  is a distinguished URI and the local name is the filename of
		 *  the schema.  The property value is a stringified Long representing
		 *  the last updated time for the schema.  
		 */
		Map<String,Long> lastUpdatedTimes = new HashMap<String,Long>();
		
		try {
			for (Iterator it = project.getPersistentProperties().keySet().iterator(); it.hasNext(); ) {
				QualifiedName qn = (QualifiedName)it.next();
				String qualifier = qn.getQualifier();
				if ((qualifier != null) && qualifier.equals(EclipseDevConstants.SCHEMA_LAST_UPDATE_TIME_URI)) {
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
		QualifiedName qn = new QualifiedName(EclipseDevConstants.SCHEMA_LAST_UPDATE_TIME_URI, schemaFileName);
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
		QualifiedName qn = new QualifiedName(EclipseDevConstants.SCHEMA_LAST_UPDATE_TIME_URI, schemaFileName);
		try {
			project.setPersistentProperty(qn, Long.toString(time));
		}
		catch (CoreException ce) {
			//This is best-effort
			ce.printStackTrace();
		}
	}
	
	public void updateSchemasIfNecessary(IProject project) {
		for (Xadl3SchemaLocation schemaLocation : getSchemaLocations(project)) {
			updateSchemaIfNecessary(project, schemaLocation);
		}
	}
	
	private boolean updateTimePassed(long lastUpdateTime, Xadl3SchemaLocation schemaLocation) {
		if (schemaLocation.getUpdateFrequency().equals(UpdateFrequency.NEVER)) {
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
			if ((currentTime - lastUpdateTime) > schemaLocation.getUpdateFrequency().getNumMilliseconds()) {
				return true;
			}
			return false;
		}
	}
	
	private static final DateFormat df = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
	
	private String getTimestampForBackupFile() {
		return df.format(new java.util.Date());
	}
	
	@SuppressWarnings("deprecation")
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
					IOUtils.closeQuietly(is);
				}
				if (!schemaFile.exists()) {
					schemaFile.create(new StringBufferInputStream(newContents), true, null);
				}
				else {
					schemaFile.setContents(new StringBufferInputStream(newContents), true, true, null);
				}
				return true;
			}
		}
		catch (CoreException ce) {
			ce.printStackTrace();
		}
		return false;
	}
	
	private void updateSchemaIfNecessary(IProject project, Xadl3SchemaLocation schemaLocation) {
		String urlString = schemaLocation.getUrlString();
		URI uri = URI.createURI(urlString);
		String schemaFileName = uri.path().substring(uri.path().lastIndexOf('/') + 1);
		
		// See if the schema already exists.  If so, we may not have to update it.
		
		String newSchemaContents = null;
		boolean needsUpdate = false;
		if (schemaExists(project, schemaFileName)) {
			// Check when we last updated it.
			long lastUpdateTime = getSchemaLastUpdateTime(project, schemaFileName);
			if (updateTimePassed(lastUpdateTime, schemaLocation)) {
				// OK, it's time to check for an update.  Let's see if the
				// schema's contents have changed.
				newSchemaContents = getSchemaContents(uri);
				if (newSchemaContents != null) {
					// Only process if we successfully read the contents
					String oldSchemaContents = getSchemaContents(project, schemaFileName);
					if (newSchemaContents.equals(oldSchemaContents)) {
						// The contents were equivalent; this counts as
						// a done update.
						needsUpdate = false;
						setSchemaLastUpdateTime(project, schemaFileName, new java.util.Date().getTime());
					}
					else {
						//We have to update.
						needsUpdate = true;
					}
				}
			}
		}
		else {
			// If schema does not exist locally, then it definitely
			// needs an update.
			needsUpdate = true;
			newSchemaContents = getSchemaContents(uri);
		}
		
		if (needsUpdate && (newSchemaContents != null)) {
			boolean success = writeSchema(project, schemaFileName, newSchemaContents, true);
			if (success) {
				setSchemaLastUpdateTime(project, schemaFileName, new java.util.Date().getTime());
			}
		}
	}

	public static Xadl3SchemaUpdater getInstance() { 
		return INSTANCE; 
	}
	

}
