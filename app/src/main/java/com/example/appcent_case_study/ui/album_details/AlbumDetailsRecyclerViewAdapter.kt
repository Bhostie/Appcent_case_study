package com.example.appcent_case_study.ui.album_details

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appcent_case_study.R
import com.example.appcent_case_study.my_classes.Album
import com.example.appcent_case_study.my_classes.SavedTrack
import com.example.appcent_case_study.my_classes.Track
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso

class AlbumDetailsRecyclerViewAdapter(private var data: List<Track>, private var imgUrl: String, private val activity: AlbumDetails) : RecyclerView.Adapter<AlbumDetailsRecyclerViewAdapter.TrackViewHolder>() {

    // Initialize media player class for playing music
    private val mediaPlayer = MediaPlayer()


    class TrackViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var image : ImageView = itemView.findViewById(R.id.cover_image_view)
        var trackTitle : TextView = itemView.findViewById(R.id.track_title_text_view)
        var duration : TextView = itemView.findViewById(R.id.duration_text_view)
        var icon : ImageView = itemView.findViewById(R.id.icon_image_view)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            println("YOU CLICKED THIS: \n")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_track_piece, parent, false)
        return TrackViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        // Loading image view
        val imageUrl = imgUrl
        Picasso.get().load(imageUrl).into(holder.image)

        holder.trackTitle.text = data[position].title
        holder.duration.text = data[position].duration?.let { formatTime(it.toInt()) }

        if (activity.isLiked(data[position].id.toString())){
            holder.icon.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
        else{
            holder.icon.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }


        // Listener of like button
        holder.icon.setOnClickListener{
            println("YOU CLICKED HEART OF: ${data[position].title}")

            // If song already liked, remove like
            if(activity.isLiked(data[position].id.toString())){
                activity.deleteLikedSong(data[position].id.toString())
            }
            // If its not liked, add it to liked list
            else{
                val saved = SavedTrack( data[position].id, data[position].title, data[position].duration, data[position].preview, imgUrl)
                activity.saveLikedSong(saved)
            }
            notifyDataSetChanged()
        }

        // Listener of entire song cell
        holder.itemView.setOnClickListener{
            println("YOU CLICKED: ${data[position].title}")


            // Reset for previous track, so they will not collide
            mediaPlayer.reset()

            // Set the data source for the media player with the preview URL
            mediaPlayer.setDataSource(data[position].preview)

            // Prepare and start the media player
            mediaPlayer.prepare()
            mediaPlayer.start()
        }


    }

    override fun getItemCount() = data.size


    private fun formatTime(totalSeconds: Int): String {
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%d:%02d\"", minutes, seconds)
    }




}