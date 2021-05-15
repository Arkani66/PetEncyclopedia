package com.example.petencyclopedia.ui.Dog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petencyclopedia.R
import com.example.petencyclopedia.databinding.FragmentDoglistBinding
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog
import com.example.petencyclopedia.ui.Dog.dogAPI.DogAPI
import com.example.petencyclopedia.ui.Dog.dogAPI.DoggoAdaptater
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogListFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentDoglistBinding? = null
    private lateinit var mrecyclerview : RecyclerView
    private val madapterView = DoggoAdaptater(listOf(), ::onClickedDoggo)


    private val mlayoutmanager = LinearLayoutManager(context)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentDoglistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        mrecyclerview = view.findViewById(R.id.doggo_recyclerview)
        mrecyclerview.apply {
            layoutManager = this@DogListFragment.mlayoutmanager
            adapter = this@DogListFragment.madapterView
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/v1/")
            //.header()
            //.asString()
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mdoggoApi: DogAPI = retrofit.create(DogAPI::class.java)

        mdoggoApi.getDoggoList().enqueue(object : Callback<DogResponse> {
            override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<DogResponse>,
                response: Response<DogResponse>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    val mdoggoResponse : DogResponse = response.body()!!
                    madapterView.updateList(mdoggoResponse.breeds)
                }
            }
        })
    }

    private fun onClickedDoggo(dog: Dog) {
        findNavController().navigate(R.id.navigation_dashboard)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}