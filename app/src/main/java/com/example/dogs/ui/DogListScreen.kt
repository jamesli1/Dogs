package com.example.dogs.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.dogs.data.Result
import com.example.dogs.data.model.Breed
import kotlinx.coroutines.flow.Flow

@Composable
fun DogListScreen(
    onItemClick: (Int) -> Unit,
) {
    val viewModel: DogViewModel = hiltViewModel()
    val breedsResponse by viewModel.dogsListResponse.collectAsState()
    val listState = rememberLazyListState()
    LaunchedEffect(Unit) {
        viewModel.loadDogs()
    }
    when (breedsResponse) {
        is Result.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .semantics { contentDescription = "Dogs List" }
            ) {
                CircularProgressIndicator(
                    strokeWidth = 4.dp
                )
            }
        }

        is Result.Success -> {
            val breeds = (breedsResponse as Result.Success<Flow<PagingData<Breed>>>).data.collectAsLazyPagingItems()
            if (breeds.itemCount == 0) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics { contentDescription = "Dogs List" }
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 4.dp
                    )
                }
            }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .semantics { contentDescription = "Dog List" },
                contentPadding = PaddingValues(8.dp),
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Dog List",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }
                items(breeds.itemCount) { index ->
                    val item = breeds[index]
                    item?.let {
                        DogCard(
                            it.id,
                            it.name,
                            it.temperament,
                            it.referenceImageId,
                            onItemClick
                        )
                    }
                }
            }
        }

        is Result.Error -> {
            val message = (breedsResponse as Result.Error).message ?: ""
            Text(
                text = message,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun DogCard(
    id: Int,
    title: String,
    temperament: String?,
    referenceImageId: String,
    onItemClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onItemClick(id) },
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = "https://cdn2.thedogapi.com/images/${referenceImageId}.jpg",
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Inside,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.padding(4.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = temperament ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3
                )
            }
        }
    }
}