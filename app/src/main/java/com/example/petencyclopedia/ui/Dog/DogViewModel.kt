package com.example.petencyclopedia.ui.Dog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog
import com.example.petencyclopedia.ui.api.Singletons
import com.example.petencyclopedia.ui.data_class.Height
import com.example.petencyclopedia.ui.data_class.Image
import com.example.petencyclopedia.ui.data_class.Weight
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogViewModel : ViewModel() {

    val doggoList : MutableLiveData<DogModel> = MutableLiveData()
    val doggoList_dog : MutableLiveData<List<Dog>> = MutableLiveData()

    init {
        callAPIDoggo()
    }

    private fun callAPIDoggo(){
        doggoList.value = DogLoader
        Singletons.mdoggoApi.getDoggoList().enqueue(object :
            Callback<List<Dog>> {
            override fun onFailure(call: Call<List<Dog>>, t: Throwable) {
                val mdoggoResponse : ArrayList<Dog> = arrayListOf<Dog>().apply {

                }
                doggoList.value = DogFailure
            }

            override fun onResponse(
                call: Call<List<Dog>>,
                response: Response<List<Dog>>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    val mdoggoResponse : List<Dog> = response.body()!!
                    doggoList.value = DogSuccess(mdoggoResponse)
                    doggoList_dog.value = mdoggoResponse
                }
                else
                    doggoList.value = DogFailure
            }
        })
    }


    fun getSingleDog(id : Int) : Dog{
        val doggo : Dog
        if(id!=0) doggo = doggoList_dog.value!![id-1]
        else doggo = doggoList_dog.value!![id]
        return doggo
    }

}