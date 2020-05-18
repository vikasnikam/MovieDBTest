package com.example.moviedb.ui.screen.latestmovielist

import android.widget.Filter
import androidx.recyclerview.widget.DiffUtil
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemMovieBinding
import com.example.moviedb.ui.base.BaseListAdapter
import com.example.moviedb.utils.setSingleClick
import kotlin.collections.ArrayList

class LatestMovieAdapter(
    val itemClickListener: (Movie) -> Unit = {}
) : BaseListAdapter<Movie, ItemMovieBinding>(object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }



    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_movie
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                movieListFiltered = if (charString.isEmpty()) {
                    movieList
                } else {
                    val filteredList: ArrayList<Movie> =
                        java.util.ArrayList<Movie>()
                    for (movie in movieList!!) {
                        if (movie.title!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(movie)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = movieListFiltered
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                movieListFiltered = filterResults.values as ArrayList<Movie>
                notifyDataSetChanged()
            }
        }

    }


    override fun bindFirstTime(binding: ItemMovieBinding) {
        binding.apply {
            root.setSingleClick {
                item?.apply {
                    itemClickListener(this)
                }
            }
        }
    }

fun setLatestMovieData(mutableList: MutableList<Movie>?) {
    this.movieList=mutableList
}

}