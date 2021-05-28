package com.example.petencyclopedia.ui.api

import com.example.petencyclopedia.ui.Cat.catAPI.CatAPI
import com.example.petencyclopedia.ui.Dog.dogAPI.DogAPI
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 *Created by $(USER) on $(DATE).
 */
class SingletonCat {
    companion object{
        var cache: Cache = Cache(File(PetApplication.context?.getCacheDir(),"responses"),10 * 1024 * 1024)

        var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()

        var mkittenApi: CatAPI =  Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CatAPI::class.java)

    }
}