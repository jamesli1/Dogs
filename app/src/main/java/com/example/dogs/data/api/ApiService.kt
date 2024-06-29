package com.example.dogs.data.api

import com.example.dogs.data.model.Breed
import com.example.dogs.data.model.Dog
import com.example.dogs.data.model.DogImage
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("breeds")
    suspend fun getDogs(
    ): List<Breed>

    @GET("breeds/{id}")
    suspend fun getDogById(
        @Path("id") id: Int
    ): Dog

    @GET("images/{id}")
    suspend fun getDogImageById(
        @Path("id") id: String
    ): DogImage
}