package com.example.testingexample

import android.app.Activity
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.testingexample.ImageHasDrawableMatcher.hasDrawable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.*
import org.junit.runner.RunWith
import java.io.File

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