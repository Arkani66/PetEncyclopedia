package com.example.petencyclopedia.ui.Cat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.petencyclopedia.R
import com.example.petencyclopedia.databinding.FragmentCatDetailBinding
import com.example.petencyclopedia.databinding.FragmentDogDetailBinding
import com.example.petencyclopedia.ui.Cat.catAPI.Cat
import com.example.petencyclopedia.ui.Cat.catAPI.KittenAdaptater
import com.example.petencyclopedia.ui.Dog.DogViewModel
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog
import com.example.petencyclopedia.ui.Dog.dogAPI.DoggoAdaptater
import com.example.petencyclopedia.ui.data_class.Height
import com.example.petencyclopedia.ui.data_class.Image
import com.example.petencyclopedia.ui.data_class.Weight

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CatDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
   //private var param1: String? = null
   //private var param2: String? = null

    private var _binding: FragmentCatDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var button_return: Button
    private lateinit var kitten_name: TextView
    private lateinit var kitten_height: TextView
    private lateinit var kitten_weight: TextView
    private lateinit var kitten_life_span: TextView
    private lateinit var kitten_temperament: TextView
    private lateinit var kitten_origin: TextView
    private lateinit var kitten_image: ImageView
    private lateinit var kitten_indoor_image: ImageView
    private lateinit var kitten_affection_image: ImageView
    private lateinit var kitten_dog_image: ImageView
    private lateinit var kitten_vocalisation_image: ImageView

    private val mcatViewModel : CatViewModel by activityViewModels()
    private val madapterView = KittenAdaptater(listOf())
    private lateinit var kitten: Cat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //arguments?.let {
       //    param1 = it.getString(ARG_PARAM1)
       //    param2 = it.getString(ARG_PARAM2)
       //}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id_kitten = arguments?.getString("name_kitten")?: "abys"

        button_return = view.findViewById(R.id.kitten_detail_button_return)
        button_return.setOnClickListener {
            findNavController().navigate(R.id.navigation_to_cat)
        }

        kitten_name = view.findViewById(R.id.kitten_detail_name)
        kitten_height = view.findViewById(R.id.kitten_detail_height)
        kitten_temperament = view.findViewById(R.id.kitten_detail_temperament)
        kitten_origin = view.findViewById(R.id.kitten_detail_origin)
        kitten_life_span = view.findViewById(R.id.kitten_detail_life_span)
        kitten_weight = view.findViewById(R.id.kitten_detail_weigth)
        kitten_image = view.findViewById(R.id.kitten_detail_image)

        kitten_indoor_image = view.findViewById(R.id.kitten_detail_indoor_bar)
        kitten_affection_image = view.findViewById(R.id.kitten_detail_affection_bar)
        kitten_dog_image = view.findViewById(R.id.kitten_detail_dog_bar)
        kitten_vocalisation_image = view.findViewById(R.id.kitten_detail_vocalisation_bar)

        kitten_name.text = id_kitten
        mcatViewModel.catlist.observe(viewLifecycleOwner, Observer {mcatModel ->
        })
        kitten = Cat(Weight("inconnu","inconnu"),"inconnu","inconnu","inconnu","inconnu","inconnu","inconnu","inconnu",0,0,"inconnu",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"inconnu","inconnu",
            Image("inconnu",0,0,"inconnu")
        )
        kitten = mcatViewModel.getSingleCat(id_kitten)

        if(kitten!= null) {
            kitten_name.text = kitten.name
            kitten_origin.text = kitten.description
            kitten_life_span.text = "Espérance de vie : " + kitten.life_span
            kitten_height.text = "Origine : " + kitten.origin
            kitten_temperament.text = "Temperament : " + kitten.temperament
            kitten_weight.text = "Weight : " + kitten.weight.metric
            Glide.with(this)
                .load(kitten.image.url)
                .centerCrop()
                .into(kitten_image)
            printingAffection()
            printingDogFriendly()
            printingIndoor()
            printingVocalisation()
        }
        else{
            kitten_name.text = "kitten is null !"
        }

    }

    fun printingAffection(){
        //affection
        if(kitten.affection_level == 0){
            Glide.with(this)
                .load(R.drawable.catbar0)
                .centerCrop()
                .into(kitten_affection_image)
        }
        else if(kitten.affection_level == 1){
            Glide.with(this)
                .load(R.drawable.catbar1)
                .centerCrop()
                .into(kitten_affection_image)
        }
        else if(kitten.affection_level == 2){
            Glide.with(this)
                .load(R.drawable.catbar2)
                .centerCrop()
                .into(kitten_affection_image)
        }
        else if(kitten.affection_level == 3){
            Glide.with(this)
                .load(R.drawable.catbar3)
                .centerCrop()
                .into(kitten_affection_image)
        }
        else if(kitten.affection_level == 4){
            Glide.with(this)
                .load(R.drawable.catbar4)
                .centerCrop()
                .into(kitten_affection_image)
        }
        else {
            Glide.with(this)
                .load(R.drawable.catbar5)
                .centerCrop()
                .into(kitten_affection_image)
        }
    }

    fun printingIndoor(){
        //affection
        if(kitten.indoor == 0){
            Glide.with(this)
                .load(R.drawable.catbar0)
                .centerCrop()
                .into(kitten_indoor_image)
        }
        else if(kitten.indoor == 1){
            Glide.with(this)
                .load(R.drawable.catbar1)
                .centerCrop()
                .into(kitten_indoor_image)
        }
        else if(kitten.indoor == 2){
            Glide.with(this)
                .load(R.drawable.catbar2)
                .centerCrop()
                .into(kitten_indoor_image)
        }
        else if(kitten.indoor == 3){
            Glide.with(this)
                .load(R.drawable.catbar3)
                .centerCrop()
                .into(kitten_indoor_image)
        }
        else if(kitten.indoor == 4){
            Glide.with(this)
                .load(R.drawable.catbar4)
                .centerCrop()
                .into(kitten_indoor_image)
        }
        else {
            Glide.with(this)
                .load(R.drawable.catbar5)
                .centerCrop()
                .into(kitten_indoor_image)
        }
    }

    fun printingDogFriendly(){
        //Dog Friendly
        if(kitten.dog_friendly == 0){
            Glide.with(this)
                .load(R.drawable.catbar0)
                .centerCrop()
                .into(kitten_dog_image)
        }
        else if(kitten.dog_friendly == 1){
            Glide.with(this)
                .load(R.drawable.catbar1)
                .centerCrop()
                .into(kitten_dog_image)
        }
        else if(kitten.dog_friendly == 2){
            Glide.with(this)
                .load(R.drawable.catbar2)
                .centerCrop()
                .into(kitten_dog_image)
        }
        else if(kitten.dog_friendly == 3){
            Glide.with(this)
                .load(R.drawable.catbar3)
                .centerCrop()
                .into(kitten_dog_image)
        }
        else if(kitten.dog_friendly == 4){
            Glide.with(this)
                .load(R.drawable.catbar4)
                .centerCrop()
                .into(kitten_dog_image)
        }
        else {
            Glide.with(this)
                .load(R.drawable.catbar5)
                .centerCrop()
                .into(kitten_dog_image)
        }
    }

    fun printingVocalisation(){
        //vocalisation
        if(kitten.vocalisation == 0){
            Glide.with(this)
                .load(R.drawable.catbar0)
                .centerCrop()
                .into(kitten_vocalisation_image)
        }
        else if(kitten.vocalisation == 1){
            Glide.with(this)
                .load(R.drawable.catbar1)
                .centerCrop()
                .into(kitten_vocalisation_image)
        }
        else if(kitten.vocalisation == 2){
            Glide.with(this)
                .load(R.drawable.catbar2)
                .centerCrop()
                .into(kitten_vocalisation_image)
        }
        else if(kitten.vocalisation == 3){
            Glide.with(this)
                .load(R.drawable.catbar3)
                .centerCrop()
                .into(kitten_vocalisation_image)
        }
        else if(kitten.vocalisation == 4){
            Glide.with(this)
                .load(R.drawable.catbar4)
                .centerCrop()
                .into(kitten_vocalisation_image)
        }
        else {
            Glide.with(this)
                .load(R.drawable.catbar5)
                .centerCrop()
                .into(kitten_vocalisation_image)
        }
    }
}