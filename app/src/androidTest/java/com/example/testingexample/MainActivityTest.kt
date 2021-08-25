package com.example.testingexample

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

/**
 *  This will test android functionality
 *  like activity, fragment, service, etc.
 *  It will be tested on real device or emulator.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    // This checks whether the activity works or not
    // Activity should appear on screen matched by the given matcher
    // The given matcher is the given id.
    @Test
    fun test_isActivityInView() {
        // ActivityScenario is part of AndroidX library
        // That can place the activity to different states
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main_root)).check(matches(isDisplayed()))
    }

    @Test
    fun test_componentVisibility() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button)).check(
            matches(withEffectiveVisibility(Visibility.VISIBLE)
            )
        )
        onView(withId(R.id.textview)).check(
            matches(withEffectiveVisibility(Visibility.VISIBLE)
            )
        )
    }

    @Test
    fun test_isTextDisplayed(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.textview)).check(matches(withText(R.string.main_textview)))

    }
}