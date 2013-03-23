package org.archstudio.prolog.engine;

import java.util.List;
import java.util.Map;

import org.archstudio.prolog.op.Operation;
import org.archstudio.prolog.op.iso.Conjunction;
import org.archstudio.prolog.op.iso.Disjunction;
import org.archstudio.prolog.op.iso.Equals;
import org.archstudio.prolog.op.iso.False;
import org.archstudio.prolog.op.iso.Is;
import org.archstudio.prolog.op.iso.IsAtom;
import org.archstudio.prolog.op.iso.IsCompound;
import org.archstudio.prolog.op.iso.IsFloat;
import org.archstudio.prolog.op.iso.IsInteger;
import org.archstudio.prolog.op.iso.IsNonvar;
import org.archstudio.prolog.op.iso.IsNumber;
import org.archstudio.prolog.op.iso.IsVar;
import org.archstudio.prolog.op.iso.Neck;
import org.archstudio.prolog.op.iso.Not;
import org.archstudio.prolog.op.iso.NotUnifiable;
import org.archstudio.prolog.op.iso.True;
import org.archstudio.prolog.op.iso.Unifiable;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.prolog.term.VariableTerm;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ProofContext implements Cloneable {

	private static final Map<String, Class<? extends Operation>> operations = Maps.newHashMap();
	{
		// register the ISO operations

		operations.put(",", Conjunction.class);
		operations.put(";", Disjunction.class);
		operations.put("|", Disjunction.class);
		operations.put("==", Equals.class);
		operations.put(":-", Neck.class);
		operations.put("\\+", Not.class);
		operations.put("\\=", NotUnifiable.class);
		operations.put("=", Unifiable.class);

		operations.put("atom", IsAtom.class);
		operations.put("compound", IsCompound.class);
		operations.put("false", False.class);
		operations.put("float", IsFloat.class);
		operations.put("integer", IsInteger.class);
		operations.put("is", Is.class);
		operations.put("nonvar", IsNonvar.class);
		operations.put("number", IsNumber.class);
		operations.put("true", True.class);
		operations.put("var", IsVar.class);

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
	private final ListMultimap<Signature, Neck> indexedRules = ArrayListMultimap.create();
	private final Map<Signature, ListMultimap<Object, ComplexTerm>[]> indexedTerms = Maps.newHashMap();

	public ProofContext() {
	}

	public ProofContext(Iterable<ComplexTerm> knowledgeBase) {
		add(knowledgeBase);
	}

	public ProofContext(ProofContext toCopy) {
		add(toCopy.knowledgeBase.values());
	}

	@SuppressWarnings("unchecked")
	public Class<Operation> getOperation(String name) {
		return (Class<Operation>) operations.get(name);
	}

	@SuppressWarnings("unchecked")
	public void add(Iterable<ComplexTerm> knowledgeBase) {
		for (ComplexTerm i : knowledgeBase) {
			ComplexTerm head = i;
			if (i instanceof Neck) {
				head = ((Neck) i).getHead();
				this.indexedRules.put(head.getSignature(), (Neck) i);
			}
			this.knowledgeBase.put(head.getSignature(), i);
			ListMultimap<Object, ComplexTerm>[] indexes = indexedTerms.get(head.getSignature());
			if (indexes == null) {
				indexedTerms.put(head.getSignature(), indexes = new ListMultimap[head.getArity()]);
				for (int j = 0; j < head.getArity(); j++) {
					indexes[j] = ArrayListMultimap.create();
				}
			}
			for (int termIndex = 0; termIndex < head.getArity(); termIndex++) {
				ListMultimap<Object, ComplexTerm> index = indexes[termIndex];
				index.put(head.getTerm(termIndex), i);
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
