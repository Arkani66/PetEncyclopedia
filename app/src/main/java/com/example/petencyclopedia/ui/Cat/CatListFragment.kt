package com.example.petencyclopedia.ui.Cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
import com.example.petencyclopedia.ui.Dog.DogFailure
import com.example.petencyclopedia.ui.Dog.DogLoader
import com.example.petencyclopedia.ui.Dog.DogSuccess
import com.example.petencyclopedia.ui.Dog.DogViewModel
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog
import com.example.petencyclopedia.ui.Dog.dogAPI.DogAPI
import com.example.petencyclopedia.ui.Dog.dogAPI.DoggoAdaptater
import com.example.petencyclopedia.ui.api.SingletonCat
import com.example.petencyclopedia.ui.data_class.Height
import com.example.petencyclopedia.ui.data_class.Image
import com.example.petencyclopedia.ui.data_class.Weight
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatListFragment : Fragment() {

    private var _binding: FragmentCatlistBinding? = null
    private lateinit var mrecyclerview : RecyclerView
    private val madapterView = KittenAdaptater(listOf(), ::onClickedCat)
    private val mlayoutmanager = LinearLayoutManager(context)
    private lateinit var mloader : ProgressBar
    private lateinit var mtexterror: TextView
    private val mcatViewModel : CatViewModel by activityViewModels()


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mrecyclerview = view.findViewById(R.id.cat_recyclerview)
        mrecyclerview.apply {
            layoutManager = this@CatListFragment.mlayoutmanager
            adapter = this@CatListFragment.madapterView
        }

        mloader = view.findViewById(R.id.cat_list_loader)
        mtexterror = view.findViewById(R.id.cat_list_text_error)

        mcatViewModel.catlist.observe(viewLifecycleOwner, Observer {mcatModel ->
            mloader.isVisible = mcatModel is CatLoader
            mtexterror.isVisible = mcatModel is CatFailure
            if(mcatModel is CatSuccess) madapterView.updateList(mcatModel.catlist)
        })
    }

    private fun onClickedCat(cat: Cat) {
        val name_kitten = cat.name
        val bundle = Bundle()
        bundle.putString("name_kitten", name_kitten)
        val fragment = Fragment()
        fragment.setArguments(bundle)
        findNavController().navigate(R.id.navigation_to_catdetails,bundleOf(
            "name_kitten" to cat.id
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}