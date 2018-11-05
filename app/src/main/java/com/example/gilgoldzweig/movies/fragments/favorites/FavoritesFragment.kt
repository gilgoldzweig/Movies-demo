package com.example.gilgoldzweig.movies.fragments.favorites


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gilgoldzweig.movies.R
import com.example.gilgoldzweig.movies.activities.info.MovieInfoActivity
import com.example.gilgoldzweig.movies.fragments.favorites.adapters.FavoritesRecyclerAdapter
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.ui.OnMovieClickedListener
import goldzweigapps.com.core.views.snackBar
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import android.support.v4.util.Pair as UtilPair


class FavoritesFragment : Fragment(), FavoritesFragmentPresenterCallback, OnMovieClickedListener {

    private lateinit var presenter: FavoritesFragmentPresenter
    private lateinit var favoritesRecyclerAdapter: FavoritesRecyclerAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var safeContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = FavoritesFragmentPresenter(this)
        safeContext = requireContext()
        favoritesRecyclerAdapter =
                FavoritesRecyclerAdapter(safeContext, onMovieClickedListener = this)
        gridLayoutManager = GridLayoutManager(safeContext, 2)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        view.favorites_fragment_rcv.layoutManager = gridLayoutManager
        view.favorites_fragment_rcv.adapter = favoritesRecyclerAdapter
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.observe()
    }

    override fun onMovieClicked(selectedMovie: Movie,
                                sharedElements: Array<android.support.v4.util.Pair<View, String>>) {

        selectedMovie.favorite = true
        presenter.selectMovie(selectedMovie)
        val intent = Intent(activity, MovieInfoActivity::class.java)

        val extra = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity!!, *sharedElements).toBundle()


        startActivity(intent, extra)
    }


    override fun onFavoritesFailedToLoad() {
        home_fragment_root.snackBar(R.string.favorites_loading_error)
    }

    override fun onFavoritesUpdated(favorites: List<Movie>) {
        favoritesRecyclerAdapter.setItems(ArrayList(favorites))
    }
}
