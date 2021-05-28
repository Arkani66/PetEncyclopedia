package com.example.petencyclopedia.ui.api

import android.app.Application
import android.content.Context

/**
 *Created by $(USER) on $(DATE).
 */
class PetApplication : Application() {

    companion object{
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}
