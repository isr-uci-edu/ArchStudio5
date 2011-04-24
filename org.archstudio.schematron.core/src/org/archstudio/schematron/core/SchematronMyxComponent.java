package org.archstudio.schematron.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.archstudio.archlight.ArchlightIssue;
import org.archstudio.archlight.ArchlightTest;
import org.archstudio.archlight.ArchlightTestError;
import org.archstudio.archlight.ArchlightTestResult;
import org.archstudio.archlight.IArchlightTool;
import org.archstudio.issueadt.IArchlightIssueADT;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.noticeadt.IArchlightNoticeADT;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.testadt.IArchlightTestADT;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.preference.IPreferenceStore;

public class SchematronMyxComponent extends AbstractMyxSimpleBrick implements IArchlightTool, IMyxDynamicBrick {

	public static final String TOOL_ID = "Schematron";

	public static final IMyxName INTERFACE_NAME_IN_TOOL = MyxUtils.createName("archlighttool");

	public static final IMyxName INTERFACE_NAME_OUT_XARCHADT = MyxUtils.createName("xarch");
	public static final IMyxName INTERFACE_NAME_OUT_ISSUEADT = MyxUtils.createName("archlightissueadt");
	public static final IMyxName INTERFACE_NAME_OUT_TESTADT = MyxUtils.createName("archlighttestadt");
	public static final IMyxName INTERFACE_NAME_OUT_NOTICEADT = MyxUtils.createName("archlightnoticeadt");
	public static final IMyxName INTERFACE_NAME_OUT_PREFERENCES = MyxUtils.createName("preferences");

	protected IXArchADT xarch = null;
	protected IArchlightIssueADT issueadt = null;
	protected IArchlightNoticeADT noticeadt = null;
	protected IArchlightTestADT testadt = null;
	protected IPreferenceStore preferences = null;

	protected SchematronTestManager testManager = null;

	protected boolean xalanVersionOK = false;

	public SchematronMyxComponent() {
	}

	public String getToolID() {
		return TOOL_ID;
	}

	public void init() {
		//init stuff
	}

	public void begin() {
		testManager = new SchematronTestManager(TOOL_ID, preferences);
		noticeadt.addNotice(TOOL_ID, "Schematron Archlight Tool Initialized at [" + SystemUtils.getDateAndTime() + "]");
		String xalanVersion = SchematronUtils.getXalanVersion();
		if (xalanVersion == null) {
			xalanVersionOK = false;
			noticeadt.addNotice(TOOL_ID, "Error: No Xalan version found.  Tests cannot run.");
		}
		else {
			xalanVersionOK = true;
			noticeadt.addNotice(TOOL_ID, "Xalan version " + xalanVersion);
		}
		reloadTests();
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_ISSUEADT)) {
			issueadt = (IArchlightIssueADT) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_NOTICEADT)) {
			noticeadt = (IArchlightNoticeADT) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_TESTADT)) {
			testadt = (IArchlightTestADT) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_XARCHADT)) {
			xarch = (IXArchADT) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)) {
			preferences = (IPreferenceStore) serviceObject;
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_ISSUEADT)) {
			issueadt = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_NOTICEADT)) {
			noticeadt = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_TESTADT)) {
			testadt = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_XARCHADT)) {
			xarch = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)) {
			preferences = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_TOOL)) {
			return this;
		}
		return null;
	}

	public void reloadTests() {
		noticeadt.addNotice(TOOL_ID, "Reloading tests at [" + SystemUtils.getDateAndTime() + "]");
		List<? extends ArchlightTest> oldTests = testadt.getAllTests(TOOL_ID);
		testManager.reload();
		List<? extends ArchlightTest> newTests = testManager.getAllArchlightTests();
		testadt.removeTests(oldTests);
		testadt.addTests(newTests);

		List<? extends Object> warnings = testManager.getWarnings();
		if (!warnings.isEmpty()) {
			for (Object warning : warnings) {
				if (warning instanceof String) {
					noticeadt.addNotice(TOOL_ID, "Warning:" + warning);
				}
				else if (warning instanceof Throwable) {
					Throwable t = (Throwable) warning;
					noticeadt.addNotice(TOOL_ID, "Error: " + t.getMessage(), t);
				}
			}
		}
	}

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
					SchematronTestException ste = new SchematronTestException("Error parsing Schematron test file.", stfpe);
					schematronTestErrorList.add(ste);
				}
				if (fileToRun != null) {
					SchematronTester tester = new SchematronTester(xmlDocument, fileToRun);
					noticeadt.addNotice(TOOL_ID, "Processing: " + fileToRun.getSourceURL());
					float pct = (float) f / (float) filesToRunSize;
					pct *= 100;
					if (pct == 0)
						pct = 5;
					//sendToolStatus("Running Tests", (int)pct);
					try {
						tester.runTest();
						for (Object result : SchematronTestResultParser.parseTestResults(xarch, docRef, TOOL_ID, tester.getResult())) {
							if (result instanceof SchematronTestException) {
								schematronTestErrorList.add((SchematronTestException) result);
							}
							else if (result instanceof ArchlightTestResult) {
								//System.out.println("result: " + results[i]);
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

		ArchlightTestResult[] testResults = (ArchlightTestResult[]) archlightTestResultList.toArray(new ArchlightTestResult[0]);

		//Remove old issues
		List<? extends ArchlightIssue> oldIssues = issueadt.getAllIssues(documentRef, TOOL_ID);
		issueadt.removeIssues(oldIssues);

		//Store the new issues
		if (testResults.length > 0) {
			List<ArchlightIssue> issueList = new ArrayList<ArchlightIssue>(testResults.length);
			for (int i = 0; i < testResults.length; i++) {
				for (ArchlightIssue issue : testResults[i].getIssues()) {
					issueList.add(issue);
				}
			}
			issueadt.addIssues(issueList);
		}

		//Store the errors
		for (SchematronTestException exception : schematronTestErrorList) {
			exception.printStackTrace();
			noticeadt.addNotice(new ArchlightTestError(exception.getTestUID(), TOOL_ID, exception.getMessage(), null, exception));
		}
	}
}
