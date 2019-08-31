package com.sa.endtask

import android.content.Intent
import androidx.annotation.StringRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.sa.endtask.ui.MainActivity
import com.sa.endtask.ui.ProductListAdapter
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule = IntentsTestRule(MainActivity::class.java, true, false)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun switchToLinearLayoutManager() {
        clickOnOptionMenu(R.string.product_list_list_view)
        val layoutManager =
            activityRule.activity.findViewById<RecyclerView>(R.id.list).layoutManager
        assertTrue(layoutManager is LinearLayoutManager)

    }

    @Test
    fun switchToGridLayoutManager() {
        clickOnOptionMenu(R.string.product_list_grid_view)
        val layoutManager =
            activityRule.activity.findViewById<RecyclerView>(R.id.list).layoutManager
        assertTrue(layoutManager is GridLayoutManager)
    }

    @Test
    fun clickOnListElement() {
        onView(withId(R.id.list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ProductListAdapter.ProductListVH>(
                1,
                click()
            )
        )
        verifyToast(R.string.product_list_item_is_clicked)
    }

    private fun verifyToast(@StringRes message: Int) {
        onView(withText(message))
            .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    private fun clickOnOptionMenu(@StringRes menu: Int) {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(menu)).perform(click())
    }

}
