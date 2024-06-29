package com.example.dogs.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.dogs.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DogListScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testDogListScreen() {
        composeTestRule.activity.setContent {
            DogListScreen {}
        }

        composeTestRule.onNodeWithContentDescription("Dogs List")
            .assertIsDisplayed()
    }
}