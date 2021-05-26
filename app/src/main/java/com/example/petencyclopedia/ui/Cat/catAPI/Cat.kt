package com.example.petencyclopedia.ui.Cat.catAPI

import android.accounts.AuthenticatorDescription
import com.example.petencyclopedia.ui.data_class.Image
import com.example.petencyclopedia.ui.data_class.Weight

/**
 *Created by $(USER) on $(DATE).
 */
data class Cat(
    var weight: Weight,
    var id : String,
    var name : String,
    var temperament : String,
    var origin : String,
    var country_code : String,
    var description: String,
    var life_span : String,
    var indoor: Int,
    var lap: Int,
    var alt_names : String,
    var experimental : Int,
    var hairless : Int,
    var natural : Int,
    var rare : Int,
    var suppress_tail : Int,
    var short_legs : Int,
    var adaptability : Int,
    var affection_level : Int,
    var child_friendly :Int,
    var dog_friendly : Int,
    var energy_level : Int,
    var grooming : Int,
    var health_issues : Int,
    var intelligence :Int,
    var shedding_level : Int,
    var social_needs : Int,
    var stranger_friendly :Int,
    var vocalisation : Int,
    var hypoallergenic : Int,
    var wikipedia_url : String,
    var reference_image_id: String,
    var image: Image
)
