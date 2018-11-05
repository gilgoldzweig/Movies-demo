package com.example.gilgoldzweig.movies.fragments.trailers.adapters

import android.content.Context
import com.example.gilgoldzweig.movies.consts.YOUTUBE_VIDEO_BASE_URL
import com.example.gilgoldzweig.movies.consts.YOUTUBE_VIDEO_THUMBNAIL_MID_RES
import com.example.gilgoldzweig.movies.consts.YOUTUBE_VIDEO_THUMBNAIL_URL
import com.example.gilgoldzweig.movies.models.Video
import com.example.gilgoldzweig.movies.ui.GlideApp
import goldzweigapps.com.annotations.annotations.GencyclerDataType
import goldzweigapps.com.gencycler.recyclerAdapters.GeneratedTrailersRecyclerAdapter
import org.jetbrains.anko.browse

class TrailersRecyclerAdapter(context: Context,
                              trailers: ArrayList<GencyclerDataType> = ArrayList()) :
        GeneratedTrailersRecyclerAdapter(context, trailers) {

    private val glide = GlideApp.with(context)

    override fun VideoViewHolder.onBindVideoViewHolder(position: Int, video: Video) {
        trailerItemTitleText.text = video.name

        glide.load("$YOUTUBE_VIDEO_THUMBNAIL_URL${video.key}$YOUTUBE_VIDEO_THUMBNAIL_MID_RES")
                .into(trailerItemPosterImage)

        onClick {
            context.browse("$YOUTUBE_VIDEO_BASE_URL${video.key}")
        }
    }
}