package org.archstudio.prolog.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.List;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.ListTerm;
import org.archstudio.prolog.term.Rule;
import org.archstudio.prolog.term.StringTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.archstudio.prolog.xtext.PrologStandaloneSetupGenerated;
import org.archstudio.prolog.xtext.prolog.Clause;
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
				throw new ParseException(resource.getErrors().get(0).getMessage() + " at " + l);
			}
			return (Program) resource.getContents().get(0);
		}
		catch (Throwable e) {
			throw new ParseException(e);
		}
	}

	public static synchronized List<Term> parseTerms(ProofContext proofContext, String input) throws ParseException {
		Program program = parse(input);
		List<Term> terms = Lists.newArrayList();
		for (Clause clause : program.getClauses()) {
			terms.add(parseClause(proofContext, clause));
		}
		return terms;
	}

	private static Term parseClause(ProofContext proofContext, Clause c) throws ParseException {
		if (c.getPredicates().size() == 1) {
			return parseExpression(proofContext, c.getPredicates().get(0));
		}
		List<Term> predicates = Lists.newArrayList();
		for (Expression predicate : c.getPredicates()) {
			predicates.add(parseExpression(proofContext, predicate));
		}
		return new Rule((ComplexTerm) predicates.get(0), predicates.subList(1, predicates.size()));
	}

	private static Term parseExpression(ProofContext proofContext, Expression e) throws ParseException {
		if (e.isList()) {
			Term tail = new ListTerm(null, null);
			if (e.getTail() != null) {
				tail = parseExpression(proofContext, e.getTail());
			}
			if (e.getHead() != null) {
				for (Expression e2 : Lists.reverse(e.getHead())) {
					Term head = parseExpression(proofContext, e2);
					tail = new ListTerm(head, tail);
				}
			}
			return tail;
		}
		Term t = null;
		if (e.getString() != null) {
			t = new StringTerm(e.getString());
			if (e.getExps().size() == 0) {
				return t;
			}
		}
		else if (e.getVariable() != null) {
			t = new VariableTerm(e.getVariable());
			if (e.getExps().size() == 0) {
				return t;
			}
		}
		else if (e.getNumber() != null) {
			t = new ConstantTerm(new BigDecimal(e.getNumber()));
			if (e.getExps().size() == 0) {
				return t;
			}
		}
		else if (e.isComplex() || e.getOps().size() == 1 && e.getExps().size() == 1) {
			List<Term> terms = Lists.newArrayList();
			for (Expression e2 : e.getExps()) {
				terms.add(parseExpression(proofContext, e2));
			}
			String name = e.getOps().get(0);
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
			return new ComplexTerm(name, terms);
		}
		else if (e.getOps().size() == 1) {
			t = new ConstantTerm(e.getOps().get(0));
			if (e.getExps().size() == 0) {
				return t;
			}
		}
		else if (t == null && e.getExps().size() == 1) {
			return parseExpression(proofContext, e.getExps().get(0));
		}
		else {
			throw new ParseException();
		}
		List<Term> terms = Lists.newArrayList(t);
		for (Expression e2 : e.getExps()) {
			terms.add(parseExpression(proofContext, e2));
		}
		for (int i = 0; i < e.getOps().size(); i++) {
			String name = e.getOps().get(i);
			Class<Operation> operationClass = proofContext.getOperation(name);
			if (operationClass != null) {
				try {
					Constructor<Operation> c = operationClass.getConstructor(String.class, List.class);
					t = c.newInstance(name, Lists.newArrayList(t, terms.get(i + 1)));
				}
				catch (Throwable exc) {
					throw new ParseException(exc);
				}
			}
			else {
				t = new ComplexTerm(name, terms);
			}
		}
		return t;
	}
}
