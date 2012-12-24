package org.archstudio.archlight;

import java.util.List;

import com.google.common.collect.Lists;

public class ArchlightTest implements java.io.Serializable {
	private static final long serialVersionUID = -3225797932676933094L;
	protected String uid;
	protected String toolID;
	protected String category;
	protected String longDescription;

	public ArchlightTest(String uid, String toolID, String category, String longDescription) {
		super();
		this.uid = uid;
		this.toolID = toolID;
		this.category = category;
		this.longDescription = longDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getToolID() {
		return toolID;
	}

	public void setToolID(String toolID) {
		this.toolID = toolID;
	}

	public String getUID() {
		return uid;
	}

	public void setUID(String uid) {
		this.uid = uid;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("ArchlightTest[");
		sb.append("uid=").append(uid).append(",");
		sb.append("toolID=").append(toolID).append(",");
		sb.append("category=").append(category).append(",");
		sb.append("longDescription=").append(longDescription).append("];");
		return sb.toString();
	}

	public static String getLastCategoryPathComponent(String category) {
		String[] categoryPathComponents = getCategoryPathComponents(category);
		return categoryPathComponents[categoryPathComponents.length - 1];
	}

	public static String[] getCategoryPathComponents(String category) {
		List<String> pathComponentList = Lists.newArrayList();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < category.length(); i++) {
			char ch = category.charAt(i);
			switch (ch) {
			case '\\':
				if (i + 1 < category.length()) {
					char nextch = category.charAt(i + 1);
					if (nextch == '/') {
						sb.append('/');
						i++;
					}
					else {
						sb.append('\\');
					}
				}
				else {
					sb.append('\\');
				}
				break;
			case '/':
				String segment = sb.toString().trim();
				if (segment.length() > 0) {
					pathComponentList.add(segment);
				}
				sb.setLength(0);
				break;
			default:
				sb.append(ch);
				break;
			}
		}
		String segment = sb.toString().trim();
		if (segment.length() > 0) {
			pathComponentList.add(segment);
		}
		return pathComponentList.toArray(new String[0]);
	}

	public boolean equals(Object o) {
		if (!(o instanceof ArchlightTest)) {
			return false;
		}
		ArchlightTest otherTest = (ArchlightTest) o;
		return nulleq(uid, otherTest.uid) && nulleq(toolID, otherTest.toolID) && nulleq(category, otherTest.category)
				&& nulleq(longDescription, otherTest.longDescription);
	}

	public int hashCode() {
		if (uid == null) {
			return getClass().hashCode();
		}
		return uid.hashCode();
	}

	private static boolean nulleq(Object o1, Object o2) {
		if (o1 == null && o2 == null) {
			return true;
		}
		if (o1 == null && o2 != null) {
			return false;
		}
		if (o1 != null && o2 == null) {
			return false;
		}
		return o1.equals(o2);
	}
}
