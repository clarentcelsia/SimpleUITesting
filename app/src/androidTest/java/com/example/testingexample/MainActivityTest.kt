package com.example.testingexample

import android.app.Activity
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasType
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 *  This will test android functionality
 *  like activity, fragment, service, etc.
 *  It will be tested on real device or emulator.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    // Initialize espresso-intents rule
    val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)

    @get: Rule
    val activityRule = ActivityScenarioRule<MainActivity>(intent)

    @Before
    fun init(){
        Intents.init()
    }

    @Test
    fun test_validateIntent() {

        val expectedIntent: Matcher<Intent> = allOf(
            //Pass all the things that did in Intent to MainActivity
            hasAction(Intent.ACTION_GET_CONTENT),
            hasType("image/*")
        )

        val activityForResult = createGalleryStub()

        //Testing intent
        intending(expectedIntent).respondWith(activityForResult)

        //Verify the action used to launch the activity
        //produces the stub result.
        onView(withId(R.id.open_gallery)).perform(click())
        intended(expectedIntent)
    }

    @After
    fun release(){
        Intents.release()
    }

    /**
     *  Stubbing helps to deal with activityForResult
     *  to make sure the intent provide the data callback response
     *  after doing the intent.
     *
     *  This is useful to check whether the intent response works as expected
     *  or not since it can't be done under test to manipulate the ui
     *  of external activity or control the ActivityResult returned to Activity.
     */

    private fun createGalleryStub(): Instrumentation.ActivityResult {
        // Test pick image from drawable
        val resource: Resources = InstrumentationRegistry.getInstrumentation()
            .context.resources

        // Get the uri of an image stored in drawable
        val imageUri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resource.getResourcePackageName(R.drawable.ic_launcher_background) + "/" +
                    resource.getResourceTypeName(R.drawable.ic_launcher_background) + "/" +
                    resource.getResourceEntryName(R.drawable.ic_launcher_background)
        )

        val resultData = Intent()
        resultData.data = imageUri

        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
    }

}