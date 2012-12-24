package org.archstudio.eclipse.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescriber;
import org.eclipse.core.runtime.content.IContentDescription;

public class XadlRootElementContentDescriber implements IContentDescriber {

	public int describe(InputStream contents, IContentDescription description) throws IOException {
		Reader r = null;
		r = new InputStreamReader(contents);
		while (true) {
			String s = readTag(r);
			if (s == null) {
				return IContentDescriber.INVALID;
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
				if (tagName.equals("xArch")) {
					return VALID;
				}
				else {
					return INVALID;
				}
			}
		}
	}

	public QualifiedName[] getSupportedOptions() {
		return new QualifiedName[0];
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
