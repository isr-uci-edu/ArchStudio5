package org.archstudio.prolog.engine;

import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.Signature;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class ProofContext {

	public final ListMultimap<Signature, ComplexTerm> knowledgeBase;

	public ProofContext(Iterable<ComplexTerm> knowledgeBase) {
		this.knowledgeBase = ArrayListMultimap.create();
		for (ComplexTerm i : knowledgeBase) {
			this.knowledgeBase.put(i.getSignature(), i);
		}
	}
}
