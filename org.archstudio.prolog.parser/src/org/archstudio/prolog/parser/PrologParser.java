package org.archstudio.prolog.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.List;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.Rule;
import org.archstudio.prolog.term.StringTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.archstudio.prolog.xtext.PrologStandaloneSetupGenerated;
import org.archstudio.prolog.xtext.prolog.Predicate;
import org.archstudio.prolog.xtext.prolog.Program;
import org.archstudio.prolog.xtext.prolog.SingleClause;
import org.archstudio.prolog.xtext.prolog.SingleTerm;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
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
				throw new ParseException(resource.getErrors().get(0).toString());
			}
			return (Program) resource.getContents().get(0);
		}
		catch (Throwable e) {
			throw new ParseException(e);
		}
	}

	public static synchronized List<ComplexTerm> parseTerms(ProofContext proofContext, String input)
			throws ParseException {
		Program program = parse(input);
		List<ComplexTerm> terms = Lists.newArrayList();
		for (SingleClause clause : program.getClauses()) {
			terms.add(parseClause(proofContext, clause));
		}
		return terms;
	}

	private static ComplexTerm parseClause(ProofContext proofContext, SingleClause c) throws ParseException {
		if (c.getPredicates().size() == 1) {
			return parsePredicate(proofContext, c.getPredicates().get(0));
		}
		List<Term> predicates = Lists.newArrayList();
		for (Predicate p : c.getPredicates()) {
			predicates.add(parsePredicate(proofContext, p));
		}
		return new Rule((ComplexTerm) predicates.get(0), predicates.subList(1, predicates.size()));
	}

	private static ComplexTerm parsePredicate(ProofContext proofContext, Predicate p) throws ParseException {
		String name = p.getFunctor().getAtom() != null ? p.getFunctor().getAtom().toString() : p.getFunctor()
				.getOperator().toString();
		if (name == null) {
			throw new ParseException("Require atom or operation");
		}
		List<Term> terms = Lists.newArrayList();
		for (SingleTerm t : p.getTerms()) {
			terms.add(parseSingleTerm(t));
		}
		Class<Operation> operationClass = proofContext.getOperation(name);
		if (operationClass != null) {
			try {
				Constructor<Operation> c = operationClass.getConstructor(String.class, List.class);
				return c.newInstance(name, terms);
			}
			catch (Throwable e) {
				throw new ParseException(e);
			}
		}
		return new ComplexTerm(name, terms);
	}

	private static Term parseSingleTerm(SingleTerm t) throws ParseException {
		if (t.getNumeral() != null) {
			return new ConstantTerm(Long.parseLong(t.getNumeral()));
		}
		if (t.getAtom() != null) {
			return new ConstantTerm(t.getAtom());
		}
		if (t.getVariable() != null) {
			return new VariableTerm(t.getVariable());
		}
		if (t.getOperator() != null) {
			throw new ParseException("Operator not allowed as term");
		}
		return new StringTerm(t.getString());
	}
}
