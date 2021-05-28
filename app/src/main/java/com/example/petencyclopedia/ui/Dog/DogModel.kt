package com.example.petencyclopedia.ui.Dog

import com.example.petencyclopedia.ui.Dog.dogAPI.Dog

/**
 *Created by $(USER) on $(DATE).
 */
sealed class DogModel {
    val doggoList: List<Dog>
        get() {
            TODO()
        }

    operator fun get(i: Int, doggolist : List<Dog>): Dog {
        val dog: Dog
        dog = doggolist[i]
        return dog
    }

}

data class  DogSuccess(val doggolist : List<Dog>) : DogModel()
object  DogFailure: DogModel()
object  DogLoader: DogModel()

