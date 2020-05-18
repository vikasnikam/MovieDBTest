package com.example.moviedb.ui.screen.latestmovielist

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentLoadmoreRefreshBinding
import com.example.moviedb.ui.base.BaseListAdapter
import com.example.moviedb.ui.base.BaseLoadMoreRefreshFragment
import com.example.moviedb.ui.screen.moviedetail.MovieDetailFragment
import kotlinx.android.synthetic.main.fragment_loadmore_refresh.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestMovieFragment :
    BaseLoadMoreRefreshFragment<FragmentLoadmoreRefreshBinding, LatestMovieViewModel, Movie>(),
    Filterable {
    override val viewModel: LatestMovieViewModel by viewModel()
    var movieList: MutableList<Movie>? = null
    var filteredList: MutableList<Movie> = java.util.ArrayList()
    override val listAdapter: BaseListAdapter<Movie, out ViewDataBinding> by lazy {
        LatestMovieAdapter(
            itemClickListener = { toMovieDetail(it) }
        )
    }


    override fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(context)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        container?.setBackgroundColor(Color.BLACK)
        viewModel.apply {
            movie.observe(viewLifecycleOwner, Observer {
                movieList= it as MutableList<Movie>?
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_view?.addTextChangedListener(getTextWatcher(search_view))
        cancel_image_view.setOnClickListener {
            search_view.text?.clear()
        }
    }


    private fun toMovieDetail(movie: Movie) {
        // go to detail page
        val fragment = MovieDetailFragment.newInstance()
        val bundle = Bundle()
        bundle.putParcelable(KEY_MOVIE_ID, movie)
        fragment.arguments = bundle
        replaceFragment(fragment, "", true, -1)
    }

    companion object {
        const val KEY_MOVIE_ID = "movie"
        fun newInstance() = LatestMovieFragment()
    }

    private fun getTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {
                when (editText) {
                    search_view -> if (s.length == 1) {
                        filter.filter(s)
                    } else if (s.length > 3) {
                        filter.filter(s)
                    }
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                 filteredList.clear()
                if (charString.isEmpty()) {
                    filteredList = movieList!!
                } else {
                    for (movie in movieList!!) {
                        if (movie.title!!.toLowerCase()==(charString.toLowerCase())) {
                            filteredList.add(movie)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                filteredList = filterResults.values as ArrayList<Movie>
                    listAdapter.submitList(filteredList)
            }
        }
    }
}