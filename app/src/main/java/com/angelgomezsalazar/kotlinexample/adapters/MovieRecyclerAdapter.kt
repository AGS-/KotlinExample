package com.angelgomezsalazar.kotlinexample.adapters

// We import adding the keyword view so we can access the synthetic properties
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angelgomezsalazar.kotlinexample.R
import com.angelgomezsalazar.kotlinexample.activities.DetailActivity
import com.angelgomezsalazar.kotlinexample.retrofit.models.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_main.view.*

/**
 * Created by angelgomez on 8/18/16.
 */
class MovieRecyclerAdapter(val movieList: List<Movie>,
                           val genreHashMap: MutableMap<Int, String?>) :
        RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindMovie(movie: Movie) {
            // We pass context using itemView
//            Picasso.with(itemView.context)
//                    .load("http://image.tmdb.org/t/p/w500" + movie.posterPath!!)
//                    .placeholder(R.drawable.sample_movie_poster)
//                    .into(itemView.itemMainImagePoster)

            itemView.itemMainTextMovieName.text = movie.title

            var genreString = ""
            for (genreId in movie.genreIds!!) {
                if (genreString == "") {
                    genreString += genreHashMap[genreId]
                } else {
                    genreString += ", " + genreHashMap[genreId]
                }
            }
            val finalGenreString = genreString
            itemView.itemMainTextGenre.text = genreString

            itemView.itemMainTextDate.text = movie.releaseDate
            itemView.itemMainCardView.setOnClickListener {
                val detailIntent = Intent(itemView.context, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.NAME_EXTRA, movie.title)
                detailIntent.putExtra(DetailActivity.GENRE_EXTRA, finalGenreString)
                detailIntent.putExtra(DetailActivity.RELEASE_EXTRA, movie.releaseDate)
                detailIntent.putExtra(DetailActivity.IMAGE_EXTRA, movie.posterPath)
                detailIntent.putExtra(DetailActivity.OVERVIEW_EXTRA, movie.overview)
                itemView.context.startActivity(detailIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MovieRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovie(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}
