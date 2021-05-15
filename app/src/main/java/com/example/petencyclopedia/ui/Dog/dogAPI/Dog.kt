package com.example.petencyclopedia.ui.Dog.dogAPI

import com.example.petencyclopedia.ui.data_class.Height
import com.example.petencyclopedia.ui.data_class.Image
import com.example.petencyclopedia.ui.data_class.Weight

/**
 *Created by $(USER) on $(DATE).
 */
data class Dog(
    var id : String,
    var name : String,
    var temperament : String,
    var life_span : String,
    var alt_names : String,
    var wikipedia_url : String,
    var origin : String,
    var weight : Weight,
    var country_code : String,
    var height : Height,
    var image : Image
)
