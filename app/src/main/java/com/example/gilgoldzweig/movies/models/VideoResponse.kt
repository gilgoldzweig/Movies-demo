package com.example.gilgoldzweig.movies.models

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoResponse(
    @JsonProperty("id")
    val id: Int = 0,

    @JsonProperty("results")
    val results: List<Video> = emptyList()
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        ArrayList<Video>().apply { source.readList(this, Video::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeList(results)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<VideoResponse> =
            object : Parcelable.Creator<VideoResponse> {
                override fun createFromParcel(source: Parcel): VideoResponse = VideoResponse(source)
                override fun newArray(size: Int): Array<VideoResponse?> = arrayOfNulls(size)
            }
    }
}