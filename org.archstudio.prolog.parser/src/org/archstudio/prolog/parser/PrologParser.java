package org.archstudio.prolog.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

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

	private static Program parse(String input) {
		try {
			if (injector == null) {
				injector = new PrologStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
			}
			XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
			resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
			Resource resource = resourceSet.createResource(URI.createURI("null:/prolog.pl"));
			InputStream in = new ByteArrayInputStream(input.getBytes());
			resource.load(in, resourceSet.getLoadOptions());
			return (Program) resource.getContents().get(0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static synchronized List<Term> parseTerms(String input) {
		Program program = parse(input);
		List<Term> terms = Lists.newArrayList();
		for (SingleClause clause : program.getClauses()) {
			terms.add(parseClause(clause));
		}
		return terms;
	}

	private static Term parseClause(SingleClause c) {
		if (c.getPredicates().size() == 1) {
			return parsePredicate(c.getPredicates().get(0));
		}
		List<Term> predicates = Lists.newArrayList();
		for (Predicate p : c.getPredicates()) {
			predicates.add(parsePredicate(p));
		}
		return new Rule((ComplexTerm) predicates.get(0), predicates.subList(1, predicates.size()));
	}

	private static Term parsePredicate(Predicate p) {
		if (p.getTerms().size() == 0) {
			return parseSingleTerm(p.getValue());
		}
		String name = p.getValue().getAtom();
		List<Term> terms = Lists.newArrayList();
		for (SingleTerm t : p.getTerms()) {
			terms.add(parseSingleTerm(t));
		}
		return new ComplexTerm(name, terms);
	}

	private static Term parseSingleTerm(SingleTerm t) {
		if (t.getNumeral() != null) {
			return new ConstantTerm(Long.parseLong(t.getNumeral()));
		}
		if (t.getAtom() != null) {
			return new ConstantTerm(t.getAtom());
		}
		if (t.getVariable() != null) {
			return new VariableTerm(t.getVariable());
		}
		return new StringTerm(t.getString());
	}
}
