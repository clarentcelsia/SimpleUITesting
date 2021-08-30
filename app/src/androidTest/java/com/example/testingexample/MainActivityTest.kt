package com.example.testingexample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.*
import org.junit.runner.RunWith

/**
 *  This will test android functionality
 *  like activity, fragment, service, etc.
 *  It will be tested on real device or emulator.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_showDialog() {
        val name = "Anonymous"

        onView(withId(R.id.launch)).perform(click())

        onView(withId(R.id.etName)).check(matches(isDisplayed()))

        //Check if text has already been entered (not null)
        onView(withId(R.id.etName)).perform(typeText(name))

        onView(withText(R.string.submit)).perform(click())

        //to check if a view is not displayed
        //(for a view that is not in the hierarchy)
        onView(withId(R.id.dialog)).check(doesNotExist())

        onView(withId(R.id.container)).check(matches(withText(name)))

    }
}