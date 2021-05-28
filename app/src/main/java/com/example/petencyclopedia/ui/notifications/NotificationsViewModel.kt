package com.example.petencyclopedia.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Here you can find your favourites cats and dogs! " + "\n" +
                "Sadly this option isn't implemented yet :'( "
    }
    val text: LiveData<String> = _text
}