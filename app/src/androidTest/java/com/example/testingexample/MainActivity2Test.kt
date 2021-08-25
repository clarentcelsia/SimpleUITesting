package com.example.testingexample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivity2Test{

    // This is to call [ActivityScenario.launch] automatically
    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity2::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.secondary_root))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_ComponentVisibility(){
        onView(withId(R.id.buttonBack))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}