package com.example.gilgoldzweig.movies.activities.info


import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.gilgoldzweig.movies.R
import com.example.gilgoldzweig.movies.models.Movie
import com.example.gilgoldzweig.movies.ui.AppBarStateChangeListener
import kotlinx.android.synthetic.main.activity_movie_info.*
import android.view.WindowManager
import android.support.v4.content.ContextCompat
import com.example.gilgoldzweig.movies.activities.info.adapters.MovieInfoPagerAdapter
import com.example.gilgoldzweig.movies.consts.*
import com.example.gilgoldzweig.movies.fragments.actors.ActorsFragment
import com.example.gilgoldzweig.movies.fragments.details.DetailsFragment
import com.example.gilgoldzweig.movies.fragments.trailers.TrailersFragment
import com.example.gilgoldzweig.movies.models.Tab
import org.jetbrains.anko.share


class MovieInfoActivity : AppCompatActivity(), MovieInfoActivityPresenterCallback {

    private lateinit var presenter: MovieInfoActivityPresenter
    private var selectedMovie: Movie? = null

    private val tabs = listOf(Tab("Overview", DetailsFragment()),
            Tab("Actors", ActorsFragment()),
            Tab("Trailers", TrailersFragment()))

    private lateinit var tabsPagerAdapter: MovieInfoPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)
        presenter = MovieInfoActivityPresenter(this)

        movie_info_activity_collapsing_toolbar.setExpandedTitleColor(ContextCompat.getColor(this, R.color.clear))
        movie_info_activity_toolbar.navigationIcon?.isAutoMirrored = true
        movie_info_activity_toolbar.inflateMenu(R.menu.movie_info_menu)
        movie_info_activity_toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.movie_info_menu_share ->
                    share("$TMDB_MOVIE_BASE_URL${selectedMovie?.id ?: ""}")
            }
            false
        }
        movie_info_activity_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        movie_info_activity_favorite_fab.setOnClickListener {
            if (it.isSelected) {
                presenter.removeFromFavorites()
                it.isSelected = false
            } else {
                presenter.addToFavorites()
                it.isSelected = true
            }
        }

        movie_info_activity_appbar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {

            override fun onExpanded(appBarLayout: AppBarLayout) {
                movie_info_activity_favorite_fab.show()

            }

            override fun onCollapsed(appBarLayout: AppBarLayout) {
                movie_info_activity_favorite_fab.hide()
                movie_info_activity_toolbar.title = selectedMovie?.title
            }

            override fun onIdle(appBarLayout: AppBarLayout) = Unit
        })

        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        tabsPagerAdapter = MovieInfoPagerAdapter(supportFragmentManager, tabs)

        movie_info_activity_pager.adapter = tabsPagerAdapter
        movie_info_activity_tab_layout.setupWithViewPager(movie_info_activity_pager)
    }


    override fun onMovieUpdated(movie: Movie) {
        selectedMovie = movie
        movie_info_activity_title.text = movie.title
        movie_info_activity_genre.text = Genre.generateGenresString(movie.genreIds)

        Glide.with(this)
                .load("$IMAGE_LOW_RES_BASE_URL${movie.posterPath}")
                .into(movie_info_activity_collapsing_poster_image)

        Glide.with(this)
                .load("$IMAGE_BASE_URL${movie.backdropPath}")
                .into(movie_info_activity_collapsing_image)

    }

    override fun onMovieFavoriteStateFound(favorite: Boolean) {
        movie_info_activity_favorite_fab.isSelected = favorite
    }
}
