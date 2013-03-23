package org.archstudio.prolog.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.op.iso.Conjunction;
import org.archstudio.prolog.op.iso.ListTerm;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.StringTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.archstudio.prolog.xtext.PrologStandaloneSetupGenerated;
import org.archstudio.prolog.xtext.prolog.Expression;
import org.archstudio.prolog.xtext.prolog.Program;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.diagnostics.AbstractDiagnostic;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.common.collect.Lists;
import com.google.inject.Injector;

public class PrologParser {

	private static Injector injector = null;

	private static Program parse(String input) throws ParseException {
		try {
			if (injector == null) {
				injector = new PrologStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
			}
			XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
			resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
			Resource resource = resourceSet.createResource(URI.createURI("null:/prolog.pl"));
			InputStream in = new ByteArrayInputStream(input.getBytes());
			resource.load(in, resourceSet.getLoadOptions());
			if (resource.getErrors().size() > 0) {
				Diagnostic d = resource.getErrors().get(0);
				String l = d.getLocation();
				if (l == null) {
					try {
						l = "line and column: " + d.getLine() + "," + d.getColumn();
					}
					catch (UnsupportedOperationException e) {
						if (d instanceof AbstractDiagnostic) {
							l = "offset: " + ((AbstractDiagnostic) d).getOffset();
						}
					}
				}
				throw new ParseException(resource.getErrors().get(0).getMessage() + " at " + l + " for: " + input);
			}
			return (Program) resource.getContents().get(0);
		}
		catch (Throwable e) {
			throw new ParseException("Parse exception for: " + input, e);
		}
	}

	public static synchronized List<Term> parseTerms(ProofContext proofContext, String input) throws ParseException {
		List<Term> terms = Lists.newArrayList();
		for (Expression exp : parse(input).getExps()) {
			terms.add(parseExpression(proofContext, exp));
		}
		return terms;
	}

	private static Term parseExpression(ProofContext proofContext, Expression e) throws ParseException {
		boolean isAtom = e.getOps().size() == 0 && e.getExps().size() == 0 && !e.isList();
		Term t = null;
		if (e.getNumber() != null) {
			try {
				t = new ConstantTerm(new BigInteger(e.getNumber()));
			}
			catch (Exception e2) {
				t = new ConstantTerm(new BigDecimal(e.getNumber()));
			}
			if (isAtom) {
				return t;
			}
		}
		else if (e.getString() != null) {
			t = new StringTerm(e.getString());
			if (isAtom) {
				return t;
			}
		}
		else if (e.getVariable() != null) {
			t = new VariableTerm(e.getVariable());
			if (isAtom) {
				return t;
			}
		}
		else if (e.getOps().size() == 1 && e.getExps().size() == 0) {
			return new ConstantTerm(e.getOps().get(0));
		}
		else if (e.getOps().size() == 0 && e.getExps().size() == 1) {
			// must be an Expression*fx rule in Prolog.xtext
			return parseExpression(proofContext, e.getExps().get(0));
		}
		else if (e.isList()) {
			if (e.getTails().size() == 0) {
				t = new ListTerm();
			}
			else {
				t = parseExpression(proofContext, e.getTails().get(0));
			}
			for (Expression e2 : Lists.reverse(e.getHeads())) {
				Term t2 = parseExpression(proofContext, e2);
				if (t2 instanceof Conjunction) {
					for (Term t3 : Lists.reverse(((Conjunction) t2).getTerms())) {
						t = new ListTerm(t3, t);
					}
				}
				else {
					t = new ListTerm(t2, t);
				}
			}
		}

		List<Term> terms = Lists.newArrayList();
		if (t != null) {
			terms.add(t);
		}
		for (Expression e2 : e.getExps()) {
			terms.add(parseExpression(proofContext, e2));
		}
		if (e.isPrefix()) {
			if (terms.size() > 0) {
				if (terms.get(0) instanceof Conjunction) {
					terms = ((Conjunction) terms.get(0)).getTerms();
				}
			}
		}

		String name = e.getOps().size() > 0 ? e.getOps().get(0) : null;
		if (".".equals(name)) {
			return new ListTerm(terms.get(0), terms.get(1));
		}
		Class<Operation> operationClass = proofContext.getOperation(name);
		if (operationClass != null) {
			try {
				Constructor<Operation> c = operationClass.getConstructor(String.class, List.class);
				return c.newInstance(name, terms);
			}
			catch (Throwable exc) {
				throw new ParseException(exc);
			}
		}

		if (name == null) {
			return terms.get(0);
		}
		return new ComplexTerm(name, terms);
	}
}
