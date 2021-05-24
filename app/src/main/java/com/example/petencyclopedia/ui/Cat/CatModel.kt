package com.example.petencyclopedia.ui.Cat

import com.example.petencyclopedia.ui.Cat.catAPI.Cat
import com.example.petencyclopedia.ui.Dog.DogModel
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog

/**
 *Created by $(USER) on $(DATE).
 */
sealed class CatModel {

    operator fun get(i: Int, catlist : List<Cat>): Cat {
        val cat: Cat
        cat = catlist[i]
        return cat
    }

}

data class  CatSuccess(val catlist : List<Cat>) : CatModel()
object  CatFailure: CatModel()
object  CatLoader: CatModel()