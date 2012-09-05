package org.archstudio.ljm;

import java.io.IOException;
import java.util.Arrays;

public class LJMExample {

	static interface IExampleInterface {

		/**
		 * Note: All parameters and return values must be serializable
		 */

		public void setValue(Object value);

		public Object getValue();
	}

	public static void main(String[] args) throws IOException {

		/*
		 * On the server side, create your object and then deploy it.
		 */

		LJMServer server = new LJMServer();
		server.deploy("ANameForTheDeployedObject", new IExampleInterface() {

			Object value = null;

			public Object getValue() {
				System.err.println("Someone asked for my value, which is: " + value);
				return value;
			}

			public void setValue(Object value) {
				System.err.println("Someone set my value to: " + value);
				this.value = value;
			}
		});

		/*
		 * On the client side, get a reference to the deployed object
		 */
		IExampleInterface deployedObject = (IExampleInterface) LJMProxyFactory.createProxy("localhost",
				server.getPort(), "ANameForTheDeployedObject", new Class[] { IExampleInterface.class });

		System.err.println("The deployed object's value is " + deployedObject.getValue());

		System.err.println();
		deployedObject.setValue("First Value");
		System.err.println("The deployed object's value is " + deployedObject.getValue());

		System.err.println();
		deployedObject.setValue(512);
		System.err.println("The deployed object's value is " + deployedObject.getValue());

		System.err.println();
		deployedObject.setValue(Arrays.asList(new Object[] { "Value 1", "Value 2", "Value 3" }));
		System.err.println("The deployed object's value is " + deployedObject.getValue());

		/*
		 * Clean up the server
		 */
		server.destroy();
	}
}
