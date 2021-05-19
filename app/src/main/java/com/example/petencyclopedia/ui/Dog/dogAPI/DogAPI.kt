package com.example.petencyclopedia.ui.Dog.dogAPI

import com.example.petencyclopedia.ui.Dog.DogResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *Created by $(USER) on $(DATE).
 */
interface DogAPI {
    //@Headers("api_key=5884f3ba-9b04-4b9c-acbd-7bebfbee73fa")
    @GET("breeds")
    fun getDoggoList(@Query("api_key")  api_key : String): Call<List<Dog>>

}