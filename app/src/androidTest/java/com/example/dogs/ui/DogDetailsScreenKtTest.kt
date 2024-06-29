package com.example.dogs.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dogs.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DogDetailsScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testDogDetailsScreen() {
        composeTestRule.activity.setContent {
            DogDetailsScreen(viewModel = hiltViewModel())
        }

        composeTestRule.onNodeWithContentDescription("Dog Details")
            .assertIsDisplayed()
    }
}