package com.example.frigomalin

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.frigomalin.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test for UI interaction
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    
    @Test
    fun testLandingFragmentDisplayed() {
        onView(withId(R.id.btn_search_recipes))
            .check(matches(isDisplayed()))
    }
    
    @Test
    fun testNavigateToReadme() {
        onView(withId(R.id.btn_about))
            .perform(click())
        
        Thread.sleep(500)
    }
    
    @Test
    fun testNavigateToSearch() {
        onView(withId(R.id.btn_search_recipes))
            .perform(click())
        
        Thread.sleep(500)
        
        onView(withId(R.id.edit_text_search))
            .check(matches(isDisplayed()))
    }
}
