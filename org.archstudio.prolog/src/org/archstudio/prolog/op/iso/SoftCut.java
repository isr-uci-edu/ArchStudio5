package org.archstudio.prolog.op.iso;

import java.util.List;

import org.archstudio.prolog.op.Executable;
import org.archstudio.prolog.term.Term;

/*
 * "A *-> B ; C" is evaluated as a soft cut: if A succeeds, B is executed and on backtracking subsequent solutions of A
 * are returned, but C is never executed. If A fails, C is executed. It is similar to ->/2, with the exception that ->/2
 * cuts both A and the disjunction if A succeeds, whereas *->/2 cuts only the disjunction. The construct "A *-> B", i.e.
 * without an Else branch, is translated as the normal conjunction A, B.
 */

public class SoftCut extends Conjunction implements Executable {

	// Note: This is also evaluated in Disjunction, see that class for details

	public SoftCut(String name, List<? extends Term> terms) {
		super(name, terms);
	}

}