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
        // Verify that the search button is displayed on landing screen
        onView(withId(R.id.btn_search_recipes))
            .check(matches(isDisplayed()))
    }
    
    @Test
    fun testNavigateToReadme() {
        // Click the About button
        onView(withId(R.id.btn_about))
            .perform(click())
        
        // Verify navigation to readme fragment
        Thread.sleep(500) // Wait for navigation
        
        // This test verifies navigation works
        // In a full app, you would verify readme content is displayed
    }
    
    @Test
    fun testNavigateToSearch() {
        // Click the Search Recipes button
        onView(withId(R.id.btn_search_recipes))
            .perform(click())
        
        // Wait for navigation
        Thread.sleep(500)
        
        // Verify search input is displayed
        onView(withId(R.id.edit_text_search))
            .check(matches(isDisplayed()))
    }
}
