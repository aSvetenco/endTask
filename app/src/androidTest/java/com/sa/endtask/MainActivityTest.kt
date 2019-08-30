package com.sa.endtask

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import com.sa.endtask.ui.MainActivity
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.sa.endtask.ui.ProductListAdapter
import org.junit.Before
import org.junit.Rule



@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    var activityRule = IntentsTestRule(MainActivity::class.java, true,  false)

    @Before
    fun setUp(){
        activityRule.launchActivity(Intent())
    }

    @Test
    fun scrollProductList() {
        onView(withId(R.id.list)).check(matches(isDisplayed()))
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition<ProductListAdapter.ProductListVH>(20, click()))
    }
}
