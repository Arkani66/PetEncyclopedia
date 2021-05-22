package com.example.petencyclopedia.ui.api

import com.example.petencyclopedia.ui.Dog.dogAPI.DogAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Created by $(USER) on $(DATE).
 */
class Singleton {
    companion object{
        var mdoggoApi: DogAPI =  Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogAPI::class.java)

        /*fun getDoggoApi(){
            val mdoggoApi: DogAPI =  Retrofit.Builder()
                .baseUrl("https://api.thedogapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DogAPI::class.java)
        }*/
    }
}
