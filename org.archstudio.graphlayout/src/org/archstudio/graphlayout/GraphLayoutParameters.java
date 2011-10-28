package org.archstudio.graphlayout;

import java.util.HashMap;
import java.util.Map;

public class GraphLayoutParameters implements java.io.Serializable {

	private static final long serialVersionUID = 7404468772149560496L;

	//Relative factors should be close to 1.0, but if you want connectors to be
	//twice as wide as they are tall, set relativeConnectorHeight to 1.0
	//and relativeConnectorWidth to 2.0. Dot seems to work best if the smallest
	//dimension given is 1.0d

	//The scale factor tells how many units '1.0' should be.

	protected double scale = 100.0d;

	protected double relativeComponentWidth = 1.0d;
	protected double relativeComponentHeight = 1.0d;

	protected double relativeConnectorWidth = 1.0d;
	protected double relativeConnectorHeight = 1.0d;

	protected Map<String, Object> otherProperties;

	public GraphLayoutParameters() {
		otherProperties = new HashMap<String, Object>();
	}

	public void setProperty(String name, Object value) {
		otherProperties.put(name, value);
	}

	public Object getProperty(String name) {
		return otherProperties.get(name);
	}

	public void removeProperty(String name) {
		otherProperties.remove(name);
	}

	public double getRelativeComponentHeight() {
		return relativeComponentHeight;
	}

	public double getRelativeComponentWidth() {
		return relativeComponentWidth;
	}

	public double getRelativeConnectorHeight() {
		return relativeConnectorHeight;
	}

	public double getRelativeConnectorWidth() {
		return relativeConnectorWidth;
	}

	public double getScale() {
		return scale;
	}

	public void setRelativeComponentHeight(double d) {
		relativeComponentHeight = d;
	}

	public void setRelativeComponentWidth(double d) {
		relativeComponentWidth = d;
	}

	public void setRelativeConnectorHeight(double d) {
		relativeConnectorHeight = d;
	}

	public void setRelativeConnectorWidth(double d) {
		relativeConnectorWidth = d;
	}

	public void setScale(double d) {
		scale = d;
	}

}
