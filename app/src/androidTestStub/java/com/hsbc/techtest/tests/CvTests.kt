package com.hsbc.techtest.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import com.hsbc.techtest.R
import com.hsbc.techtest.StubActivityTestRule
import com.hsbc.techtest.app.Stub
import com.hsbc.techtest.utils.atPosition
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CvTests {

    @get:Rule
    var rule = StubActivityTestRule()

    @Test
    fun successLoadingCv() {
        rule.launch(Stub.Success)
        verifyScreen()
    }

    @Test
    fun errorLoadingCvSuccessOnRetry() {
        rule.launch(Stub.GenericError)

        onView(withId(R.id.cv_activity_errorRetryView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.error_retry_view_button))
            .check(matches(isDisplayed()))
            .perform(click())

        verifyScreen()
    }

    private fun verifyScreen() {

        onView(withId(R.id.cv_activity_experience_container))
            .check(matches(isDisplayed()))

        onView(withId(R.id.cv_activity_name))
            .check(matches(isDisplayed()))
            .check(matches(withText("Samuel Kirton")))

        onView(withId(R.id.cv_activity_industry_exp_value))
            .check(matches(isDisplayed()))
            .check(matches(withText("6 years")))

        onView(withId(R.id.cv_activity_users_reached_value))
            .check(matches(isDisplayed()))
            .check(matches(withText("1.25 million")))

        onView(withId(R.id.cv_activity_experience_recyclerView))
            .check(matches(atPosition(0, hasDescendant(ViewMatchers.withText("EOS Global Hackathon")))))
            .check(matches(atPosition(0, hasDescendant(ViewMatchers.withText("November 2018 - December 2018")))))
            .check(matches(atPosition(0, hasDescendant(ViewMatchers.withText("I put together a multidisciplinary team of past colleagues for the EOS Global hackathon in London...")))))

        onView(withId(R.id.cv_activity_experience_recyclerView))
            .check(matches(atPosition(1, hasDescendant(ViewMatchers.withText("RBS - Senior Android Developer")))))
            .check(matches(atPosition(1, hasDescendant(ViewMatchers.withText("May 2018 - November 2018")))))
            .check(matches(atPosition(1, hasDescendant(ViewMatchers.withText("At RBS I worked alongside another Senior Android Developer to setup the foundations of a new payments mobile app...")))))
    }
}
