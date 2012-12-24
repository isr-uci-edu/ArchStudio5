package org.archstudio.graphlayout.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AliasTable {
	protected Map<String, String> truenameToAlias = null;
	protected Map<String, String> aliasToTruename = null;

	protected int counter = 0;

	public AliasTable() {
		truenameToAlias = Collections.synchronizedMap(new HashMap<String, String>());
		aliasToTruename = Collections.synchronizedMap(new HashMap<String, String>());
	}

	public String getAlias(String truename) {
		String alias = truenameToAlias.get(truename);
		if (alias == null) {
			alias = generateAlias();
			truenameToAlias.put(truename, alias);
			aliasToTruename.put(alias, truename);
		}
		return alias;
	}

	public String getTruename(String alias) {
		return aliasToTruename.get(alias);
	}

	public boolean hasAlias(String truename) {
		return truenameToAlias.containsKey(truename);
	}

	public boolean hasTruename(String alias) {
		return aliasToTruename.containsKey(alias);
	}

	public void clear() {
		truenameToAlias.clear();
		aliasToTruename.clear();
	}

	protected String generateAlias() {
		return "elt" + counter++;
	}
}
