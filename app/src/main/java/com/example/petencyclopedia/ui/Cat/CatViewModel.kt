package com.example.petencyclopedia.ui.Cat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petencyclopedia.ui.Cat.catAPI.Cat
import com.example.petencyclopedia.ui.Dog.DogFailure
import com.example.petencyclopedia.ui.Dog.DogLoader
import com.example.petencyclopedia.ui.Dog.DogModel
import com.example.petencyclopedia.ui.Dog.DogSuccess
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog
import com.example.petencyclopedia.ui.api.SingletonCat
import com.example.petencyclopedia.ui.api.Singletons
import com.example.petencyclopedia.ui.data_class.Height
import com.example.petencyclopedia.ui.data_class.Image
import com.example.petencyclopedia.ui.data_class.Weight
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *Created by $(USER) on $(DATE).
 */
class CatViewModel : ViewModel(){
    val catlist : MutableLiveData<CatModel> = MutableLiveData()
    val catList_cat : MutableLiveData<List<Cat>> = MutableLiveData()

    init {
        callAPICat()
    }

    private fun callAPICat(){
        catlist.value = CatLoader
        SingletonCat.mkittenApi.getcatList("ca2d4dd4-288f-4b05-acc3-c0f26d579e4d").enqueue(object : Callback<List<Cat>> {
            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                catlist.value = CatFailure

            }

            override fun onResponse(
                call: Call<List<Cat>>,
                response: Response<List<Cat>>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    val mkittenresponse : List<Cat> = response.body()!!
                    catlist.value = CatSuccess(mkittenresponse)
                    catList_cat.value = mkittenresponse
                }
                else
                    catlist.value = CatFailure
            }
        })

    }

    fun getSingleCat(id : String) : Cat {
        var kitten : Cat = catList_cat.value!![0]
        catList_cat.value?.forEach(){
            if( it.id.equals(id)) kitten = it
        }
        return kitten
    }
}