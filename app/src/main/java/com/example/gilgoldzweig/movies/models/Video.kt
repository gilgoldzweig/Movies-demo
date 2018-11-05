package com.example.gilgoldzweig.movies.models

import android.os.Parcel
import android.os.Parcelable
import com.example.gilgoldzweig.movies.R
import com.example.gilgoldzweig.movies.fragments.trailers.adapters.TrailersRecyclerAdapter
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import goldzweigapps.com.annotations.annotations.GencyclerDataType
import goldzweigapps.com.annotations.annotations.Holder

@JsonIgnoreProperties(ignoreUnknown = true)
@Holder(R.layout.item_trailer, TrailersRecyclerAdapter::class)
data class Video(
    @JsonProperty("site")
    var site: String = "",

    @JsonProperty("size")
    var size: Int = 0,

    @JsonProperty("name")
    var name: String = "",

    @JsonProperty("id")
    var id: String = "",

    @JsonProperty("type")
    var type: String = "",

    @JsonProperty("key")
    var key: String = ""
) : Parcelable, GencyclerDataType {
    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readInt(),
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: ""
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(site)
        writeInt(size)
        writeString(name)
        writeString(id)
        writeString(type)
        writeString(key)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Video> = object : Parcelable.Creator<Video> {
            override fun createFromParcel(source: Parcel): Video = Video(source)
            override fun newArray(size: Int): Array<Video?> = arrayOfNulls(size)
        }
    }
}