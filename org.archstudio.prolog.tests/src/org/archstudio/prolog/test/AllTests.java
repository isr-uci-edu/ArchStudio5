package org.archstudio.prolog.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ProofTest2.class, ProofTest.class, TermTest.class, UnificationTest.class, ParseTest.class })
public class AllTests {

}
