package org.archstudio.myx.conn;

public interface IEventRegulatorValve {

	/**
	 * An closed valve queues events in the regulator until it is reopened.
	 */
	public void closeValve();

	/**
	 * An open valve allows events to flow through the regulator.
	 */
	public void openValve();

	public boolean isValveOpen();
}
