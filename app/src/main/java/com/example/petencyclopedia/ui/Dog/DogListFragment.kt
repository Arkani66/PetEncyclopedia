package com.example.petencyclopedia.ui.Dog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petencyclopedia.R
import com.example.petencyclopedia.databinding.FragmentDoglistBinding
import com.example.petencyclopedia.ui.Dog.dogAPI.Dog
import com.example.petencyclopedia.ui.Dog.dogAPI.DoggoAdaptater
import com.example.petencyclopedia.ui.api.Singleton
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

        _binding = FragmentDoglistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        mrecyclerview = view.findViewById(R.id.doggo_recyclerview)
        mrecyclerview.apply {
            layoutManager = this@DogListFragment.mlayoutmanager
            adapter = this@DogListFragment.madapterView
        }

        Singleton.mdoggoApi.getDoggoList("api_key=5884f3ba-9b04-4b9c-acbd-7bebfbee73fa").enqueue(object : Callback<List<Dog>> {
            override fun onFailure(call: Call<List<Dog>>, t: Throwable) {
                val mdoggoResponse : ArrayList<Dog> = arrayListOf<Dog>().apply {
                    add(Dog(Weight("6 - 13","3 - 6"), Height("9 - 11.5","23 - 29"),1,"Hasn't work out","default","default","default","default","default","default",
                        Image("BJa4kxc4X",1600,1199,"https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg")
                    ))
                }
                madapterView.updateList(mdoggoResponse)
            }

            override fun onResponse(
                call: Call<List<Dog>>,
                response: Response<List<Dog>>
            ) {
                if(response.isSuccessful && response.body()!=null){
                    val mdoggoResponse : List<Dog> = response.body()!!
                    madapterView.updateList(mdoggoResponse)
                }
            }
        })

    }

    private fun onClickedDoggo(dog: Dog) {
        val name_doggo = dog.name
        val bundle = Bundle()
        bundle.putString("name", name_doggo)
        val fragment = Fragment()
        fragment.setArguments(bundle)
        findNavController().navigate(R.id.navigation_to_dogdetail, bundle)
    }

   /* private fun onClickedPokemon(pokemon: Pokemon) {
        findNavController().navigate(R.id.navigation_cat)
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}