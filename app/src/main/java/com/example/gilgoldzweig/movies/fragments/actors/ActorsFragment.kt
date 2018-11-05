package com.example.gilgoldzweig.movies.fragments.actors

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gilgoldzweig.movies.R
import com.example.gilgoldzweig.movies.fragments.actors.adapters.ActorsRecyclerAdapter
import com.example.gilgoldzweig.movies.models.Cast
import goldzweigapps.com.core.views.hide
import goldzweigapps.com.core.views.show
import kotlinx.android.synthetic.main.fragment_actors.*
import kotlinx.android.synthetic.main.fragment_actors.view.*

class ActorsFragment : Fragment(), ActorsFragmentPresenterCallback {

  private lateinit var presenter: ActorsFragmentPresenter
  private lateinit var actorsRecyclerAdapter: ActorsRecyclerAdapter
  private lateinit var safeContext: Context

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    safeContext = requireContext()
    presenter = ActorsFragmentPresenter(this)
    actorsRecyclerAdapter = ActorsRecyclerAdapter(safeContext)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_actors, container, false)
    view.actors_fragment_rcv.layoutManager = GridLayoutManager(safeContext, 3)
    view.actors_fragment_rcv.adapter = actorsRecyclerAdapter
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.observe()
  }

  override fun onActorsFound(videos: List<Cast>) {
    actorsRecyclerAdapter.setItems(ArrayList(videos))
    hideErrorViews()
  }

  override fun onActorsNotFound() {
    showErrorViews()
  }

  private fun showErrorViews() {
    actors_fragment_error_image.show()
    actors_fragment_error_text.show()
  }

  private fun hideErrorViews() {
    actors_fragment_error_image.hide()
    actors_fragment_error_text.hide()
  }
}
