package com.example.gilgoldzweig.movies.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.example.gilgoldzweig.movies.R
import com.example.gilgoldzweig.movies.fragments.favorites.adapters.FavoritesRecyclerAdapter
import com.fasterxml.jackson.annotation.JsonProperty
import goldzweigapps.com.annotations.annotations.GencyclerDataType
import goldzweigapps.com.annotations.annotations.Holder

@Entity(tableName = "movies")
@Holder(R.layout.item_movie, FavoritesRecyclerAdapter::class)
data class Movie(
    @JsonProperty("overview")
    var overview: String = "",

    @JsonProperty("original_language")
    var originalLanguage: String = "",

    @JsonProperty("original_title")
    var originalTitle: String = "",

    @JsonProperty("video")
    var video: Boolean = false,

    @JsonProperty("title")
    var title: String = "",

    @JsonProperty("genre_ids")
    var genreIds: List<Int> = emptyList(),

    @JsonProperty("poster_path", required = false, defaultValue = "")
    var posterPath: String? = "",

    @JsonProperty("backdrop_path", required = false, defaultValue = "")
    var backdropPath: String? = "",

    @JsonProperty("release_date")
    var releaseDate: String = "",

    @JsonProperty("vote_average")
    var voteAverage: Double = 0.0,

    @JsonProperty("popularity")
    var popularity: Double = 0.0,

    @JsonProperty("id")
    @PrimaryKey
    var id: Int = 0,

    @JsonProperty("adult")
    var adult: Boolean = false,

    @JsonProperty("vote_count")
    var voteCount: Int = 0,

    @JsonProperty(required = false)
    var favorite: Boolean = false

) : GencyclerDataType, Parcelable {
    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: "",
        1 == source.readInt(),
        source.readString() ?: "",
        ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
        source.readString(),
        source.readString(),
        source.readString() ?: "",
        source.readDouble(),
        source.readDouble(),
        source.readInt(),
        1 == source.readInt(),
        source.readInt(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(overview)
        writeString(originalLanguage)
        writeString(originalTitle)
        writeInt((if (video) 1 else 0))
        writeString(title)
        writeList(genreIds)
        writeString(posterPath)
        writeString(backdropPath)
        writeString(releaseDate)
        writeDouble(voteAverage)
        writeDouble(popularity)
        writeInt(id)
        writeInt((if (adult) 1 else 0))
        writeInt(voteCount)
        writeInt((if (favorite) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}