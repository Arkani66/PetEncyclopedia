package com.example.petencyclopedia.ui.Dog.dogAPI

import retrofit2.Call
import com.example.petencyclopedia.ui.Dog.DogResponse
import retrofit2.http.GET

/**
 *Created by $(USER) on $(DATE).
 */
interface DogAPI {
    @GET("breeds")
    fun getDoggoList(): Call<DogResponse>
}