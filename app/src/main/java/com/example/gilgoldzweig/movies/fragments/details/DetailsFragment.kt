package com.example.gilgoldzweig.movies.fragments.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gilgoldzweig.movies.R
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(), DetailsFragmentPresenterCallback {

  private lateinit var presenter: DetailsFragmentPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter = DetailsFragmentPresenter(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.observe()
  }

  override fun onMovieDescriptionLoaded(description: String) {
    details_fragment_description_text.text = description
  }
}
