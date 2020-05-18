package com.example.moviedb.ui.screen.moviedetail

import androidx.recyclerview.widget.DiffUtil
import com.example.moviedb.R
import com.example.moviedb.data.model.Crew
import com.example.moviedb.databinding.ItemCastBinding
import com.example.moviedb.ui.base.BaseListAdapter


class CrewAdapter() : BaseListAdapter<Crew, ItemCastBinding>(object : DiffUtil.ItemCallback<Crew>() {
    override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
        return oldItem == newItem
    }
}) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_crew
    }

}