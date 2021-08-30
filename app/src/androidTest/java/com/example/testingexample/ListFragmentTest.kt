package com.example.testingexample

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.testingexample.adapter.ListAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 *  detail: https://github.com/android/testing-samples/
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ListFragmentTest{

    // Here uses the Activity Scenario because List Fragment
    // is the starter
    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // data test samples
    val LIST_INDEX = 1

    @Test
    fun test_isRecyclerDisplayed(){
        onView(withId(R.id.list)).check(matches(isDisplayed()))
    }

    @Test
    fun test_selectListItem(){
        onView(withId(R.id.list)).perform(actionOnItemAtPosition<ListAdapter.ListViewHolder>(
            LIST_INDEX,
            click()))

        onView(withId(R.id.detail_fragment)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.list)).check(matches(isDisplayed()))
    }


}