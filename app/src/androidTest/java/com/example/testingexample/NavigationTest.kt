package com.example.testingexample

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NavigationTest {

    @Test
    fun test_FragmentNavigation(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Nav to MainFragment
        onView(withId(R.id.main_fragment)).check(matches(isDisplayed()))

        onView(withId(R.id.main_author)).perform(click())

        onView(withId(R.id.detail_fragment)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.main_fragment)).check(matches(isDisplayed()))

    }
}