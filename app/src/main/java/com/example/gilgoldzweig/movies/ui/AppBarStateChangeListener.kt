package com.example.gilgoldzweig.movies.ui

import android.support.design.widget.AppBarLayout

abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {

    private var mCurrentState: Int = STATE_EXPANDED

    override fun onOffsetChanged(appBarLayout: AppBarLayout, offset: Int) {
        when {
            offset == 0 -> {
                if (mCurrentState != STATE_EXPANDED) {
                    onExpanded(appBarLayout)
                }
                mCurrentState = STATE_EXPANDED
            }
            Math.abs(offset) >= appBarLayout.totalScrollRange -> {
                if (mCurrentState != STATE_COLLAPSED) {
                    onCollapsed(appBarLayout)
                }
                mCurrentState = STATE_COLLAPSED
            }
            else -> {
                if (mCurrentState != STATE_IDLE) {
                    onIdle(appBarLayout)
                }
                mCurrentState = STATE_IDLE
            }
        }
    }

    abstract fun onExpanded(appBarLayout: AppBarLayout)
    abstract fun onCollapsed(appBarLayout: AppBarLayout)
    abstract fun onIdle(appBarLayout: AppBarLayout)

    companion object {
        const val STATE_IDLE = 0
        const val STATE_EXPANDED = 1
        const val STATE_COLLAPSED = 2

    }

}