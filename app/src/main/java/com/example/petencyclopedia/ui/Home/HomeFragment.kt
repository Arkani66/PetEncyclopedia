package com.example.petencyclopedia.ui.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.petencyclopedia.R
import com.example.petencyclopedia.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var texthome : TextView
    private lateinit var imagedog: ImageView
    private lateinit var imagecat: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        texthome = view.findViewById(R.id.home_text)
        imagecat = view.findViewById(R.id.button_cat)
        imagedog = view.findViewById(R.id.button_dog)

        Glide.with(this)
            .load(R.drawable.funnycat)
            .centerCrop()
            .into(imagecat)
        Glide.with(this)
            .load(R.drawable.happydog)
            .centerCrop()
            .into(imagedog)

        imagecat.setOnClickListener{
            //Toast.makeText(this@HomeFragment, R.string.hello_homecat, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.navigation_tocat_fromhome)
        }
        imagedog.setOnClickListener{
            //Toast.makeText(this@HomeFragment, R.string.hello_homedog, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.navigation_todog_fromhome)
        }
    }

}