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
    private lateinit var kitten_breed: TextView
    private lateinit var kitten_height: TextView
    private lateinit var kitten_weight: TextView
    private lateinit var kitten_life_span: TextView
    private lateinit var kitten_temperament: TextView
    private lateinit var kitten_origin: TextView
    private lateinit var kitten_image: ImageView

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
        kitten_breed = view.findViewById(R.id.kitten_detail_breed)
        kitten_image = view.findViewById(R.id.kitten_detail_image)

        kitten_name.text = id_kitten
        mcatViewModel.catlist.observe(viewLifecycleOwner, Observer {mcatModel ->
        })
        kitten = Cat(Weight("inconnu","inconnu"),"inconnu","inconnu","inconnu","inconnu","inconnu","inconnu","inconnu",0,"inconnu",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"inconnu","inconnu",
            Image("inconnu",0,0,"inconnu")
        )
        kitten = mcatViewModel.getSingleCat(id_kitten)

        if(kitten!= null) {
            kitten_name.text = kitten.name
            kitten_life_span.text = "Espérance de vie : " + kitten.life_span
            kitten_origin.text = "Origine : " + kitten.origin
            kitten_temperament.text = "Temperament : " + kitten.temperament
            val image_url = "https://cdn2.thedogapi.com/images/"+kitten.reference_image_id+".jpg"
            Glide.with(this)
                .load(kitten.image.url)
                .centerCrop()
                .into(kitten_image)
        }
        else{
            kitten_name.text = "kitten is null !"
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CatDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CatDetailFragment().apply {
                //arguments = Bundle().apply {
                //    putString(ARG_PARAM1, param1)
                //    putString(ARG_PARAM2, param2)
                //}
            }
    }
}