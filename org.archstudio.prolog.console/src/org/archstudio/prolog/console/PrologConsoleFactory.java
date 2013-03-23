package org.archstudio.prolog.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.archstudio.prolog.engine.MostGeneralUnifierEngine;
import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.engine.UnificationEngine;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.op.iso.Neck;
import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleInputStream;
import org.eclipse.ui.console.IOConsoleOutputStream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PrologConsoleFactory implements IConsoleFactory {

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

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {

				outpw.println("Welcome. Use :- to switch to INPUT mode and ?- to switch to QUERY mode.");

				boolean inQueryMode = true;

				while (true) {
					try {
						if (inQueryMode) {

							promptpw.print("[QUERY MODE] ?- ");
							promptpw.flush();

							String command = inbr.readLine().trim();
							if ("?-".equals(command)) {
								inQueryMode = true;
								continue;
							}
							if (":-".equals(command)) {
								inQueryMode = false;
								continue;
							}

							for (Term t : PrologParser.parseTerms(proofContext, command)) {
								if (!(t instanceof Operation)) {
									errpw.println("Not executable: ?- " + command);
									continue;
								}
								boolean firstResult = true;
								for (Map<VariableTerm, Term> result : ((Operation) t).execute(proofContext,
										unificationEngine, t, Maps.<VariableTerm, Term> newHashMap())) {
									if (!firstResult) {
										outpw.println(";");
									}
									firstResult = false;
									boolean firstVar = true;
									for (Entry<VariableTerm, Term> var : SystemUtils.sortedByKey(result.entrySet())) {
										if (!firstVar) {
											outpw.println();
										}
										firstVar = false;
										outpw.print(var.getKey() + " = " + var.getValue());
									}
								}
								outpw.println(".");
								outpw.flush();
							}
						}
						else {

							promptpw.print("[INPUT MODE] > ");
							promptpw.flush();

							String command = inbr.readLine().trim();
							if ("?-".equals(command)) {
								inQueryMode = true;
								continue;
							}
							if (":-".equals(command)) {
								inQueryMode = false;
								continue;
							}

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