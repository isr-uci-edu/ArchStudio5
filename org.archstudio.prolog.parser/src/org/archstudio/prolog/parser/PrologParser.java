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
			return parseOperation(proofContext, c.getPredicates().get(0));
		}
		List<Term> predicates = Lists.newArrayList();
		for (Expression predicate : c.getPredicates()) {
			predicates.add(parseOperation(proofContext, predicate));
		}
		return new Rule((ComplexTerm) predicates.get(0), predicates.subList(1, predicates.size()));
	}

	private static List<Term> parseOperations(ProofContext proofContext, List<Expression> operations)
			throws ParseException {
		List<Term> terms = Lists.newArrayListWithCapacity(operations.size());
		for (Expression o : operations) {
			terms.add(parseOperation(proofContext, o));
		}
		return terms;
	}

	private static Term parseOperation(ProofContext proofContext, Expression o) throws ParseException {
		if (o.getTerm() != null) {
			return parseTerm(proofContext, o.getTerm());
		}
		List<Term> terms = Lists.newArrayList();
		for (Expression t : o.getExps()) {
			terms.add(parseOperation(proofContext, t));
		}
		Term t = terms.get(0);
		for (int i = 0; i < o.getOps().size(); i++) {
			String name = o.getOps().get(i);
			Class<Operation> operationClass = proofContext.getOperation(name);
			if (operationClass != null) {
				try {
					Constructor<Operation> c = operationClass.getConstructor(String.class, List.class);
					t = c.newInstance(name, Lists.newArrayList(t, terms.get(i + 1)));
				}
				catch (Throwable e) {
					throw new ParseException(e);
				}
			}
			else {
				t = new ComplexTerm(name, terms);
			}
		}
		return t;
	}

	private static Term parseTerm(ProofContext proofContext, org.archstudio.prolog.xtext.prolog.Term t)
			throws ParseException {
		if (t.getAtom() != null) {
			return new ConstantTerm(t.getAtom());
		}
		if (t.isList()) {
			Term tail = new ListTerm(null, null);
			if (t.getTail() != null) {
				tail = parseOperation(proofContext, t.getTail());
			}
			if (t.getHead() != null) {
				for (Expression o : Lists.reverse(t.getHead())) {
					Term head = parseOperation(proofContext, o);
					tail = new ListTerm(head, tail);
				}
			}
			return tail;
		}
		if (t.getString() != null) {
			return new StringTerm(t.getString());
		}
		if (t.getVariable() != null) {
			return new VariableTerm(t.getVariable());
		}
		if (t.getNumber() != null) {
			return new ConstantTerm(new BigDecimal(t.getNumber()));
		}
		throw new IllegalArgumentException();
	}
}
