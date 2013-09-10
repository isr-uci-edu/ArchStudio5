package org.archstudio.prolog.engine;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.op.iso.Abs;
import org.archstudio.prolog.op.iso.Add;
import org.archstudio.prolog.op.iso.AlphaGreaterThan;
import org.archstudio.prolog.op.iso.AlphaGreaterThanEqual;
import org.archstudio.prolog.op.iso.AlphaLessThan;
import org.archstudio.prolog.op.iso.AlphaLessThanEqual;
import org.archstudio.prolog.op.iso.BagOf;
import org.archstudio.prolog.op.iso.Conjunction;
import org.archstudio.prolog.op.iso.Disjunction;
import org.archstudio.prolog.op.iso.Equals;
import org.archstudio.prolog.op.iso.False;
import org.archstudio.prolog.op.iso.FindAll;
import org.archstudio.prolog.op.iso.IfThen;
import org.archstudio.prolog.op.iso.Is;
import org.archstudio.prolog.op.iso.IsAtom;
import org.archstudio.prolog.op.iso.IsAtomic;
import org.archstudio.prolog.op.iso.IsCallable;
import org.archstudio.prolog.op.iso.IsCompound;
import org.archstudio.prolog.op.iso.IsFloat;
import org.archstudio.prolog.op.iso.IsInteger;
import org.archstudio.prolog.op.iso.IsNonvar;
import org.archstudio.prolog.op.iso.IsNumber;
import org.archstudio.prolog.op.iso.IsVar;
import org.archstudio.prolog.op.iso.Length;
import org.archstudio.prolog.op.iso.Multiply;
import org.archstudio.prolog.op.iso.Neck;
import org.archstudio.prolog.op.iso.Not;
import org.archstudio.prolog.op.iso.NotEquals;
import org.archstudio.prolog.op.iso.NotUnifiable;
import org.archstudio.prolog.op.iso.SetOf;
import org.archstudio.prolog.op.iso.SoftCut;
import org.archstudio.prolog.op.iso.Sort;
import org.archstudio.prolog.op.iso.Subtract;
import org.archstudio.prolog.op.iso.True;
import org.archstudio.prolog.op.iso.Unifiable;
import org.archstudio.prolog.op.iso.ValueEquals;
import org.archstudio.prolog.op.iso.ValueGreaterThan;
import org.archstudio.prolog.op.iso.ValueGreaterThanEqual;
import org.archstudio.prolog.op.iso.ValueLessThan;
import org.archstudio.prolog.op.iso.ValueLessThanEqual;
import org.archstudio.prolog.op.iso.ValueNotEquals;
import org.archstudio.prolog.op.iso.Write;
import org.archstudio.prolog.op.iso.WriteLine;
import org.archstudio.prolog.op.iso.WriteNewLine;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.ListTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class ProofContext implements Cloneable {

	private static final Map<String, Class<? extends Term>> operations = Maps.newHashMap();
	{
		// register the ISO operations

		operations.put("+/2", Add.class);
		operations.put("@>/2", AlphaGreaterThan.class);
		operations.put("@>=/2", AlphaGreaterThanEqual.class);
		operations.put("@</2", AlphaLessThan.class);
		operations.put("@=</2", AlphaLessThanEqual.class);
		operations.put(",/2", Conjunction.class);
		operations.put(";/2", Disjunction.class);
		operations.put("==/2", Equals.class);
		operations.put("->/2", IfThen.class);
		operations.put("./2", ListTerm.class);
		operations.put("*/2", Multiply.class);
		operations.put(":-/2", Neck.class);
		operations.put("\\+/1", Not.class);
		operations.put("\\=/2", NotUnifiable.class);
		operations.put("\\==/2", NotEquals.class);
		operations.put("*->/2", SoftCut.class);
		operations.put("-/2", Subtract.class);
		operations.put("=/2", Unifiable.class);
		operations.put("=:=/2", ValueEquals.class);
		operations.put(">/2", ValueGreaterThan.class);
		operations.put(">=/2", ValueGreaterThanEqual.class);
		operations.put("</2", ValueLessThan.class);
		operations.put("=</2", ValueLessThanEqual.class);
		operations.put("=\\=/2", ValueNotEquals.class);

		operations.put("abs/1", Abs.class);
		operations.put("atom/1", IsAtom.class);
		operations.put("atomic/1", IsAtomic.class);
		operations.put("bagof/3", BagOf.class);
		operations.put("callable/1", IsCallable.class);
		operations.put("compound/1", IsCompound.class);
		operations.put("fail/0", False.class);
		operations.put("false/0", False.class);
		operations.put("findall/3", FindAll.class);
		operations.put("float/1", IsFloat.class);
		operations.put("integer/1", IsInteger.class);
		operations.put("is/2", Is.class);
		operations.put("length/2", Length.class);
		operations.put("nonvar/1", IsNonvar.class);
		operations.put("nl/0", WriteNewLine.class);
		operations.put("number/1", IsNumber.class);
		operations.put("setof/3", SetOf.class);
		operations.put("sort/2", Sort.class);
		operations.put("true/0", True.class);
		operations.put("var/1", IsVar.class);
		operations.put("write/1", Write.class);
		operations.put("writeln/1", WriteLine.class);

		// add additional operations
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		if (reg != null) {
			// The Extension Registry can be null if we're running outside of Eclipse
			for (IConfigurationElement configurationElement : reg
					.getConfigurationElementsFor("org.archstudio.prolog.operation")) {
				String name = configurationElement.getAttribute("name");
				String className = configurationElement.getAttribute("class");
				if (name != null && className != null) {
					String bundleName = configurationElement.getDeclaringExtension().getContributor().getName();
					try {
						@SuppressWarnings("unchecked")
						Class<Executable> clazz = (Class<Executable>) Platform.getBundle(bundleName).loadClass(
								className);
						operations.put(name, clazz);
					}
					catch (ClassNotFoundException cnfe) {
						cnfe.printStackTrace();
					}
				}
			}
		}
	}
	private final ListMultimap<Signature, ComplexTerm> knowledgeBase = ArrayListMultimap.create();
	private final Map<Signature, ListMultimap<Object, ComplexTerm>[]> valueBasedKnowledgeBase = Maps.newHashMap();
	private final LoadingCache<Signature, Map<ComplexTerm, Integer>> knowledgeBaseIndeces = CacheBuilder.newBuilder()
			.build(new CacheLoader<Signature, Map<ComplexTerm, Integer>>() {
				@Override
				public Map<ComplexTerm, Integer> load(Signature key) throws Exception {
					Map<ComplexTerm, Integer> indeces = Maps.newHashMap();
					List<ComplexTerm> terms = knowledgeBase.get(key);
					for (int i = 0; i < terms.size(); i++) {
						indeces.put(terms.get(i), i);
					}
					return indeces;
				}
			});

	private BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

	public ProofContext() {
	}

	public ProofContext(Iterable<ComplexTerm> knowledgeBase) {
		add(knowledgeBase);
	}

	public ProofContext(ProofContext toCopy) {
		add(toCopy.knowledgeBase.values());
	}

	public void add(Iterable<ComplexTerm> terms) {
		for (ComplexTerm term : terms) {
			add(term, true);
		}
	}

	@SuppressWarnings("unchecked")
	public void add(ComplexTerm term, boolean atEnd) {
		ComplexTerm head = term;
		if (!(term instanceof Neck) && !PrologUtils.extractVariables(Sets.<VariableTerm> newHashSet(), term).isEmpty()) {
			term = new Neck(":-", Lists.newArrayList(term, new True("true")));
		}
		if (term instanceof Neck) {
			head = (ComplexTerm) ((Neck) term).getTerm(0);
		}
		add(knowledgeBase.get(head.getSignature()), term, atEnd);
		ListMultimap<Object, ComplexTerm>[] indexes = valueBasedKnowledgeBase.get(head.getSignature());
		if (indexes == null) {
			valueBasedKnowledgeBase.put(head.getSignature(), indexes = new ListMultimap[head.getArity()]);
			for (int j = 0; j < head.getArity(); j++) {
				indexes[j] = ArrayListMultimap.create();
			}
		}
		for (int termIndex = 0; termIndex < head.getArity(); termIndex++) {
			ListMultimap<Object, ComplexTerm> index = indexes[termIndex];
			add(index.get(head.getTerm(termIndex)), term, atEnd);
		}
		knowledgeBaseIndeces.invalidate(term.getSignature());
	}

	private void add(List<ComplexTerm> list, ComplexTerm term, boolean atEnd) {
		if (atEnd) {
			list.add(term);
		}
		else {
			list.add(0, term);
		}
	}

	public List<ComplexTerm> getKnowledgeBaseTerms(ComplexTerm goal, Map<VariableTerm, Term> variables) {
		if (!knowledgeBase.containsKey(goal.getSignature())) {
			throw new IllegalArgumentException("Unrecognized signature: " + goal.getSignature());
		}
		List<ComplexTerm> result = knowledgeBase.get(goal.getSignature());
		for (int termIndex = 0; termIndex < goal.getArity(); termIndex++) {
			if (goal.getTerm(termIndex) instanceof VariableTerm) {
				if (variables.containsKey(goal.getTerm(termIndex))) {
					Object value = variables.get(goal.getTerm(termIndex));
					ListMultimap<Object, ComplexTerm>[] v = valueBasedKnowledgeBase.get(goal.getSignature());
					if (v != null) {
						List<ComplexTerm> t = v[termIndex].get(value);
						if (t != null) {
							// TODO: perform intersection instead of size comparison
							if (t.size() < result.size()) {
								result = t;
							}
						}
					}
				}
			}
			else {
				Object value = goal.getTerm(termIndex);
				ListMultimap<Object, ComplexTerm>[] v = valueBasedKnowledgeBase.get(goal.getSignature());
				if (v != null) {
					List<ComplexTerm> t = (List<ComplexTerm>) v[termIndex].asMap().get(value);
					if (t != null) {
						// TODO: perform intersection instead of size comparison
						if (t.size() < result.size()) {
							result = t;
						}
					}
				}
			}
		}
		return Lists.newArrayList(result);
	}

	public Integer getIndex(ComplexTerm complexTerm) {
		return knowledgeBaseIndeces.getUnchecked(complexTerm.getSignature()).get(complexTerm);
	}

	public Term create(String name, List<Term> terms) {
		@SuppressWarnings("unchecked")
		Class<Executable> operationClass = (Class<Executable>) operations.get(name + "/" + terms.size());
		if (operationClass != null) {
			try {
				if (terms.size() > 0) {
					Constructor<Executable> c = operationClass.getConstructor(String.class, List.class);
					return c.newInstance(name, terms);
				}
				else {
					Constructor<Executable> c = operationClass.getConstructor(String.class);
					return c.newInstance(name);
				}
			}
			catch (Throwable exc) {
				throw new RuntimeException(exc);
			}
		}
		if (terms.size() > 0) {
			return new ComplexTerm(name, terms);
		}
		return new ConstantTerm(name);
	}

	public BufferedWriter getOutput() {
		return output;
	}

	public void setOutput(BufferedWriter output) {
		this.output = output;
	}
}
