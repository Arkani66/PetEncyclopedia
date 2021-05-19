package com.example.petencyclopedia.ui.Dog.dogAPI

import com.example.petencyclopedia.ui.data_class.Height
import com.example.petencyclopedia.ui.data_class.Image
import com.example.petencyclopedia.ui.data_class.Weight

/**
 *Created by $(USER) on $(DATE).
 */
data class Dog(
    var weight : Weight,
    var height : Height,
    var id : Int,
    var name : String,
    var bred_for : String,
    var breed_group : String,
    var life_span : String,
    var temperament : String,
    //var alt_names : String,
    //var wikipedia_url : String,
    var origin : String,
    var reference_image_id : String,
    var image : Image
)
