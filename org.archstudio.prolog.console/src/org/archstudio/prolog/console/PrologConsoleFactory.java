package org.archstudio.prolog.console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.op.iso.Neck;
import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsolePageParticipant;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleInputStream;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.ui.part.IPageBookViewPage;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PrologConsoleFactory implements IConsoleFactory {

	public static final class StopExecutionAction extends Action {

		private final IConsole console;

		public StopExecutionAction(IConsole console) {
			super("Stop Execution", ImageDescriptor.createFromImage(PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_ELCL_STOP)));
			this.console = console;
		}

		@Override
		public void run() {
			((ProofContext) ((IOConsole) console).getAttribute(ProofContext.class.getName())).setCancelled(true);
		}
	}

	public static final class PageParticipant implements IConsolePageParticipant {

		private StopExecutionAction stopExecutionAction;

		@Override
		public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
			return null;
		}

		@Override
		public void init(IPageBookViewPage page, IConsole console) {
			stopExecutionAction = new StopExecutionAction(console);

			IToolBarManager manager = page.getSite().getActionBars().getToolBarManager();
			manager.appendToGroup(IConsoleConstants.LAUNCH_GROUP, stopExecutionAction);
		}

		@Override
		public void dispose() {
		}

		@Override
		public void activated() {
		}

		@Override
		public void deactivated() {
		}

	}

	@Override
	public void openConsole() {
		openConsole(null);
	}

	public static void openConsole(ProofContext optionalProofContext) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (IConsole console : existing) {
			if (PrologConsoleFactory.class.getName().equals(console.getType())) {
				conMan.removeConsoles(new IConsole[] { console });
			}
		}
		// create a new one 
		IOConsole console = new IOConsole("Prolog", PrologConsoleFactory.class.getName(), null, true);
		conMan.addConsoles(new IConsole[] { console });
		hookUpProlog(console, optionalProofContext);
		console.activate();
	}

	public static void hookUpProlog(final IOConsole console, ProofContext optionalProofContext) {
		final IOConsoleInputStream in = console.getInputStream();
		in.setColor(Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN));
		final BufferedReader inbr = new BufferedReader(new InputStreamReader(in));

		final IOConsoleOutputStream prompt = console.newOutputStream();
		prompt.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLUE));
		final PrintWriter promptpw = new PrintWriter(prompt, true);

		final IOConsoleOutputStream out = console.newOutputStream();
		out.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		final PrintWriter outpw = new PrintWriter(out, true);

		final IOConsoleOutputStream err = console.newOutputStream();
		err.setColor(Display.getDefault().getSystemColor(SWT.COLOR_RED));
		final PrintWriter errpw = new PrintWriter(err, true);

		final ProofContext proofContext = optionalProofContext != null ? optionalProofContext : new ProofContext();
		final UnificationEngine unificationEngine = new MostGeneralUnifierEngine();

		console.setAttribute(ProofContext.class.getName(), proofContext);

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {

				outpw.println("Welcome. Use :- to switch to INPUT mode and ?- to switch to QUERY mode.");
				proofContext.setOutput(new BufferedWriter(new OutputStreamWriter(out)));

				boolean inQueryMode = true;

				MAIN: while (true) {
					try {
						if (inQueryMode) {

							promptpw.print("[QUERY MODE] ?- ");
							promptpw.flush();

							String command = "";
							while (true) {
								String line = inbr.readLine().trim();
								command += "\n" + line;
								if (":-".equals(command.trim())) {
									inQueryMode = false;
									continue MAIN;
								}
								if (command.endsWith(".")) {
									break;
								}
							}

							proofContext.setCancelled(false);
							for (Term t : PrologParser.parseTerms(proofContext, command)) {
								if (!(t instanceof Executable)) {
									errpw.println("Not executable: ?- " + command);
									continue;
								}
								int count = 1;
								boolean firstResult = true;
								for (Map<VariableTerm, Term> result : ((Executable) t).execute(proofContext,
										unificationEngine, t, Maps.<VariableTerm, Term> newHashMap())) {
									if (!firstResult) {
										outpw.println(";");
									}
									firstResult = false;
									if (result.isEmpty()) {
										outpw.print("true");
									}
									else {
										boolean firstVar = true;
										String prefix = "" + count++ + ". ";
										for (Entry<VariableTerm, Term> var : SystemUtils.sortedByKey(result.entrySet())) {
											if (var.getKey().getName().startsWith("_G")) {
												continue;
											}
											if (!firstVar) {
												outpw.println();
												outpw.print(Strings.repeat(" ", prefix.length()));
											}
											else {
												outpw.print(prefix);
											}
											firstVar = false;
											outpw.print(var.getKey() + " = " + var.getValue());
										}
										outpw.flush();
									}
								}
								if (firstResult) {
									outpw.print("false");
								}
								outpw.println(".");
								outpw.flush();
								if (proofContext.isCancelled()) {
									errpw.println("Execution stopped.");
								}
							}
						}
						else {

							promptpw.print("[INPUT MODE] > ");
							promptpw.flush();

							String command = "";
							while (true) {
								String line = inbr.readLine().trim();
								command += "\n" + line;
								if ("?-".equals(command.trim())) {
									inQueryMode = true;
									continue MAIN;
								}
								if (command.endsWith(".")) {
									break;
								}
							}

							proofContext.setCancelled(false);
							for (Term t : PrologParser.parseTerms(proofContext, command)) {
								if (!(t instanceof ComplexTerm || t instanceof Neck)) {
									errpw.println("Expecting a complex term or rule: " + command);
									continue;
								}
								proofContext.add(Lists.newArrayList((ComplexTerm) t));
							}
							continue;
						}
					}
					catch (IOException e) {
						// console window was closed
						proofContext.setOutput(new BufferedWriter(new OutputStreamWriter(System.out)));
						return;
					}
					catch (Exception e) {
						e.printStackTrace(errpw);
					}
				}
			}
		});
		t.setName("Prolog Console");
		t.setDaemon(true);
		t.start();
	}
}