package com.example.gilgoldzweig.movies.fragments.movies


import android.arch.paging.PagedList
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
import com.example.gilgoldzweig.movies.fragments.movies.adapters.MoviesRecyclerAdapter
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.modules.network.state.State
import com.example.gilgoldzweig.movies.ui.OnMovieClickedListener
import goldzweigapps.com.core.views.ActionResId
import goldzweigapps.com.core.views.snackBar
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.view.*
import android.support.v4.util.Pair as UtilPair

class MoviesFragment : Fragment(), MoviesFragmentPresenterCallback, OnMovieClickedListener {

	private lateinit var presenter: MoviesFragmentPresenter
	private lateinit var moviesRecyclerAdapter: MoviesRecyclerAdapter
	private lateinit var gridLayoutManager: GridLayoutManager
	private lateinit var safeContext: Context

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		presenter = MoviesFragmentPresenter(this)
		safeContext = requireContext()
		moviesRecyclerAdapter = MoviesRecyclerAdapter(safeContext, this)

	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_movies, container, false)

		//Creating the item again because of a bug with how fragments work
		//Bug: java.lang.IllegalArgumentException: LayoutManager android.support.v7.widget.GridLayoutManager@453e687 is already attached to a RecyclerView
		//StackOverFlow: https://stackoverflow.com/questions/30528206/layoutmanager-is-already-attached-to-a-recyclerview-error
		gridLayoutManager = GridLayoutManager(safeContext, 2)

		gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
			override fun getSpanSize(position: Int) =
				if (moviesRecyclerAdapter.isFooter(position)) 2 else 1
		}

		view.movies_fragment_rcv.layoutManager = gridLayoutManager
		view.movies_fragment_rcv.adapter = moviesRecyclerAdapter
		view.movies_fragment_refresh_layout.setOnRefreshListener {
			presenter.reload()
		}
		return view
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		presenter.observe()


	}

	override fun onMovieClicked(
		selectedMovie: Movie,
		sharedElements: Array<UtilPair<View, String>>) {
		//I know there is bug with the starting of the activity which skips around 50 frames
		//I don't have time to fix it
		presenter.selectMovie(selectedMovie)
		val intent = Intent(activity, MovieInfoActivity::class.java)

		val extra = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, *sharedElements)
			.toBundle()

		startActivity(intent, extra)
	}

	override fun onMoviesInitiating() {
		movies_fragment_refresh_layout.isRefreshing = true
	}

	override fun onMoviesFailedToLoad() {
		movies_fragment_refresh_layout.isRefreshing = false
		movies_fragment_root.snackBar(
			R.string.movies_loading_error, action = ActionResId(R.string.retry,
				View.OnClickListener {
					presenter.retryLastAction()
				})
		)
	}

	override fun onMoviesUpdated(movies: PagedList<Movie>) {
		moviesRecyclerAdapter.submitList(movies)
		if (movies_fragment_refresh_layout.isRefreshing) {
			movies_fragment_refresh_layout.isRefreshing = false
		}
	}

	override fun onStateUpdated(state: State) {
		when (state) {
			State.RELOADING_ERROR -> {
				movies_fragment_rcv.recycledViewPool.clear()
				moviesRecyclerAdapter.notifyDataSetChanged()
			}
			State.ERROR -> {
				if (moviesRecyclerAdapter.itemCount == 0) {
					moviesRecyclerAdapter.currentState = state
				}
			}
			else -> {
				moviesRecyclerAdapter.currentState = state
			}
		}
		if (state == State.RELOADING_ERROR) {

		} else {

		}

	}

}
