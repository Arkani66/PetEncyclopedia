package com.example.petencyclopedia.ui.Dog.dogAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petencyclopedia.R
import com.example.petencyclopedia.ui.Dog.DogSuccess
import com.example.petencyclopedia.ui.api.PetApplication.Companion.context

/**
 *Created by $(USER) on $(DATE).
 */
class DoggoAdaptater(private var dataSet : List<Dog>, var listener: ((Dog) -> Unit)? = null) : RecyclerView.Adapter<DoggoAdaptater.ViewHolder>(){

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView : TextView
        val imagedog : ImageView
        init {
            //Define clickListener for the ViewHolder's View
            /*textView.setOnClickListener(){
           }*/
            textView = view.findViewById(R.id.doggo_name)
            imagedog = view.findViewById(R.id.doggo_item_image)
        }
    }

    fun updateList(list: List<Dog>){
        dataSet = list
        //notifie que la liste a changer et qu'il faut mettre à jour la view
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        //Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.doggo_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //Get element from your dataset at this position and replace the
        //contents of the view widget with that element
        val dog :Dog = dataSet[position]
        viewHolder.textView.text = dog.name
        viewHolder.itemView.setOnClickListener{
            listener?.invoke(dog)
        }
        if(dog.image.url != null){
            Glide.with(viewHolder.itemView.context)
                .load(dog.image.url)
                .centerCrop()
                .into(viewHolder.imagedog)
        }else {
            Glide.with(viewHolder.itemView.context)
                .load(R.drawable.default_dog)
                .centerCrop()
                .into(viewHolder.imagedog)
        }

    }

    override fun getItemCount() = dataSet.size

}