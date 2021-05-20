package com.example.petencyclopedia.ui.Cat

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
import com.example.petencyclopedia.databinding.FragmentCatlistBinding
import com.example.petencyclopedia.ui.Cat.catAPI.Cat
import com.example.petencyclopedia.ui.Cat.catAPI.CatAPI
import com.example.petencyclopedia.ui.Cat.catAPI.KittenAdaptater
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog
import com.example.petencyclopedia.ui.Dog.dogAPI.DogAPI
import com.example.petencyclopedia.ui.Dog.dogAPI.DoggoAdaptater
import com.example.petencyclopedia.ui.data_class.Height
import com.example.petencyclopedia.ui.data_class.Image
import com.example.petencyclopedia.ui.data_class.Weight
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatListFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentCatlistBinding? = null
    private lateinit var mrecyclerview : RecyclerView
    private val madapterView = KittenAdaptater(listOf(), ::onClickedCat)
    private val mlayoutmanager = LinearLayoutManager(context)


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       /* dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)*/

        _binding = FragmentCatlistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        mrecyclerview = view.findViewById(R.id.kitten_recyclerview)
        mrecyclerview.apply {
            layoutManager = this@CatListFragment.mlayoutmanager
            adapter = this@CatListFragment.madapterView
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mkittenAPI: CatAPI = retrofit.create(CatAPI::class.java)

        mkittenAPI.getcatList("ca2d4dd4-288f-4b05-acc3-c0f26d579e4d").enqueue(object : Callback<List<Cat>> {
            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                val mkittenresponse : ArrayList<Cat> = arrayListOf<Cat>().apply {
                    add(
                        Cat("1","Hasn't worked out", "calm","0","d","de","ed","de",0,0,0,0,0,0,0,0,0,"0",0,0,0,0,0,0,0,0,0,0))
                }
                madapterView.updateList(mkittenresponse)
            }

            override fun onResponse(
                call: Call<List<Cat>>,
                response: Response<List<Cat>>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    val mkittenresponse : List<Cat> = response.body()!!
                    madapterView.updateList(mkittenresponse)
                }
            }
        })

    }

    private fun onClickedCat(cat: Cat) {
        findNavController().navigate(R.id.navigation_dog)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}