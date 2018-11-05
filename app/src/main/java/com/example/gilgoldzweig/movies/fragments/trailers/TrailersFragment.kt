package com.example.gilgoldzweig.movies.fragments.trailers

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gilgoldzweig.movies.R
import com.example.gilgoldzweig.movies.fragments.trailers.adapters.TrailersRecyclerAdapter
import com.example.gilgoldzweig.movies.models.Video
import goldzweigapps.com.core.views.hide
import goldzweigapps.com.core.views.show
import kotlinx.android.synthetic.main.fragment_trailers.*
import kotlinx.android.synthetic.main.fragment_trailers.view.*

class TrailersFragment : Fragment(), TrailersFragmentPresenterCallback {

  private lateinit var presenter: TrailersFragmentPresenter
  private lateinit var trailersRecyclerAdapter: TrailersRecyclerAdapter
  private lateinit var safeContext: Context

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    safeContext = requireContext()
    presenter = TrailersFragmentPresenter(this)
    trailersRecyclerAdapter = TrailersRecyclerAdapter(safeContext)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_trailers, container, false)
    view.trailers_fragment_rcv.layoutManager = LinearLayoutManager(safeContext)
    view.trailers_fragment_rcv.adapter = trailersRecyclerAdapter
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.observe()
  }
  override fun onTrailersFound(videos: List<Video>) {
    trailersRecyclerAdapter.setItems(ArrayList(videos))
    hideErrorViews()
  }

  override fun onTrailersNotFound() {
    showErrorViews()
  }

  fun showErrorViews() {
    trailers_fragment_error_image.show()
    trailers_fragment_error_text.show()
  }

  fun hideErrorViews() {
    trailers_fragment_error_image.hide()
    trailers_fragment_error_text.hide()
  }
}
