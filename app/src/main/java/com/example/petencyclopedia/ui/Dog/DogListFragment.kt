package com.example.petencyclopedia.ui.Dog

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petencyclopedia.R
import com.example.petencyclopedia.databinding.FragmentDoglistBinding
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog
import com.example.petencyclopedia.ui.Dog.dogAPI.DoggoAdaptater
import com.example.petencyclopedia.ui.api.Singletons
import com.example.petencyclopedia.ui.data_class.Height
import com.example.petencyclopedia.ui.data_class.Image
import com.example.petencyclopedia.ui.data_class.Weight
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogListFragment : Fragment() {

    private lateinit var homeViewModel: DogViewModel
    private var _binding: FragmentDoglistBinding? = null
    private lateinit var mrecyclerview : RecyclerView
    private val madapterView = DoggoAdaptater(listOf(), ::onClickedDoggo)
    private val mlayoutmanager = LinearLayoutManager(context)
    private lateinit var mloader : ProgressBar
    private lateinit var mtexterror: TextView
    private val mdogViewModel : DogViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentDoglistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        mrecyclerview = view.findViewById(R.id.doggo_recyclerview)
        mrecyclerview.apply {
            layoutManager = this@DogListFragment.mlayoutmanager
            adapter = this@DogListFragment.madapterView
        }
        mloader = view.findViewById(R.id.doggo_list_loader)
        mtexterror = view.findViewById(R.id.doggo_list_text_error)

        mdogViewModel.doggoList.observe(viewLifecycleOwner, Observer {mdogModel ->
            mloader.isVisible = mdogModel is DogLoader
            mtexterror.isVisible = mdogModel is DogFailure
            if(mdogModel is DogSuccess) madapterView.updateList(mdogModel.doggolist)
            else mrecyclerview.isInvisible

        })
    }

        private fun onClickedDoggo(dog: Dog) {
        /*val name_doggo = dog.name
        val bundle = Bundle()
        bundle.putString("name", name_doggo)
        val fragment = Fragment()
        fragment.setArguments(bundle)
        findNavController().navigate(R.id.navigation_to_dogdetail, bundle)*/
        val id_name = dog.id
        val action =
        findNavController().navigate(R.id.navigation_to_dogdetail, bundleOf(
            "id_doggo" to dog.id
        ))
    }

   /* private fun onClickedPokemon(pokemon: Pokemon) {
        findNavController().navigate(R.id.navigation_cat)
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}