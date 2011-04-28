package org.archstudio.myxgen.jet.ui.console;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * A helper to print text to the console of Myx code generator.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class ConsoleDisplayMgr {

	private static MessageConsole console = null;

	/** title of the console window */
	private final static String CONSOLE_TITLE = "Myx Code Generator";

	/**
	 * Prints the specified message to the console window, followed by a line separator string.
	 * 
	 * @param message
	 * @param swtColor
	 *            SWT.COLOR_*
	 */
	public static void print(String message, int swtColor) {
		createConsoleIfNull();
		showConsoleView();

		MessageConsoleStream mcs = console.newMessageStream();
		Display display = Display.getCurrent();
		if (display != null) {
			mcs.setColor(display.getSystemColor(swtColor));
		}
		mcs.print(message);
	}

	/**
	 * Prints the specified message to the console window, followed by a line separator string.
	 * 
	 * @param message
	 */
	public static void println(String message) {
		createConsoleIfNull();
		showConsoleView();

		MessageConsoleStream mcs = console.newMessageStream();
		mcs.println(message);
	}

	/**
	 * Prints the specified message to the console window, followed by a line separator string.
	 * 
	 * @param message
	 * @param swtColor
	 *            SWT.COLOR_*
	 */
	public static void println(String message, int swtColor) {
		createConsoleIfNull();
		showConsoleView();

		MessageConsoleStream mcs = console.newMessageStream();
		mcs.setColor(Display.getCurrent().getSystemColor(swtColor));
		mcs.println(message);
	}

	/**
	 * Prints the specified message to the console window, followed by a line separator string
	 * 
	 * @param errorMessage
	 */
	public static void printlnError(String errorMessage) {
		createConsoleIfNull();
		showConsoleView();

		MessageConsoleStream mcs = console.newMessageStream();
		mcs.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		mcs.println(errorMessage);
	}

	/**
	 * Creates a MessageConsole if it doesn't exist.
	 * 
	 * @return
	 */
	private static MessageConsole createConsoleIfNull() {
		if (console == null) {
			console = new MessageConsole(CONSOLE_TITLE, null);
			ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { console });
		}
		console.activate();
		return console;
	}

	/**
	 * Shows a console view.
	 */
	private static void showConsoleView() {

		try {
			IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (activeWorkbenchWindow != null) {
				IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
				if (activePage != null) {
					activePage.showView(IConsoleConstants.ID_CONSOLE_VIEW, null, IWorkbenchPage.VIEW_VISIBLE);
				}
			}

		}
		catch (PartInitException partEx) {
			partEx.printStackTrace();
		}
	}
}
