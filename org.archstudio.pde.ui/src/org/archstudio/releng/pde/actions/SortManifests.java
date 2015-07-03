package org.archstudio.releng.pde.actions;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collections;
import java.util.Comparator;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.List;
import java.util.Set;

import org.archstudio.sysutils.SystemUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.osgi.framework.util.Headers;
import org.eclipse.osgi.util.ManifestElement;
import org.osgi.framework.BundleException;

/**
 * Utility to sort the contents of a MANIFEST.MF file. The MANIFEST.MF specification is located at:
 * http://docs.oracle.com/javase/1.5.0/docs/guide/jar/jar.html#Manifest% 20Specification
 * <p>
 * Of note, Eclipse does not adhere to the maximum line length requirement, so neither do we. (It does when creating the
 * binary version of a plugin, however.)
 */
@SuppressWarnings("all")
public class SortManifests extends AbstractProjectHandler {

	private static final String MANIFEST_ELEMENT_SEPARATOR = ",";
	private static final String ATTRIBUTE_ASSIGNMENT = "=";
	private static final String DIRECTIVE_ASSIGNMENT = ":=";
	private static final String ATTRIBUTE_AND_DIRECTIVE_SEPARATOR = ";";
	private static final String MANIFEST_VERSION_HEADER = "Manifest-Version";
	private static final String MANIFEST_HEADER_VALUE_SEPARATOR = ": ";
	private static final String MANIFEST_CONTINUE_LINE_PREFIX = " ";
	private static final String EOL = System.getProperty("line.separator");
	private static final Set<String> HEADERS_TO_SORT = new HashSet<>();

	static {
		HEADERS_TO_SORT.add("Bundle-ClassPath");
		HEADERS_TO_SORT.add("Export-Package");
		HEADERS_TO_SORT.add("Import-Package");
		HEADERS_TO_SORT.add("Require-Bundle");
	}

	@Override
	protected void execute(IProject project) {
		try {
			// Sort MANIFEST.MF files.
			if (project.getFile("META-INF/MANIFEST.MF").exists()) {
				IFile manifestFile = project.getFile("META-INF/MANIFEST.MF");
				String manifest = new String(SystemUtils.blt(manifestFile.getContents()), "UTF-8");
				String sortedManifest = sort(manifest);
				manifestFile.setContents(new ByteArrayInputStream(sortedManifest.getBytes("UTF-8")), true, true, null);
				manifestFile.refreshLocal(IResource.DEPTH_ZERO, null);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String sort(String manifestInput) throws BundleException {
		// Parse the manifest input.
		byte[] inputByteArray = manifestInput.getBytes(StandardCharsets.UTF_8);
		Headers<String, String> manifestHeaders = Headers.parseManifest(new ByteArrayInputStream(inputByteArray));

		// Convert the headers to an array and sort by header name.
		Enumeration<String> headerEnumeration = manifestHeaders.keys();
		List<String> sortedHeaders = new ArrayList<>();
		while (headerEnumeration.hasMoreElements()) {
			sortedHeaders.add(headerEnumeration.nextElement());
		}
		Collections.sort(sortedHeaders);

		// Treat the manifest version specially and place it on the first line.
		String manifestVersion = manifestHeaders.get(MANIFEST_VERSION_HEADER);
		// Some older MANIFEST.MF files lack this property. So, add it.
		if (manifestVersion == null) {
			manifestVersion = "1.0";
		}
		sortedHeaders.remove(MANIFEST_VERSION_HEADER);

		// Capture each line of manifest output. Note: we allow these lines to
		// exceed the allowable line length for MANIFEST.MF files.
		List<String> manifestLineOutput = new ArrayList<>();
		manifestLineOutput.add(MANIFEST_VERSION_HEADER + MANIFEST_HEADER_VALUE_SEPARATOR + manifestVersion);

		// Combine headers into a new, sorted manifest file.
		for (String name : sortedHeaders) {
			String value = manifestHeaders.get(name);

			// This will hold the sorted elements of the header, or the value itself it cannot be split up into elements
			// to sort
			List<String> reassembledElements = new ArrayList<>();

			// Sort the elements only if the header is tagged in HEADERS_TO_SORT. We restrict ourselves to only these
			// headers because the parser will treat any ',' in header value as an element delimiter. This doesn't make
			// sense for some header values, such as the name specified by Bundle-Name (which may contain commas).
			if (HEADERS_TO_SORT.contains(name)) {

				// Break the header value into elements as delimited by a ','.
				ManifestElement[] elements = ManifestElement.parseHeader(name, value);
				Arrays.sort(elements, new Comparator<ManifestElement>() {
					@Override
					public int compare(ManifestElement o1, ManifestElement o2) {
						return o1.getValue().compareTo(o2.getValue());
					}
				});

				// Sort the elements themselves (they are accessed as a key in a map, so presumably this is okay).
				for (ManifestElement element : elements) {
					List<String> attributes = new ArrayList<>();
					List<String> directives = new ArrayList<>();

					// Sort and process attributes.
					Enumeration<String> attributeKeysEnumeration = element.getKeys();
					if (attributeKeysEnumeration != null) {
						Set<String> attributeKeysSet = new HashSet<>();
						while (attributeKeysEnumeration.hasMoreElements()) {
							attributeKeysSet.add(attributeKeysEnumeration.nextElement());
						}
						List<String> attributeKeysList = new ArrayList<>(attributeKeysSet);
						Collections.sort(attributeKeysList, new Comparator<String>() {
							@Override
							public int compare(String o1, String o2) {
								return o1.compareTo(o2);
							}
						});

						// Attributes may have multiple, unsorted, values. We leave them in the same order in case it is
						// semantically meaningful.
						for (String attributeKey : attributeKeysList) {
							for (String attributeValue : element.getAttributes(attributeKey)) {
								// Eclipse wraps attribute values in Quotation marks.
								attributes.add(attributeKey + ATTRIBUTE_ASSIGNMENT + "\"" + attributeValue + "\"");
							}
						}
					}

					// Sort and process directives.
					Enumeration<String> directiveKeysEnumeration = element.getDirectiveKeys();
					if (directiveKeysEnumeration != null) {
						Set<String> directiveKeysSet = new HashSet<>();
						while (directiveKeysEnumeration.hasMoreElements()) {
							directiveKeysSet.add(directiveKeysEnumeration.nextElement());
						}
						List<String> directiveKeysList = new ArrayList<>(directiveKeysSet);
						Collections.sort(directiveKeysList, new Comparator<String>() {
							@Override
							public int compare(String o1, String o2) {
								return o1.compareTo(o2);
							}
						});

						// Directives may have multiple, unsorted, values. We leave them in the same order in case it is
						// semantically meaningful.
						for (String directiveKey : directiveKeysList) {
							for (String directiveValue : element.getDirectives(directiveKey)) {
								directives.add(directiveKey + DIRECTIVE_ASSIGNMENT + directiveValue);
							}
						}
					}

					// Reconstruct the manifest element. Eclipse places attributes before directives.
					StringBuffer reassembledElement = new StringBuffer();
					reassembledElement.append(element.getValue());
					for (String attribute : attributes) {
						reassembledElement.append(ATTRIBUTE_AND_DIRECTIVE_SEPARATOR);
						reassembledElement.append(attribute);
					}
					for (String directive : directives) {
						reassembledElement.append(ATTRIBUTE_AND_DIRECTIVE_SEPARATOR);
						reassembledElement.append(directive);
					}

					reassembledElements.add(reassembledElement.toString());
				}
			} // if (HEADERS_TO_SORT.contains(name))
			else {
				// The header is not marked for sorting of its elements.
				reassembledElements.add(value);
			}

			// Reconstruct the header.
			StringBuffer manifestLine = new StringBuffer();
			for (int i = 0; i < reassembledElements.size(); i++) {
				manifestLine.delete(0, manifestLine.length());

				if (i == 0) {
					// The first line has the header name.
					manifestLine.append(name).append(MANIFEST_HEADER_VALUE_SEPARATOR);
				}
				else if (i > 0) {
					// Subsequent lines begin with a space.
					manifestLine.append(MANIFEST_CONTINUE_LINE_PREFIX);
				}
				manifestLine.append(reassembledElements.get(i));
				// Lines that continue on are followed with a comma.
				if (i < reassembledElements.size() - 1) {
					manifestLine.append(MANIFEST_ELEMENT_SEPARATOR);
				}

				manifestLineOutput.add(manifestLine.toString());
			}
		}

		// We don't bother wrapping lines since Eclipse doesn't either.
		StringBuffer manifestOutput = new StringBuffer();
		for (String line : manifestLineOutput) {
			manifestOutput.append(line);

			// Each line, including the terminal line, ends in a EOL.
			manifestOutput.append(EOL);
		}

		// Finally, Headers.parseManifest stops at the first empty line. So, we append the remaining part of the file,
		// if present.
		try {
			BufferedReader lineReader = new BufferedReader(new StringReader(manifestInput));
			String line;
			boolean hitEmptyLine = false;
			while ((line = lineReader.readLine()) != null) {
				if (line.trim().length() == 0) {
					hitEmptyLine = true;
				}
				if (hitEmptyLine) {
					manifestOutput.append(line).append(EOL);
				}
			}
		}
		catch (IOException ignored) {
			// This shouldn't happen since we're reading a string.
			throw new RuntimeException(ignored);
		}

		return manifestOutput.toString();
	}
}