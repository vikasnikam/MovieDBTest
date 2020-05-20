package com.example.moviedb.ui.screen.latestmovielist

import androidx.recyclerview.widget.DiffUtil
import com.example.moviedb.R
import com.example.moviedb.data.model.Cast
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.SearchedMovie
import com.example.moviedb.databinding.ItemCastBinding
import com.example.moviedb.databinding.ItemMovieBinding
import com.example.moviedb.databinding.ItemRecentMovieBinding
import com.example.moviedb.ui.base.BaseListAdapter
import com.example.moviedb.utils.setSingleClick

class RecentMovieAdapter( val searchedMovieItemClickListener: (SearchedMovie) -> Unit = {}): BaseListAdapter<SearchedMovie, ItemRecentMovieBinding>(object : DiffUtil.ItemCallback<SearchedMovie>() {
    override fun areItemsTheSame(oldItem: SearchedMovie, newItem: SearchedMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchedMovie, newItem: SearchedMovie): Boolean {
        return oldItem == newItem
    }
}) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_recent_movie
    }

    override fun bindFirstTime(binding: ItemRecentMovieBinding) {
        binding.apply {
            root.setSingleClick {
                item?.apply {
                    searchedMovieItemClickListener(this)
                }
            }
        }
    }

}