package org.archstudio.prolog.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.ListTerm;
import org.archstudio.prolog.term.StringTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.archstudio.prolog.xtext.PrologStandaloneSetupGenerated;
import org.archstudio.prolog.xtext.prolog.AtomExpression;
import org.archstudio.prolog.xtext.prolog.Expression;
import org.archstudio.prolog.xtext.prolog.ListExpression;
import org.archstudio.prolog.xtext.prolog.Model;
import org.archstudio.prolog.xtext.prolog.NumberExpression;
import org.archstudio.prolog.xtext.prolog.StringExpression;
import org.archstudio.prolog.xtext.prolog.UnaryExpression;
import org.archstudio.prolog.xtext.prolog.VariableExpression;
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

	private static Model parse(String input) throws ParseException {
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
			return (Model) resource.getContents().get(0);
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
		if (e == null) {
			return null;
		}
		if (e instanceof UnaryExpression) {
			UnaryExpression c = (UnaryExpression) e;
			Term right = parseExpression(proofContext, c.getRight());
			if (c.getOp() != null) {
				right = proofContext.create(c.getOp(), Lists.newArrayList(right));
			}
			return right;
		}
		if (e instanceof AtomExpression) {
			AtomExpression c = (AtomExpression) e;
			String op = c.getAtom();
			List<Term> terms = toList(parseExpression(proofContext, c.getTerms()));
			return proofContext.create(op, terms);
		}
		if (e instanceof VariableExpression) {
			VariableExpression c = (VariableExpression) e;
			return new VariableTerm(c.getName());
		}
		if (e instanceof StringExpression) {
			StringExpression c = (StringExpression) e;
			return new StringTerm(c.getValue());
		}
		if (e instanceof NumberExpression) {
			NumberExpression c = (NumberExpression) e;
			String value = c.getValue().toLowerCase();
			Number n;
			if (value.indexOf('.') >= 0 || value.indexOf('e') >= 0) {
				n = new BigDecimal(value);
			}
			else {
				n = new BigInteger(value);
			}
			return new ConstantTerm(n);
		}
		if (e instanceof ListExpression) {
			ListExpression c = (ListExpression) e;
			Term list = new ListTerm();
			if (c.getTail() != null) {
				list = parseExpression(proofContext, c.getTail());
			}
			for (Term head : Lists.reverse(toList(parseExpression(proofContext, c.getHead())))) {
				list = new ListTerm(head, list);
			}
			return list;
		}
		Term left = parseExpression(proofContext, e.getLeft());
		Term right = parseExpression(proofContext, e.getRight());
		return proofContext.create(e.getOp(), Lists.newArrayList(left, right));
	}

	private static List<Term> toList(Term t) {
		if (t instanceof ComplexTerm) {
			ComplexTerm c = (ComplexTerm) t;
			if (",".equals(c.getFunctor())) {
				List<Term> list = Lists.newArrayList(c.getTerm(0));
				list.addAll(toList(c.getTerm(1)));
				return list;
			}
		}
		if (t != null) {
			return Lists.newArrayList(t);
		}
		return Lists.newArrayList();
	}
}
