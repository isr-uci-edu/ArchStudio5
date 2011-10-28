package org.archstudio.schematron.core;

// import archstudio.comp.preferences.IPreferences;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.archstudio.archlight.ArchlightTest;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.preference.IPreferenceStore;

public class SchematronTestManager {
	public static final String DEFAULT_TEST_FILE_URL = "platform:/plugin/org.archstudio.schematron.core/res/";

	public static final String RULE_FILE_INDEX_NAME = "rulefileindex.txt";

	protected String toolID;

	protected IPreferenceStore preferences;

	protected List<String> testFileBaseURLs = null;

	protected List<String> testFileURLs = null;

	protected List<SchematronTestFile> testFiles = null;

	protected List<ArchlightTest> archlightTests = null;

	// Either strings or Throwables
	protected List<Object> warnings = new ArrayList<Object>();

	public SchematronTestManager(String toolID, IPreferenceStore preferences) {
		this.toolID = toolID;
		this.preferences = preferences;
	}

	public List<? extends SchematronTestFile> getAllTestFiles() {
		return testFiles;
	}

	public ArchlightTest getArchlightTest(String uid) {
		for (ArchlightTest archlightTest : getAllArchlightTests()) {
			if (archlightTest.getUID().equals(uid)) {
				return archlightTest;
			}
		}
		return null;
	}

	public List<? extends ArchlightTest> getAllArchlightTests() {
		return archlightTests;
	}

	public List<? extends Object> getWarnings() {
		return warnings;
	}

	public void reload() {
		clearWarnings();
		reloadBaseURLs();
		reloadFileURLs();
		reloadTestFiles();
		reloadArchlightTests();
	}

	private void clearWarnings() {
		warnings.clear();
	}

	private void reloadBaseURLs() {
		List<String> testFileBaseURLList = new ArrayList<String>();
		testFileBaseURLList.add(DEFAULT_TEST_FILE_URL);

		String s = preferences.getString(SchematronConstants.PREF_TEST_FILE_PATHS);
		System.out.println(s);
		while (true) {
			// Process s;
			break;
		}
		//TODO: Finish this

		testFileBaseURLs = testFileBaseURLList;
	}

	private InputStream openURI(String uriString) throws IOException {
		URI uri = URI.createURI(uriString);
		InputStream is = URIConverter.INSTANCE.createInputStream(uri);
		return is;
	}

	private void reloadFileURLs() {
		List<String> newTestFileURLs = new ArrayList<String>();
		List<Object> newWarnings = new ArrayList<Object>();

		for (String urlString : testFileBaseURLs) {
			if (!urlString.endsWith("/")) {
				urlString += "/";
			}
			String ruleFileIndexURLString = urlString + RULE_FILE_INDEX_NAME;
			try {
				InputStream is = openURI(ruleFileIndexURLString);
				if (is == null) {
					throw new FileNotFoundException("Could not find file: " + ruleFileIndexURLString);
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				while (true) {
					String fileName = br.readLine();
					if (fileName == null) {
						break;
					}
					fileName = fileName.trim();
					if (fileName.length() == 0) {
						continue;
					}
					String ruleFileURLString = urlString + fileName;
					newTestFileURLs.add(ruleFileURLString);
				}
				br.close();
			}
			catch (MalformedURLException mue) {
				newWarnings.add(mue);
			}
			catch (FileNotFoundException fnfe) {
				newWarnings.add(fnfe);
			}
			catch (IOException ioe) {
				newWarnings.add(ioe);
			}
		}
		testFileURLs = newTestFileURLs;
		warnings.addAll(newWarnings);
	}

	private void reloadTestFiles() {
		List<SchematronTestFile> newTestFiles = new ArrayList<SchematronTestFile>();
		List<Object> newWarnings = new ArrayList<Object>();

		for (String testFileURL : testFileURLs) {
			try {
				SchematronTestFile stf = SchematronTestFile.create(toolID, testFileURL);
				newTestFiles.add(stf);

				for (String additionalWarning : stf.getParseWarnings()) {
					newWarnings.add(additionalWarning);
				}
			}
			catch (SchematronTestFileParseException stfpe) {
				newWarnings.add(stfpe);
			}
			catch (MalformedURLException mue) {
				newWarnings.add(mue);
			}
			catch (FileNotFoundException fnfe) {
				newWarnings.add(fnfe);
			}
			catch (IOException ioe) {
				newWarnings.add(ioe);
			}
		}

		testFiles = newTestFiles;
		warnings.addAll(newWarnings);
	}

	private void reloadArchlightTests() {
		List<ArchlightTest> newArchlightTests = new ArrayList<ArchlightTest>();
		List<Object> newWarnings = new ArrayList<Object>();
		Set<String> testUIDs = new HashSet<String>();

		for (SchematronTestFile testFile : testFiles) {
			for (ArchlightTest fileTest : testFile.getArchlightTests()) {
				String testUID = fileTest.getUID();
				if (testUIDs.contains(testUID)) {
					SchematronInitializationException e = new SchematronInitializationException("Duplicate Test UID: "
							+ testUID);
					newWarnings.add(e);
				}
				else {
					newArchlightTests.add(fileTest);
					testUIDs.add(testUID);
				}
			}
		}

		warnings.addAll(newWarnings);
		archlightTests = newArchlightTests;
	}
}
