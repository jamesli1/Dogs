package com.example.dogs.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.data.Result
import com.example.dogs.data.model.Breed
import com.example.dogs.data.model.Dog
import com.example.dogs.domain.GetDogByIdUseCase
import com.example.dogs.domain.GetDogImageByIdUseCase
import com.example.dogs.domain.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val getDogsUseCase: GetDogsUseCase,
    private val getDogByIdUseCase: GetDogByIdUseCase,
    private val getDogImageByIdUseCase: GetDogImageByIdUseCase
) : ViewModel() {
    private val _dogsListResponse = MutableStateFlow<Result<List<Breed>>>(Result.Loading)
    val dogsListResponse: StateFlow<Result<List<Breed>>> = _dogsListResponse.asStateFlow()

    private val _dogDetailsResponse = MutableStateFlow<Result<Dog>>(Result.Loading)
    val dogDetailsResponse: StateFlow<Result<Dog>> = _dogDetailsResponse.asStateFlow()

    fun loadDogs() {
        viewModelScope.launch {
            _dogsListResponse.value = Result.Loading
            try {
                val response = getDogsUseCase()
                _dogsListResponse.value = Result.Success(response)
            } catch (e: Exception) {
                _dogsListResponse.value = Result.Error(e.message)
            }
        }
    }

    fun getDetailsById(id: Int) {
        viewModelScope.launch {
            _dogDetailsResponse.value = Result.Loading
            try {
                val response = getDogByIdUseCase(id)
                val image = getDogImageByIdUseCase(response.referenceImageId ?: "")
                response.referenceImageId = image.url
                _dogDetailsResponse.value = Result.Success(response)
            } catch (e: Exception) {
                _dogDetailsResponse.value = Result.Error(e.message)
            }
        }
    }
}