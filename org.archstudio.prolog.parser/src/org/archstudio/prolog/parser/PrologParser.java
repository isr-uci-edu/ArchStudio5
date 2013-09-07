package org.archstudio.prolog.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.op.iso.Conjunction;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.ListTerm;
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
							int offset = ((AbstractDiagnostic) d).getOffset();
							input = input.substring(0, offset) + "<<<HERE>>>" + input.substring(offset);
							l = "offset: " + offset;
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
		if (e.getOps().isEmpty() && e.getExps().size() == 1) {
			return parseExpression(proofContext, e.getExps().get(0));
		}
		if (e.isParen()) {
			return parseExpression(proofContext, e.getSub());
		}
		if (e.getNumber() != null) {
			try {
				return new ConstantTerm(new BigInteger(e.getNumber()));
			}
			catch (Exception e2) {
				return new ConstantTerm(new BigDecimal(e.getNumber()));
			}
		}
		if (e.getString() != null) {
			return new StringTerm(e.getString());
		}
		if (e.getVariable() != null) {
			return new VariableTerm(e.getVariable());
		}
		if (e.isList()) {
			Term list;
			if (e.getTail() != null) {
				list = parseExpression(proofContext, e.getTail());
			}
			else {
				list = new ListTerm();
			}
			if (e.getHead() != null) {
				Term head = parseExpression(proofContext, e.getHead());
				if (head instanceof Conjunction) {
					for (Term t : Lists.reverse(((Conjunction) head).getTerms())) {
						list = new ListTerm(t, list);
					}
				}
				else {
					list = new ListTerm(head, list);
				}
			}
			return list;
		}

		String name;
		List<Term> terms = null;
		if (e.getAtom() != null) {
			name = e.getAtom();
			if (e.isPrefix()) {
				Term atomTerms = parseExpression(proofContext, e.getTerms());
				if (atomTerms instanceof Conjunction) {
					terms = ((Conjunction) atomTerms).getTerms();
				}
				else {
					terms = Lists.newArrayList(atomTerms);
				}
			}
		}
		else {
			name = e.getOps().get(0);
			terms = Lists.newArrayList();
			for (Expression exp : e.getExps()) {
				terms.add(parseExpression(proofContext, exp));
			}
		}
		return proofContext.create(name, terms);
	}
}
