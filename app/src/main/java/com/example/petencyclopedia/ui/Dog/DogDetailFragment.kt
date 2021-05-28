package com.example.petencyclopedia.ui.Dog

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.petencyclopedia.R
import com.example.petencyclopedia.databinding.FragmentDogDetailBinding
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog
import com.example.petencyclopedia.ui.Dog.dogAPI.DogAPI
import com.example.petencyclopedia.ui.Dog.dogAPI.DoggoAdaptater
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
    private lateinit var doggo_image: ImageView

    private val mdogViewModel : DogViewModel by activityViewModels()
    private val madapterView = DoggoAdaptater(listOf())
    private lateinit var doggo: Dog
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDogDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

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
        val id_doggo = arguments?.getInt("id_doggo")?: 0
        val name_doggo = arguments?.getString("name_doggo")?: "Airedale"
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
        doggo_image = view.findViewById(R.id.doggo_detail_image)

        mdogViewModel.doggoList.observe(viewLifecycleOwner, Observer {mdogModel ->
        })
        doggo = Dog(Weight("inconnu","inconnu"),Height("inconnu","inconnu"),0,"inconnu","inconnu","inconnu","inconnu","inconnu","inconnu","inconnu",Image("inconnu",0,0,"inconnu"))
        //7doggo = mdogViewModel.getSingleDog(id_doggo)
        doggo = mdogViewModel.getSingleDogFromName(name_doggo)

        if(doggo!= null){
            doggo_name.text = doggo.name
            doggo_breed.text = "Espèce : "+ doggo.breed_group
            doggo_weight.text = "Poids : " + doggo.weight.metric
            doggo_height.text = "Taille : " + doggo.height.metric
            doggo_life_span.text = "Espérance de vie : " + doggo.life_span
            doggo_origin.text = "Origine : " + doggo.origin
            doggo_temperament.text = "Temperament : " + doggo.temperament

            val image_url = "https://cdn2.thedogapi.com/images/"+doggo.reference_image_id+".jpg"
            Glide.with(this)
                .load(image_url)
                .centerCrop()
                .into(doggo_image)
        }
        else{
            doggo_name.text = "doggo is null !"
        }

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