package org.archstudio.schematron.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.archstudio.archlight.ArchlightElementIdentifier;
import org.archstudio.archlight.ArchlightIssue;
import org.archstudio.archlight.ArchlightTest;
import org.archstudio.archlight.ArchlightTestResult;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * Myx brick: "Schematron Impl"
 * 
 * @see org.archstudio.schematron.core.SchematronMyxComponentStub
 * @generated
 */
public class SchematronMyxComponent extends org.archstudio.schematron.core.SchematronMyxComponentStub {

	public static final String TOOL_ID = "Schematron";

	protected SchematronTestManager testManager = null;

	protected boolean xalanVersionOK = false;

	public SchematronMyxComponent() {
	}

	@Override
	public String getToolID() {
		return TOOL_ID;
	}

	protected void addNotice(String text) {
		ILog log = Activator.getDefault().getLog();
		log.log(new Status(IStatus.INFO, Activator.getDefault().getBundle().getSymbolicName(), TOOL_ID
				+ " Archlight Tool: " + text));
	}

	protected void addError(String text, Throwable t) {
		ILog log = Activator.getDefault().getLog();
		log.log(new Status(IStatus.ERROR, Activator.getDefault().getBundle().getSymbolicName(), TOOL_ID
				+ " Archlight Tool: " + text, t));
	}

	@Override
	public void begin() {
		testManager = new SchematronTestManager(TOOL_ID);
		addNotice("Initialized at [" + SystemUtils.getDateAndTime() + "]");
		String xalanVersion = SchematronUtils.getXalanVersion();
		if (xalanVersion == null) {
			xalanVersionOK = false;
			addError("No Xalan version found. Tests cannot run.", null);
		}
		else {
			xalanVersionOK = true;
			addNotice("Xalan version " + xalanVersion);
		}
		reloadTests();
	}

	@Override
	public void reloadTests() {
		addNotice("Reloading tests at [" + SystemUtils.getDateAndTime() + "]");
		testManager.reload();
		List<? extends ArchlightTest> newTests = testManager.getAllArchlightTests();
		tests.addTests(newTests);

		List<? extends Object> warnings = testManager.getWarnings();
		if (!warnings.isEmpty()) {
			for (Object warning : warnings) {
				if (warning instanceof String) {
					addNotice("Warning:" + warning);
				}
				else if (warning instanceof Throwable) {
					Throwable t = (Throwable) warning;
					addError("Error: " + t.getMessage(), t);
				}
			}
		}
	}

	@Override
	public void runTests(ObjRef documentRef, Collection<String> testUIDs) {
		List<SchematronTestException> schematronTestErrorList = new ArrayList<SchematronTestException>();
		List<ArchlightTestResult> archlightTestResultList = new ArrayList<ArchlightTestResult>();

		if (!xalanVersionOK) {
			SchematronTestException ste = new SchematronTestException(
					"Schematron requires Xalan; but the version of Xalan available was not sufficient to run Schematron tests.");
			schematronTestErrorList.add(ste);
		}
		else {
			List<? extends SchematronTestFile> testFiles = testManager.getAllTestFiles();
			Set<SchematronTestFile> filesToRun = new HashSet<SchematronTestFile>();
			List<String> testUIDsToRun = new ArrayList<String>(testUIDs);

			for (String testUIDToRun : testUIDsToRun) {
				for (SchematronTestFile testFile : testFiles) {
					for (ArchlightTest testInFile : testFile.getArchlightTests()) {
						if (testUIDToRun.equals(testInFile.getUID())) {
							filesToRun.add(testFile);
							break;
						}
					}
				}
			}
			ObjRef docRef = documentRef;
			String xmlDocument = new String(xarch.serialize(xarch.getURI(docRef)));
			int filesToRunSize = filesToRun.size();

			int f = 0;
			for (SchematronTestFile fileToRun : filesToRun) {
				try {
					fileToRun = SchematronTestFile.create(fileToRun, testUIDsToRun);
				}
				catch (SchematronTestFileParseException stfpe) {
					SchematronTestException ste = new SchematronTestException("Error parsing Schematron test file.",
							stfpe);
					schematronTestErrorList.add(ste);
				}
				if (fileToRun != null) {
					SchematronTester tester = new SchematronTester(xmlDocument, fileToRun);
					addNotice("Processing: " + fileToRun.getSourceURL());
					float pct = (float) f / (float) filesToRunSize;
					pct *= 100;
					if (pct == 0) {
						pct = 5;
					}
					//sendToolStatus("Running Tests", (int)pct);
					try {
						tester.runTest();
						for (Object result : SchematronTestResultParser.parseTestResults(xarch, docRef, TOOL_ID,
								tester.getResult())) {
							if (result instanceof SchematronTestException) {
								schematronTestErrorList.add((SchematronTestException) result);
							}
							else if (result instanceof ArchlightTestResult) {
								//System.out.println("result: " + result);
								archlightTestResultList.add((ArchlightTestResult) result);
							}
						}
					}
					catch (SchematronInitializationException sie) {
						SchematronTestException ste = new SchematronTestException("Error initializing Schematron", sie);
						schematronTestErrorList.add(ste);
					}
					catch (SchematronTestException ste) {
						schematronTestErrorList.add(ste);
					}
				}
				f++;
			}
			//sendToolStatus("Idle", -1);
		}
		//Now we have two lists: a list of TronTestResults and a list of
		//SchematronTestExceptions if anything went wrong during testing.

		ArchlightTestResult[] testResults = archlightTestResultList.toArray(new ArchlightTestResult[0]);

		try {
			// get resource for xarch document ref
			URI uri = xarch.getURI(documentRef);
			IResource resource = null;
			if ("platform".equalsIgnoreCase(uri.scheme())) {
				if (uri.segmentCount() > 1 && uri.segment(0).equals("resource")) {
					List<String> pathSegs = Lists.newArrayList(uri.segmentsList());
					IPath path = new Path(Joiner.on("/").join(pathSegs));
					resource = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
				}
			}
			if ("file".equalsIgnoreCase(uri.scheme())) {
				for (IFile file : ResourcesPlugin.getWorkspace().getRoot()
						.findFilesForLocationURI(new java.net.URI(uri.toString()))) {
					resource = file;
					break;
				}
			}

			if (resource != null) {

				//Remove old issues
				for (IMarker marker : resource.findMarkers(IMarker.PROBLEM, false, IResource.DEPTH_INFINITE)) {
					if (this.getClass().getName().equals(marker.getAttribute(IMarker.SOURCE_ID))) {
						marker.delete();
					}
				}

				//Store the new issues
				if (testResults.length > 0) {
					for (ArchlightTestResult testResult : testResults) {
						for (ArchlightIssue issue : testResult.getIssues()) {
							for (ArchlightElementIdentifier id : issue.getElementIdentifiers()) {
								IMarker marker = resource.createMarker(IMarker.PROBLEM);
								int severity;
								switch (issue.getSeverity()) {
								case ArchlightIssue.SEVERITY_INFO:
									severity = IMarker.SEVERITY_INFO;
									break;
								case ArchlightIssue.SEVERITY_WARNING:
									severity = IMarker.SEVERITY_WARNING;
									break;
								default:
									severity = IMarker.SEVERITY_ERROR;
								}
								marker.setAttribute(IMarker.SEVERITY, severity);
								marker.setAttribute(IMarker.MESSAGE, issue.getDetailedDescription());
								marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
								marker.setAttribute(IMarker.LOCATION, id.getElementID());
								marker.setAttribute(IMarker.SOURCE_ID, this.getClass().getName());
							}
						}
					}
				}
			}

			//Store the errors
			for (SchematronTestException exception : schematronTestErrorList) {
				addError("Error: " + exception.getMessage(), exception);
			}
		}
		catch (Exception e) {
			addError("Error: " + e.getMessage(), e);
		}

	}
}