package com.example.gilgoldzweig.movies.activities.info.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.gilgoldzweig.movies.models.Tab
import java.lang.UnsupportedOperationException

class MovieInfoPagerAdapter(manager: FragmentManager, val tabs: List<Tab>) :
        FragmentPagerAdapter(manager) {

    override fun getCount() = tabs.size

    override fun getItem(position: Int) = tabs[position].fragment

    override fun getPageTitle(position: Int) = tabs[position].title


}