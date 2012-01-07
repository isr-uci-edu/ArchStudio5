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
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.ObjRef;

/**
 * Myx brick: "Schematron Impl"
 * 
 * @see org.archstudio.schematron.core.SchematronMyxComponentStub
 * @generated
 */
public class SchematronMyxComponent extends
		org.archstudio.schematron.core.SchematronMyxComponentStub {

	public static final String TOOL_ID = "Schematron";

	protected SchematronTestManager testManager = null;

	protected boolean xalanVersionOK = false;

	public SchematronMyxComponent() {
	}

	@Override
	public String getToolID() {
		return TOOL_ID;
	}

	@Override
	public void begin() {
		testManager = new SchematronTestManager(TOOL_ID, preferences);
		notices.addNotice(TOOL_ID, "Schematron Archlight Tool Initialized at ["
				+ SystemUtils.getDateAndTime() + "]");
		String xalanVersion = SchematronUtils.getXalanVersion();
		if (xalanVersion == null) {
			xalanVersionOK = false;
			notices.addNotice(TOOL_ID,
					"Error: No Xalan version found.  Tests cannot run.");
		} else {
			xalanVersionOK = true;
			notices.addNotice(TOOL_ID, "Xalan version " + xalanVersion);
		}
		reloadTests();
	}

	@Override
	public void reloadTests() {
		notices.addNotice(TOOL_ID,
				"Reloading tests at [" + SystemUtils.getDateAndTime() + "]");
		List<? extends ArchlightTest> oldTests = tests.getAllTests(TOOL_ID);
		testManager.reload();
		List<? extends ArchlightTest> newTests = testManager
				.getAllArchlightTests();
		tests.removeTests(oldTests);
		tests.addTests(newTests);

		List<? extends Object> warnings = testManager.getWarnings();
		if (!warnings.isEmpty()) {
			for (Object warning : warnings) {
				if (warning instanceof String) {
					notices.addNotice(TOOL_ID, "Warning:" + warning);
				} else if (warning instanceof Throwable) {
					Throwable t = (Throwable) warning;
					notices.addNotice(TOOL_ID, "Error: " + t.getMessage(), t);
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
		} else {
			List<? extends SchematronTestFile> testFiles = testManager
					.getAllTestFiles();
			Set<SchematronTestFile> filesToRun = new HashSet<SchematronTestFile>();
			List<String> testUIDsToRun = new ArrayList<String>(testUIDs);

			for (String testUIDToRun : testUIDsToRun) {
				for (SchematronTestFile testFile : testFiles) {
					for (ArchlightTest testInFile : testFile
							.getArchlightTests()) {
						if (testUIDToRun.equals(testInFile.getUID())) {
							filesToRun.add(testFile);
							break;
						}
					}
				}
			}
			ObjRef docRef = documentRef;
			String xmlDocument = new String(xarch.serialize(xarch
					.getURI(docRef)));
			int filesToRunSize = filesToRun.size();

			int f = 0;
			for (SchematronTestFile fileToRun : filesToRun) {
				try {
					fileToRun = SchematronTestFile.create(fileToRun,
							testUIDsToRun);
				} catch (SchematronTestFileParseException stfpe) {
					SchematronTestException ste = new SchematronTestException(
							"Error parsing Schematron test file.", stfpe);
					schematronTestErrorList.add(ste);
				}
				if (fileToRun != null) {
					SchematronTester tester = new SchematronTester(xmlDocument,
							fileToRun);
					notices.addNotice(TOOL_ID,
							"Processing: " + fileToRun.getSourceURL());
					float pct = (float) f / (float) filesToRunSize;
					pct *= 100;
					if (pct == 0) {
						pct = 5;
					}
					//sendToolStatus("Running Tests", (int)pct);
					try {
						tester.runTest();
						for (Object result : SchematronTestResultParser
								.parseTestResults(xarch, docRef, TOOL_ID,
										tester.getResult())) {
							if (result instanceof SchematronTestException) {
								schematronTestErrorList
										.add((SchematronTestException) result);
							} else if (result instanceof ArchlightTestResult) {
								//System.out.println("result: " + result);
								archlightTestResultList
										.add((ArchlightTestResult) result);
							}
						}
					} catch (SchematronInitializationException sie) {
						SchematronTestException ste = new SchematronTestException(
								"Error initializing Schematron", sie);
						schematronTestErrorList.add(ste);
					} catch (SchematronTestException ste) {
						schematronTestErrorList.add(ste);
					}
				}
				f++;
			}
			//sendToolStatus("Idle", -1);
		}
		//Now we have two lists: a list of TronTestResults and a list of
		//SchematronTestExceptions if anything went wrong during testing.

		ArchlightTestResult[] testResults = archlightTestResultList
				.toArray(new ArchlightTestResult[0]);

		//Remove old issues
		List<? extends ArchlightIssue> oldIssues = issues.getAllIssues(
				documentRef, TOOL_ID);
		issues.removeIssues(oldIssues);

		//Store the new issues
		if (testResults.length > 0) {
			List<ArchlightIssue> issueList = new ArrayList<ArchlightIssue>(
					testResults.length);
			for (ArchlightTestResult testResult : testResults) {
				for (ArchlightIssue issue : testResult.getIssues()) {
					issueList.add(issue);
				}
			}
			issues.addIssues(issueList);
		}

		//Store the errors
		for (SchematronTestException exception : schematronTestErrorList) {
			exception.printStackTrace();
			notices.addNotice(new ArchlightTestError(exception.getTestUID(),
					TOOL_ID, exception.getMessage(), null, exception));
		}
	}
}