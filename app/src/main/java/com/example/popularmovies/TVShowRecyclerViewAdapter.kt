package com.example.popularmovies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class TVShowRecyclerViewAdapter(
    private val shows: List<TVShow>,
    private val mListener: OnListFragmentInteractionListener?,
    private val context: Context
) : RecyclerView.Adapter<TVShowRecyclerViewAdapter.ShowViewHolder>() {

    inner class ShowViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: TVShow? = null
        val mShowTitle: TextView = mView.findViewById<View>(R.id.show_name) as TextView
        val mShowDescription: TextView = mView.findViewById<View>(R.id.show_description) as TextView
        val mShowImage: ImageView = mView.findViewById<View>(R.id.show_image) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_tv_show, parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = shows[position]

        holder.mItem = show
        holder.mShowTitle.text = show.showName
        holder.mShowDescription.text = show.showDescription

        val baseUrl = "https://image.tmdb.org/t/p/w500/"
        val imageUrl = baseUrl + show.showImage
        Glide.with(holder.mView).load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground).centerInside().centerCrop().transform(RoundedCorners(30)).into(holder.mShowImage)

        holder.mView.setOnClickListener {
            val intent = Intent(context, ShowDetailActivity::class.java)
            intent.putExtra("show_name", show.showName)
            intent.putExtra("show_description", show.showDescription)
            intent.putExtra("show_image", show.showImage)
            intent.putExtra("show_release_date", show.showDate)
            intent.putExtra("show_popularity", show.showPopularity)
            intent.putExtra("show_average_vote", show.showAvgVote)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return shows.size
    }
}