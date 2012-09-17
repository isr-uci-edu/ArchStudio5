package org.archstudio.prolog.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ProofTest.class, TermTest.class, UnificationTest.class })
public class AllTests {

}
