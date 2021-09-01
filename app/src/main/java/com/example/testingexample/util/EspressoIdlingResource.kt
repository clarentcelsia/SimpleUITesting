package com.example.testingexample.util

import androidx.test.espresso.idling.CountingIdlingResource

/**
 *  This Idling Resource represents an asynchronous operation
 *  whose result affect subsequent operation in UI test.
 */
object EspressoIdlingResource {

    private const val RES = "RESOURCE"

    //Integrate your app with IdlingResources implementation
    //https://developer.android.com/training/testing/espresso/idling-resource

    @JvmField val countingIdlingRes = CountingIdlingResource(RES)

    //Decrements / Increments the count of in-flight transactions
    //to the resource being monitored.
    fun increment() {
        countingIdlingRes.increment()
    }

    fun decrement() {
        if(!countingIdlingRes.isIdleNow)
            countingIdlingRes.decrement()
    }
}