package com.example.testingexample.util

import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement

class EspressoIdlingResourceRule: TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return IdlingResourceStatement(base)
    }

    class IdlingResourceStatement(private val base: Statement?): Statement(){

        private val idlingResource = EspressoIdlingResource.countingIdlingRes

        override fun evaluate() {
            //before
            IdlingRegistry.getInstance().register(idlingResource)
            try {
                base?.evaluate()?: throw Exception("Error evaluating test.Base statement is null")
            }finally {
                //after
                IdlingRegistry.getInstance().unregister(idlingResource)
            }
        }

    }
}

/**
 *  Test watcher implements test rule
 *  This is simplified version of test rule (option1)
 */
class EspressoIdlingResourceRule2: TestWatcher(){
    private val idlingResource = EspressoIdlingResource.countingIdlingRes

    //before
    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }

    //after
    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }
}