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

    val doggoList : MutableLiveData<List<Dog>> = MutableLiveData()

    init {
        callAPIDoggo()
    }

    private fun callAPIDoggo(){
        Singletons.mdoggoApi.getDoggoList().enqueue(object :
            Callback<List<Dog>> {
            override fun onFailure(call: Call<List<Dog>>, t: Throwable) {
                val mdoggoResponse : ArrayList<Dog> = arrayListOf<Dog>().apply {
                    add(Dog(
                        Weight("6 - 13","3 - 6"), Height("9 - 11.5","23 - 29"),1,"Hasn't work out","default","default","default","default","default","default",
                        Image("BJa4kxc4X",1600,1199,"https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg")
                    ))
                }
                doggoList.value = mdoggoResponse
            }

            override fun onResponse(
                call: Call<List<Dog>>,
                response: Response<List<Dog>>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    val mdoggoResponse : List<Dog> = response.body()!!
                    doggoList.value = mdoggoResponse
                }
            }
        })
    }

    fun getSingleDog(id : Int) : Dog{
        val doggo : Dog
        if(id!=0) doggo = doggoList.value!![id-1]
        else doggo = doggoList.value!![id]
        return doggo
    }
}