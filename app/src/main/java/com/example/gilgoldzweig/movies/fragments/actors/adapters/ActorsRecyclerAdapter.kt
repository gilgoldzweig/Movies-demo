package com.example.gilgoldzweig.movies.fragments.actors.adapters

import android.content.Context
import com.example.gilgoldzweig.movies.R
import com.example.gilgoldzweig.movies.consts.*
import com.example.gilgoldzweig.movies.models.Cast
import com.example.gilgoldzweig.movies.ui.GlideApp
import goldzweigapps.com.annotations.annotations.GencyclerDataType
import goldzweigapps.com.gencycler.recyclerAdapters.GeneratedActorsRecyclerAdapter
import org.jetbrains.anko.browse

class ActorsRecyclerAdapter(context: Context,
                            actors: ArrayList<GencyclerDataType> = ArrayList()) :
        GeneratedActorsRecyclerAdapter(context, actors) {

    private val glide = GlideApp.with(context)

    override fun CastViewHolder.onBindCastViewHolder(position: Int, actor: Cast) {
        actorItemRealNameText.text = actor.name
        actorItemCharacterNameText.text = actor.character

        glide.load("$IMAGE_LOW_RES_BASE_URL${actor.profilePath}")
                .error(R.drawable.ic_error_outline_black_36dp)
                .into(actorItemProfileImage)

        onClick {
            context.browse("$TMDB_ACTOR_BASE_URL${actor.id}")
        }
    }
}