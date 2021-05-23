package com.example.petencyclopedia.ui.Dog

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.petencyclopedia.R
import com.example.petencyclopedia.databinding.FragmentDogDetailBinding
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog
import com.example.petencyclopedia.ui.Dog.dogAPI.DogAPI
import com.example.petencyclopedia.ui.data_class.Height
import com.example.petencyclopedia.ui.data_class.Image
import com.example.petencyclopedia.ui.data_class.Weight
import kotlinx.android.synthetic.main.fragment_dog_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 * Use the [DogDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DogDetailFragment : Fragment() {

    private var _binding: FragmentDogDetailBinding? = null
    private lateinit var button_return: Button
    private lateinit var doggo_name: TextView
    private lateinit var doggo_breed: TextView
    private lateinit var doggo_height: TextView
    private lateinit var doggo_weight: TextView
    private lateinit var doggo_life_span: TextView
    private lateinit var doggo_temperament: TextView
    private lateinit var doggo_origin: TextView


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*homeViewModel =
            ViewModelProvider(this).get(DogViewModel::class.java)*/

        _binding = FragmentDogDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }
    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_detail, container, false)
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        //val name_doggo = arguments?.getString("name")
        val name_doggo = "Airedale"
        button_return = view.findViewById(R.id.doggo_detail_button_return)
        button_return.setOnClickListener {
            findNavController().navigate(R.id.navigation_to_dog)
        }

        doggo_name = view.findViewById(R.id.doggo_detail_name)
        doggo_height = view.findViewById(R.id.doggo_detail_height)
        doggo_temperament = view.findViewById(R.id.doggo_detail_temperament)
        doggo_origin = view.findViewById(R.id.doggo_detail_origin)
        doggo_life_span = view.findViewById(R.id.doggo_detail_life_span)
        doggo_weight = view.findViewById(R.id.doggo_detail_weigth)
        doggo_breed = view.findViewById(R.id.doggo_detail_breed)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mdoggoApi: DogAPI = retrofit.create(DogAPI::class.java)

        mdoggoApi.getDoggo("api_key=5884f3ba-9b04-4b9c-acbd-7bebfbee73fa",name_doggo).enqueue(object : Callback<Dog> {
            override fun onFailure(call: Call<Dog>, t: Throwable) {
                doggo_name.text = "Hasn't worked out"
            }

            override fun onResponse(
                call: Call<Dog>,
                response: Response<Dog>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    val mdoggoResponse : Dog = response.body()!!
                    doggo_name.text = mdoggoResponse.name
                    doggo_breed.text = mdoggoResponse.breed_group
                    doggo_weight.text = mdoggoResponse.weight.metric
                    doggo_height.text = mdoggoResponse.height.metric
                    doggo_life_span.text = mdoggoResponse.life_span
                    doggo_origin.text = mdoggoResponse.origin
                    doggo_temperament.text = mdoggoResponse.temperament
                }
            }
        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DogDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DogDetailFragment().apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}