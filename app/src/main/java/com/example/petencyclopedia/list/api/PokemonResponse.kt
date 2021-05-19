package com.example.petencyclopedia.list.api

import com.example.petencyclopedia.list.Pokemon


data class PokemonResponse(
    val count: Int,
    val next: String,
    val results: List<Pokemon>
)