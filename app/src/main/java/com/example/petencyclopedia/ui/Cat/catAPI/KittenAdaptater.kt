package com.example.petencyclopedia.ui.Cat.catAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petencyclopedia.R

class KittenAdaptater(private var dataSet : List<Cat>, var listener: ((Cat) -> Unit)? = null) : RecyclerView.Adapter<KittenAdaptater.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView : TextView = view.findViewById(R.id.cat_name)
        init {
            //Define clickListener for the ViewHolder's View
            /*textView.setOnClickListener(){

            }*/
        }
    }

    fun updateList(list: List<Cat>){
        dataSet = list
        //notifie que la liste a changer et qu'il faut mettre à jour la view
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        //Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cat_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //Get element from your dataset at this position and replace the
        //contents of the view widget with that element
        val cat : Cat = dataSet[position]
        viewHolder.textView.text = cat.name
        viewHolder.itemView.setOnClickListener{
            listener?.invoke(cat)
        }
    }

    override fun getItemCount() = dataSet.size

}
