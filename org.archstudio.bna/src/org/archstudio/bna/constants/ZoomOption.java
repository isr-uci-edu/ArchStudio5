package org.archstudio.bna.constants;

/**
 * Preference option for zooming
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public enum ZoomOption {
	NONE("None"), // 
	WHEEL("Wheel"), // by mouse wheel
	SHIFT_WHEEL("Shift + Wheel"), // by shift + mouse wheel
	CTRL_WHEEL("Ctrl + Wheel");//by ctrl + mouse wheel

	/**
	 * Human readable lable of this option
	 */
	private String lable;

	/**
	 * The list of lable-value pairs
	 */
	private final static String[][] lableAndValues = createLabelAndValues();

	private ZoomOption(String lable) {
		this.lable = lable;
	}

	/**
	 * Returns the lable of this option
	 * 
	 * @return
	 */
	public String getLable() {
		return this.lable;
	}

	/**
	 * Returns a list of lable-value pairs. Such as { {"None", "NONE"}, {"Wheel", "WHEEL"}, {"Shift + Wheel",
	 * "SHIFT_WHEEL"}, {"Ctrl + Wheel", "CTRL_WHEEL"}}.
	 * 
	 * @see edu.uci.isr.archstudio4.comp.archipelago.prefs.ArchipelagoPreferencePanel
	 * @return
	 */
	public static String[][] getLabelAndValues() {
		return lableAndValues;
	}

	/**
	 * Creates a list of lable-value pairs.
	 * 
	 * @return
	 */
	private static String[][] createLabelAndValues() {
		String[][] lableAndValues = new String[ZoomOption.values().length][2];
		int index = 0;
		for (ZoomOption option : ZoomOption.values()) {
			//lable
			lableAndValues[index][0] = option.getLable();
			//value
			lableAndValues[index][1] = option.toString();
			index++;
		}
		return lableAndValues;
	}
}
