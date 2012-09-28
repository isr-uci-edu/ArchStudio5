package org.archstudio.prolog.engine;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.op.iso.Atom;
import org.archstudio.prolog.op.iso.Compound;
import org.archstudio.prolog.op.iso.Equals;
import org.archstudio.prolog.op.iso.Nonvar;
import org.archstudio.prolog.op.iso.NotEquals;
import org.archstudio.prolog.op.iso.Var;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Rule;
import org.archstudio.prolog.term.Signature;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ProofContext {

	private static final Map<String, Class<? extends Operation>> operations = Maps.newHashMap();
	{
		// register the ISO operations
		operations.put("==", Equals.class);
		operations.put("\\=", NotEquals.class);
		operations.put("atom", Atom.class);
		operations.put("compound", Compound.class);
		operations.put("float", org.archstudio.prolog.op.iso.Float.class);
		operations.put("integer", org.archstudio.prolog.op.iso.Integer.class);
		operations.put("nonvar", Nonvar.class);
		operations.put("number", org.archstudio.prolog.op.iso.Number.class);
		operations.put("var", Var.class);

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
						Class<Operation> clazz = (Class<Operation>) Platform.getBundle(bundleName).loadClass(className);
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
	private final ListMultimap<Signature, Rule> indexedRules = ArrayListMultimap.create();
	private final Map<Signature, ListMultimap<Object, ComplexTerm>[]> indexedTerms = Maps.newHashMap();

	public ProofContext() {
	}

	public ProofContext(Iterable<ComplexTerm> knowledgeBase) {
		add(knowledgeBase);
	}

	@SuppressWarnings("unchecked")
	public Class<Operation> getOperation(String name) {
		return (Class<Operation>) operations.get(name);
	}

	@SuppressWarnings("unchecked")
	public void add(Iterable<ComplexTerm> knowledgeBase) {
		for (ComplexTerm i : knowledgeBase) {
			this.knowledgeBase.put(i.getSignature(), i);
			if (i instanceof Rule) {
				this.indexedRules.put(i.getSignature(), (Rule) i);
			}
			ListMultimap<Object, ComplexTerm>[] indexes = indexedTerms.get(i.getSignature());
			if (indexes == null) {
				indexedTerms.put(i.getSignature(), indexes = new ListMultimap[i.getArity()]);
				for (int j = 0; j < i.getArity(); j++) {
					indexes[j] = ArrayListMultimap.create();
				}
			}
			for (int termIndex = 0; termIndex < i.getArity(); termIndex++) {
				ListMultimap<Object, ComplexTerm> index = indexes[termIndex];
				index.put(i.getTerm(termIndex), i);
			}
		}
	}

	public List<ComplexTerm> getKnowledgeBaseTerms(ComplexTerm goal, Map<VariableTerm, Term> variables) {
		for (int termIndex = 0; termIndex < goal.getArity(); termIndex++) {
			if (variables.containsKey(goal.getTerm(termIndex))) {
				List<ComplexTerm> terms = Lists.newArrayList();
				terms.addAll(indexedTerms.get(goal.getSignature())[termIndex].get(variables.get(goal.getTerm(termIndex))));
				terms.addAll(indexedRules.get(goal.getSignature()));
				return terms;
			}
		}
		return knowledgeBase.get(goal.getSignature());
	}
}
