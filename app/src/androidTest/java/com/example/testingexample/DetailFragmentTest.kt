package com.example.testingexample

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.testingexample.factory.AppFragmentFactory
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.StringBuilder

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailFragmentTest {

    @Test
    fun test_isTextDisplayed(){

        val fragmentFactory = AppFragmentFactory(null)
        val author = arrayListOf("Neil Gaiman, Terry Pratchett")
        val bundle = Bundle()
        bundle.putStringArrayList("author", author)
        val scenario = launchFragmentInContainer<DetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        onView(withId(R.id.author)).check(matches(
            withText(DetailFragment.stringBuilderForDetailFragment(author))
        ))


    }
}