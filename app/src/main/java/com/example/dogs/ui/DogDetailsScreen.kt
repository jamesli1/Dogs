package com.example.dogs.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dogs.data.Result
import com.example.dogs.data.model.Dog

@Composable
fun DogDetailsScreen(viewModel: DogViewModel) {
    val dogDetailsResponse by viewModel.dogDetailsResponse.collectAsState()
    when (dogDetailsResponse) {
        is Result.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .semantics { contentDescription = "Dog Details" }
            ) {
                CircularProgressIndicator(
                    strokeWidth = 4.dp
                )
            }
        }

        is Result.Error -> {
            val message = (dogDetailsResponse as Result.Error).message ?: ""
            Text(
                text = message,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }

        is Result.Success -> {
            val details = (dogDetailsResponse as Result.Success<Dog>).data
            DogDetail(details)
        }
    }
}

@Composable
fun DogDetail(details: Dog) {
    Column(
        modifier = Modifier
            .padding(48.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )

    {
        Text(
            text = details.name,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        InfoItem("Group", details.bredGroup ?: "")
        InfoItem("Bred for", details.bredFor ?: "")
        InfoItem("Life span", details.lifeSpan ?: "")
        InfoItem("Height (cm)", details.height?.metric ?: "")
        InfoItem("Weight (kg)", details.weight?.metric ?: "")
        InfoItem("Temperament", details.temperament ?: "")
        Spacer(modifier = Modifier.height(36.dp))
        AsyncImage(
            model = details.referenceImageId,
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun InfoItem(name: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$name:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(2f)
        )
    }
}