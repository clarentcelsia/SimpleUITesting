package com.example.testingexample

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.testingexample.factory.AppFragmentFactory
import com.example.testingexample.model.Books.GOOD_OMENS
import com.example.testingexample.model.Books.SHERLOCK_HOLMES
import com.example.testingexample.model.Data
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainFragmentTest {

    @Test
    fun test_isListVisible() {
        val fragmentFactory = AppFragmentFactory()

        val book = SHERLOCK_HOLMES
        val bundle = Bundle()
        bundle.putInt("bookId", book.id!!)

        // FragmentScenario
        val scenario = launchFragmentInContainer<MainFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // Verify
        onView(withId(R.id.main_titles)).check(matches(withText(book.title)))

        onView(withId(R.id.main_description)).check(matches(withText(book.desc)))
    }
}