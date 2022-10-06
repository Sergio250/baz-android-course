package com.example.cryptochallenge.ui.featuredhomepage

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.cryptochallenge.MainActivity
import com.example.cryptochallenge.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class CryptoFrontPageFragmentTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun checkIfButtonIsDisplayed() {
        onView(withId(R.id.btnStart)).check(matches(isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun clickStartButton_opensNextUI() {
        onView(withId(R.id.btnStart)).perform(click())
        // Verify the next fragment
        onView(withId(R.id.fragmentList)).check(matches(isDisplayed()))
        // Verify the return of the fragment
        pressBack()
        onView(withId(R.id.fragmentFrontPage)).check(matches(isDisplayed()))
    }
}
