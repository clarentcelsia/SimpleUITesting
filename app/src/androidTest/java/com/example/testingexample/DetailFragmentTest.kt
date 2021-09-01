package com.example.testingexample

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.testingexample.factory.AppFragmentFactory
import com.example.testingexample.model.Books
import com.example.testingexample.ui.fragment.DetailFragment
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailFragmentTest {

    @Test
    fun test_isTextDisplayed(){

        val fragmentFactory = AppFragmentFactory(null)
        val data = Books.SHERLOCK_HOLMES
        val bundle = Bundle()
        bundle.putParcelable("data", data)
        val scenario = launchFragmentInContainer<DetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        onView(withId(R.id.detail_title)).check(matches(withText(data.title)))

        onView(withId(R.id.detail_imageview)).check(matches(withTagValue(equalTo(data.image))))

        onView(withId(R.id.detail_description)).check(matches(withText(data.desc)))

        onView(withId(R.id.author)).check(matches(
            withText(DetailFragment.stringBuilderForDetailFragment(data.author))
        ))


    }
}