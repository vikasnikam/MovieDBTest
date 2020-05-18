package com.example.moviedb.ui.screen.moviedetail

import android.widget.Filter
import androidx.recyclerview.widget.DiffUtil
import com.example.moviedb.R
import com.example.moviedb.data.model.Cast
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemCastBinding
import com.example.moviedb.ui.base.BaseListAdapter

class SimilarMovieAdapter: BaseListAdapter<Movie, ItemCastBinding>(object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_similar_movie
    }
    override fun getFilter(): Filter? {
        return null
    }
}

