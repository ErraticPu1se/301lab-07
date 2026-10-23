package com.example.lab_7

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FormulaZeroTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigate_to_races_add_race_and_return_home() {
        composeTestRule.onNodeWithTag("raceButton").performClick()
        composeTestRule.onNodeWithText("Upcoming Races").assertIsDisplayed()
        composeTestRule.onNodeWithTag("locationInput").performTextInput("Canada")
        composeTestRule.onNodeWithTag("addRaceButton").performClick()
        composeTestRule.onNodeWithText("Canada").assertIsDisplayed()
        composeTestRule.onNodeWithTag("locationInput").assert(SemanticsMatcher.expectValue(SemanticsProperties.EditableText, AnnotatedString("")))
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithText("FormulaZero Home").assertIsDisplayed()
    }

    @Test
    fun navigate_to_drivers_add_clear_and_return_home() {
        composeTestRule.onNodeWithTag("driversButton").performClick()
        composeTestRule.onNodeWithText("FormulaZero Drivers").assertIsDisplayed()
        for (driver in listOf("Alex", "Jordan")) {
            composeTestRule.onNodeWithTag("driverInput").performTextInput(driver)
            composeTestRule.onNodeWithTag("addDriverButton").performClick()
            composeTestRule.onNodeWithText(driver).assertIsDisplayed()
        }
        composeTestRule.onNodeWithTag("driverInput").assert(SemanticsMatcher.expectValue(SemanticsProperties.EditableText, AnnotatedString("")))
        composeTestRule.onNodeWithTag("clearDriversButton").performClick()
        composeTestRule.onNodeWithText("Alex").assertDoesNotExist()
        composeTestRule.onNodeWithText("Jordan").assertDoesNotExist()
        composeTestRule.onNodeWithTag("driversList").onChildren().assertCountEquals(0)
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithText("FormulaZero Home").assertIsDisplayed()
    }

    @Test
    fun blank_entries_are_not_added() {
        composeTestRule.onNodeWithTag("raceButton").performClick()
        composeTestRule.onNodeWithTag("locationInput").performTextInput("   ")
        composeTestRule.onNodeWithTag("addRaceButton").performClick()
        composeTestRule.onNodeWithTag("racesList").onChildren().assertCountEquals(0)
        composeTestRule.onNodeWithTag("backButton").performClick()
        composeTestRule.onNodeWithTag("driversButton").performClick()
        composeTestRule.onNodeWithTag("driverInput").performTextInput("   ")
        composeTestRule.onNodeWithTag("addDriverButton").performClick()
        composeTestRule.onNodeWithTag("driversList").onChildren().assertCountEquals(0)
    }
}
