package com.example.petencyclopedia.ui.api

import com.example.petencyclopedia.ui.Dog.dogAPI.DogAPI
import com.example.petencyclopedia.ui.api.PetApplication.Companion.context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 *Created by $(USER) on $(DATE).
 */
class Singletons {
    companion object{
        var cache: Cache = Cache(File(context?.getCacheDir(),"responses"),10 * 1024 * 1024)

        var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()

        var mdoggoApi: DogAPI =  Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(DogAPI::class.java)

    }
}
