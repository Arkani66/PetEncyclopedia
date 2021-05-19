package com.example.petencyclopedia.ui.Dog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog

class DogViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Voici les différentes catégories de Chiens"
    }
    val text: LiveData<String> = _text

    val doggoList : LiveData<List<Dog>> = MutableLiveData()

    init {

    }
}