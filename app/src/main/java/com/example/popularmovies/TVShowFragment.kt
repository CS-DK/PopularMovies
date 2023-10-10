package com.example.popularmovies

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val API_KEY = "5e7b3b464eabd6906bc1c9f68bcfccbb"

class TVShowFragment : Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        val layoutId = if (isLandscape) R.layout.fragment_tv_show_list else R.layout.fragment_tv_show_list
        val view = inflater.inflate(layoutId, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView){
        progressBar.show()

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client[
            "https://api.themoviedb.org/3/tv/popular",  // Use the TV show API endpoint
            params,
            object : JsonHttpResponseHandler()
            {
                override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON
                ) {
                    progressBar.hide()

                    val resultsArray : JSONArray = json.jsonObject.getJSONArray("results")

                    val gson = Gson()
                    val arrayShowType = object : TypeToken<List<TVShow>>() {}.type
                    val models: List<TVShow> = gson.fromJson(resultsArray.toString(), arrayShowType)
                    recyclerView.adapter = TVShowRecyclerViewAdapter(models, this@TVShowFragment, requireContext())

                    Log.d("ConnectedTVShowAPI", "response successful")
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    progressBar.hide()
                    t?.message?.let {
                        Log.e("ConnectedTVShowAPI", errorResponse)
                    }
                }
            }
        ]
    }

    override fun onItemClick(item: TVShow) {
        Toast.makeText(context, "TV Show: " + item.showName, Toast.LENGTH_LONG).show()
    }
}