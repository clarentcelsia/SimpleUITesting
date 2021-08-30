package com.example.testingexample

import android.view.WindowManager
import android.view.WindowManager.LayoutParams.TYPE_TOAST
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.testingexample.MainActivity.Companion.buildToastMessage
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
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

        // check toast
        onView(withText(buildToastMessage(name)))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))

    }

}


class ToastMatcher: TypeSafeMatcher<Root>(){
    override fun describeTo(description: Description?) {
        description?.appendText("toasting")
    }

    override fun matchesSafely(item: Root?): Boolean {
        val type = item?.windowLayoutParams?.get()?.type
        if(type == TYPE_TOAST){
            val windowToken = item.decorView.windowToken
            val appToken = item.decorView.applicationWindowToken
            //check if the window isnt contained by any other windows
            if(windowToken === appToken)
                return true
        }
        return false
    }

}