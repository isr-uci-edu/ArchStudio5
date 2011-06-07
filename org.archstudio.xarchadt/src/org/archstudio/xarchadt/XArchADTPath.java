package org.archstudio.xarchadt;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkPositionIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * An XArchADTPath is similar to an XML XPath but it works for xArch-based XML documents. An XArchADTPath can be
 * converted to and from a string easily with the functions in this class. It is useful for quickly assessing an
 * element's position within an XML document without having to call costly operations to walk around the XML tree.
 * <p>
 * An XArchADTPath is composed of segments, starting at the XML tree root and proceeding down the tree to the specified
 * element. Each segment has one of the following formats:
 * <p>
 * <i>tagName</i> (for elements where that is the only tag)
 * <p>
 * <i>tagName</i>:<i>tagIndex</i> Where tagIndex is the the index of that tag name within the list of all tags with the
 * same name. So, if there are five tags called ComponentInstance in the same place, the third one is
 * <code>componentInstance:2</code> (remember, indices are zero-based).
 * <p>
 * <i>tagName</i>:id=<i>tagID</i> Where tagID is the ID of the element, if the element has an ID attribute.
 * <p>
 * So, a sample XArchADTPath might be:
 * <p>
 * <code>xArch/ArchStructure:id=hello there/Component:5/Description</code>
 * <p>
 * Any slashes in the ID or tag name are escaped with a backslash; backslashes are also escaped as a double-backslash.
 */
public class XArchADTPath implements java.io.Serializable {

	private static final long serialVersionUID = -787271424572180965L;

	private static final String escape(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == ':') {
				sb.append("\\:");
			}
			else if (ch == '/') {
				sb.append("\\/");
			}
			else if (ch == '\\') {
				sb.append("\\\\");
			}
			else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	private static final void addSegment(int i, String s, String tagName, String tagAttribute, List<String> tagNames,
			List<Integer> tagIndexes, List<String> tagIDs) {
		if (tagName.length() == 0) {
			throw new IllegalArgumentException("Illegal XArchADTPath Specification; Zero Length Name at [" + i
					+ "] in " + s);
		}

		tagNames.add(tagName);
		if (tagAttribute == null) {
			tagIndexes.add(new Integer(-1));
			tagIDs.add(null);
		}
		else {
			if (tagAttribute.length() == 0) {
				throw new IllegalArgumentException("Illegal XArchADTPath Specification; Zero Length Annotation at ["
						+ i + "] in " + s);
			}

			if (tagAttribute.startsWith("id=")) {
				tagAttribute = tagAttribute.substring(3);
				tagIndexes.add(new Integer(-1));
				tagIDs.add(tagAttribute);
			}
			else {
				try {
					int index = Integer.parseInt(tagAttribute);
					tagIndexes.add(new Integer(index));
					tagIDs.add(null);
				}
				catch (NumberFormatException nfe) {
					throw new IllegalArgumentException("Illegal XArchADTPath Specification; Illegal Tagged Value at ["
							+ i + "] in " + s);
				}
			}
		}
	}

	private static final XArchADTPath parse(String s) {
		List<String> tagNames = new ArrayList<String>();
		List<String> tagElementNames = new ArrayList<String>();
		List<Integer> tagIndexes = new ArrayList<Integer>();
		List<String> tagIDs = new ArrayList<String>();

		//If mode = 0; we're parsing a tag name
		//If mode = 1; we're parsing a tag attribute
		int mode = 0;

		StringBuffer tokenBuf = new StringBuffer();

		String tagName = null;
		String tagAttribute = null;

		try {
			for (int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				if (ch == '\\') {
					i++;
					ch = s.charAt(i);
					tokenBuf.append(ch);
				}
				else {
					//It wasn't an escaped character, so it might
					//have been the delimiter
					if (ch == '/') {
						if (mode == 0) { //No tagged attribute
							tagName = tokenBuf.toString();
							tokenBuf.setLength(0);
							tagAttribute = null;
						}
						else if (mode == 1) { //we were parsing a tagged attribute
							tagAttribute = tokenBuf.toString();
							tokenBuf.setLength(0);
						}
						//This the end of the segment
						mode = 0; //mode = 0 now
						addSegment(i, s, tagName, tagAttribute, tagNames, tagIndexes, tagIDs);
						tagName = null;
						tagAttribute = null;
					}
					else if (ch == ':') {
						if (mode == 0) {
							tagName = tokenBuf.toString();
							tokenBuf.setLength(0);
							//Now parsing a tagged attribute
							mode = 1;
						}
						else if (mode == 1) {
							throw new IllegalArgumentException("Illegal XArchADTPath Specification; Illegal Colon at ["
									+ i + "] in " + s);
						}
					}
					else {
						tokenBuf.append(ch);
					}
				}
			}
			if (tokenBuf.length() > 0) {
				if (mode == 0) {
					tagName = tokenBuf.toString();
					tagAttribute = null;
					addSegment(s.length(), s, tagName, tagAttribute, tagNames, tagIndexes, tagIDs);
				}
				if (mode == 1) {
					tagAttribute = tokenBuf.toString();
					addSegment(s.length(), s, tagName, tagAttribute, tagNames, tagIndexes, tagIDs);
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Illegal XArchADTPath Specification; Unexpected end of path in " + s);
		}

		return new XArchADTPath(Collections.unmodifiableList(tagNames), Collections.unmodifiableList(tagElementNames),
				Collections.unmodifiableList(tagIndexes), Collections.unmodifiableList(tagIDs));
	}

	public static final ObjRef resolve(IXArchADTQuery xarch, ObjRef documentRootRef, XArchADTPath p) {
		String pathTagsString = p.toTagsOnlyString();
		if (!pathTagsString.startsWith("xADL/")) {
			return null;
		}

		ObjRef curNode = documentRootRef;
		outerLoop: for (int i = 0; i < p.getLength(); i++) {
			String tagName = p.getTagName(i);
			int tagIndex = p.getTagIndex(i);
			String tagID = p.getTagID(i);

			// It's not the very first one
			if (tagID != null) {
				try {
					ObjRef ref = (ObjRef) xarch.get(curNode, tagName);
					if (ref != null) {
						try {
							String objectID = (String) xarch.get(ref, "id");
							if (objectID != null) {
								if (objectID.equals(tagID)) {
									curNode = ref;
									continue;
								}
							}
						}
						catch (Exception e) {
						}
					}
				}
				catch (Exception e) {
				}
				try {
					for (ObjRef ref : xarch.getAll(curNode, tagName)) {
						try {
							String objectID = (String) xarch.get(ref, "id");
							if (objectID != null) {
								if (objectID.equals(tagID)) {
									curNode = ref;
									continue outerLoop;
								}
							}
						}
						catch (Exception e) {
						}
					}
				}
				catch (Exception e) {
				}
				// We fell off the tree;
				return null;
			}
			else {
				// Find by index
				if (tagIndex < 1) {
					try {
						ObjRef ref = (ObjRef) xarch.get(curNode, tagName);
						if (ref != null) {
							curNode = ref;
							continue;
						}
					}
					catch (Exception e) {
					}
					try {
						List<ObjRef> refs = xarch.getAll(curNode, tagName);
						if (refs != null && refs.size() > 0) {
							curNode = refs.get(0);
							continue;
						}
					}
					catch (Exception e) {
					}
					// Fell off the tree
					return null;
				}
				else {
					try {
						List<ObjRef> refs = xarch.getAll(curNode, tagName);
						if (refs != null && refs.size() > 0) {
							curNode = refs.get(tagIndex);
							continue;
						}
					}
					catch (Exception e) {
					}
					// Fell off the tree;
					return null;
				}
			}
		}

		return curNode;
	}

	private final List<String> tagNames;
	private final List<String> tagElementNames;
	private final List<Integer> tagIndexes;
	private final List<String> tagIDs;

	private volatile String stringString = null;
	private volatile String tagsOnlyString = null;
	private volatile String dumpString = null;
	private volatile List<String> readOnlyTagNames = null;

	public XArchADTPath(List<String> tagNames, List<String> tagElementNames, List<Integer> tagIndexes,
			List<String> tagIDs) {
		checkArgument(tagNames.size() == tagElementNames.size());
		checkArgument(tagElementNames.size() == tagIndexes.size());
		checkArgument(tagIndexes.size() == tagIDs.size());

		this.tagNames = Lists.newArrayList(tagNames);
		this.tagElementNames = Lists.newArrayList(tagElementNames);
		this.tagIndexes = Lists.newArrayList(tagIndexes);
		this.tagIDs = Lists.newArrayList(tagIDs);
	}

	XArchADTPath(XArchADTPath XArchADTPath) {
		this(XArchADTPath.tagNames, XArchADTPath.tagElementNames, XArchADTPath.tagIndexes, XArchADTPath.tagIDs);
	}

	/**
	 * Parse a stringified XArchADTPath into an XArchADTPath object.
	 * 
	 * @param s
	 *            Stringified XArchADTPath
	 * @exception IllegalArgumentException
	 *                if the string is invalid.
	 */
	public XArchADTPath(String s) {
		this(parse(s));
	}

	/**
	 * Get the length, in number of elements, of this XArchADTPath.
	 * 
	 * @return XArchADTPath's length.
	 */
	public int getLength() {
		return tagNames.size();
	}

	/**
	 * Get the name of the tag at the given segment.
	 * 
	 * @param index
	 *            Segment number
	 * @return name of tag at that segment
	 */
	public String getTagName(int index) {
		checkPositionIndex(index, getLength());
		return tagNames.get(index);
	}

	/**
	 * Get the index of the tag at the given segment.
	 * 
	 * @param index
	 *            Segment number
	 * @return index of tag at that segment, or -1 if the index is not applicable
	 */
	public int getTagIndex(int index) {
		checkPositionIndex(index, getLength());
		return tagIndexes.get(index);
	}

	/**
	 * Get the ID of the tag at the given segment.
	 * 
	 * @param index
	 *            Segment number
	 * @return ID of tag at that segment, or null if the index has no ID
	 */
	public String getTagID(int index) {
		checkPositionIndex(index, getLength());
		return tagIDs.get(index);
	}

	public XArchADTPath subpath(int fromIndex) {
		checkPositionIndex(fromIndex, getLength());
		return subpath(fromIndex, getLength());
	}

	public XArchADTPath subpath(int fromIndex, int toIndex) {
		checkPositionIndex(fromIndex, getLength());
		checkPositionIndex(toIndex, getLength());
		if (fromIndex == 0 && toIndex == getLength()) {
			return this;
		}
		return new XArchADTPath(tagNames.subList(fromIndex, toIndex), tagElementNames.subList(fromIndex, toIndex),
				tagIndexes.subList(fromIndex, toIndex), tagIDs.subList(fromIndex, toIndex));
	}

	/**
	 * Converts this XArchADTPath into a string.
	 * 
	 * @return String representation of this XArchADTPath
	 */
	@Override
	public String toString() {
		if (stringString == null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < getLength(); i++) {
				String tagName = tagNames.get(i);
				int tagIndex = tagIndexes.get(i);
				String tagID = tagIDs.get(i);
				if (i > 0) {
					sb.append("/");
				}
				sb.append(escape(tagName));
				if (tagID != null) {
					sb.append(":id=" + escape(tagID));
				}
				else if (tagIndex != -1) {
					sb.append(":" + tagIndex);
				}
			}
			stringString = sb.toString();
		}
		return stringString;
	}

	/**
	 * Converts this XArchADTPath into a string, stripping all information except tag names.
	 * 
	 * @return String representation of this XArchADTPath with tag names only.
	 */
	public String toTagsOnlyString() {
		if (tagsOnlyString == null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < getLength(); i++) {
				String tagName = tagNames.get(i);
				if (i > 0) {
					sb.append("/");
				}
				sb.append(escape(tagName));
			}
			tagsOnlyString = sb.toString();
		}
		return tagsOnlyString;
	}

	/**
	 * Converts this XArchADTPath into a debugging string.
	 * 
	 * @return String representation of this XArchADTPath, useful for debugging.
	 */
	public String toDumpString() {
		if (dumpString == null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < getLength(); i++) {
				String tagName = tagNames.get(i);
				int tagIndex = tagIndexes.get(i);
				String tagID = tagIDs.get(i);
				sb.append(tagName);
				sb.append(",");
				if (tagID != null) {
					sb.append("id=" + tagID);
					sb.append(",");
				}
				else if (tagIndex != -1) {
					sb.append("[");
					sb.append(tagIndex);
					sb.append("]");
				}
				sb.append(System.getProperty("line.separator"));
			}
			dumpString = sb.toString();
		}
		return dumpString;
	}

	public List<String> getTagNames() {
		if (readOnlyTagNames == null) {
			readOnlyTagNames = Collections.unmodifiableList(tagNames);
		}
		return readOnlyTagNames;
	}
}
