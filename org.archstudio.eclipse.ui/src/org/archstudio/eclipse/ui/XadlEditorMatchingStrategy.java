package org.archstudio.eclipse.ui;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;

public class XadlEditorMatchingStrategy implements org.eclipse.ui.IEditorMatchingStrategy {

	public boolean matches(IEditorReference editorRef, IEditorInput input) {

		Reader r = null;

		try {
			if (input instanceof IFileEditorInput) {
				IFileEditorInput fileInput = (IFileEditorInput) input;
				IFile f = fileInput.getFile();
				if (!f.getName().toLowerCase().endsWith(".xml")) {
					return false;
				}
				r = new InputStreamReader(f.getContents());
			}
			else if (input instanceof IPathEditorInput) {
				java.io.File f = ((IPathEditorInput) input).getPath().toFile();
				r = new FileReader(f);
			}
			if (r != null) {
				while (true) {
					String s = readTag(r);
					if (s == null) {
						return false;
					}
					if (!s.startsWith("<?")) {
						StringBuffer sb = new StringBuffer();
						int i = 1;
						while (true) {
							char ch = s.charAt(i++);
							if (Character.isWhitespace(ch) || ch == '>') {
								break;
							}
							else {
								sb.append(ch);
							}
						}

						//We have the tag name (including namespace prefix) in sb
						String tagName = sb.toString();
						//Cut off namespace prefix if any
						tagName = tagName.substring(tagName.indexOf(":") + 1);
						if (tagName.equals("xADL")) {
							return true;
						}
						else {
							return false;
						}
					}
				}
			}
		}
		catch (Exception e) {
			return false;
		}
		finally {
			if (r != null) {
				try {
					r.close();
				}
				catch (Exception e) {
				}
			}
		}
		return false;
	}

	private static final int BEFORE_TAG = 0;
	private static final int IN_TAG = 1;

	public String readTag(Reader r) throws IOException {
		StringBuffer sb = new StringBuffer();
		int state = BEFORE_TAG;

		while (true) {
			int ich = r.read();
			if (ich == -1) {
				return null;
			}
			char ch = (char) ich;
			if (state == BEFORE_TAG) {
				if (ch == '<') {
					state = IN_TAG;
					sb.append(ch);
				}
			}
			else if (state == IN_TAG) {
				sb.append(ch);
				if (ch == '>') {
					return sb.toString();
				}
			}
		}
	}
}
