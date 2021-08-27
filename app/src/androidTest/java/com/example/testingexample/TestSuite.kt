package com.example.testingexample

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 *  Here's gonna testing 2 classes at the same time
 *  SuiteClasses return the class to be run
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainFragmentTest::class,
    NavigationTest::class
)
class TestSuite
