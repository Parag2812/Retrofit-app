package com.example.myretroapp

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class myAdapter(val productArrayList: List<Product>, val context: MainActivity):
            RecyclerView.Adapter<myAdapter.myViewHolder>() {

    class myViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val title :TextView = itemView.findViewById(R.id.TitletextView)
        val description : TextView = itemView.findViewById(R.id.descriptiontextView)
        val thumbnail : ImageView = itemView.findViewById(R.id.thumbnailImageView)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.eachitem, parent, false)
        return myViewHolder(itemView)
        }

    override fun getItemCount(): Int {
        return productArrayList.size
        }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
//        holder.thumbnail.setImageResource(currentItem.thumbnail)
//        PICASSO FOR LOADING IMAGES IN IMAGEVIEW BY URL
         Picasso.get().load(currentItem.thumbnail).into(holder.thumbnail);


    }
}