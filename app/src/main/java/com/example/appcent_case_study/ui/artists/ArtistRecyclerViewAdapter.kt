package com.example.appcent_case_study.ui.artists

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcent_case_study.R
import com.example.appcent_case_study.my_classes.Artist
import com.example.appcent_case_study.my_classes.ArtistItem
import com.example.appcent_case_study.ui.artist_details.ArtistDetails
import com.squareup.picasso.Picasso

class ArtistRecyclerViewAdapter(private var data: List<Artist>) : RecyclerView.Adapter<ArtistRecyclerViewAdapter.GenreViewHolder>() {

    fun updateData(input: List<Artist>){
        data = input
    }


    class GenreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var image: ImageView = itemView.findViewById(R.id.image_view)
        var title: TextView = itemView.findViewById(R.id.title_text_view)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            println("YOU CLICKED THIS: \n")
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_genre_piece, parent, false)
        return GenreViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {

        // Converting our image string into ImageView with Picasso
        val imageUrl = data[position].pictureMedium!!
        Picasso.get().load(imageUrl).into(holder.image)

        // View the Genre name
        holder.title.text = data[position].name

        // Navigate to the Artists screen when clicked
        holder.itemView.setOnClickListener{
            println("YOU CLICKED: ${data[position].name}")  //Debug

            val intent = Intent(holder.itemView.context, ArtistDetails::class.java)
            intent.putExtra("artistId", data[position].id)
            intent.putExtra("artistName",data[position].name)
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount() = data.size

}