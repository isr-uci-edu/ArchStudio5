package org.archstudio.swtutils;

import org.eclipse.swt.graphics.RGB;

public class ColorScheme {
	protected String name;
	protected RGB[][] colorSets;

	public ColorScheme(String name, RGB[][] colorSets) {
		this.name = name;
		this.colorSets = colorSets;
	}

	public RGB getRGB(int set, int variant) {
		RGB[] colorSet = colorSets[set];
		return colorSet[variant];
	}

	public int getNumSets() {
		return colorSets.length;
	}

	public int getNumVariants() {
		return colorSets[0].length;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
