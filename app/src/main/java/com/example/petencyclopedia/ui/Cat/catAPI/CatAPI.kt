package com.example.petencyclopedia.ui.Cat.catAPI

import com.example.petencyclopedia.ui.Dog.DogResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *Created by $(USER) on $(DATE).
 */
interface CatAPI {
    @GET("breeds")
    fun getcatList(@Query("api_key")  api_key : String): Call<List<Cat>>
}