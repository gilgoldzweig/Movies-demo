package com.example.gilgoldzweig.movies.models

import android.os.Parcel
import android.os.Parcelable
import com.example.gilgoldzweig.movies.R
import com.example.gilgoldzweig.movies.fragments.actors.adapters.ActorsRecyclerAdapter
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import goldzweigapps.com.annotations.annotations.GencyclerDataType
import goldzweigapps.com.annotations.annotations.Holder

@JsonIgnoreProperties(ignoreUnknown = true)
@Holder(R.layout.item_actor, ActorsRecyclerAdapter::class)
data class Cast(
  @JsonProperty("cast_id")
  var castId: Int = 0,
  @JsonProperty("character")
  var character: String = "",
  @JsonProperty("gender")
  var gender: Int = 0,
  @JsonProperty("credit_id")
  var creditId: String = "",
  @JsonProperty("name")
  var name: String = "",
  @JsonProperty("profile_path", required = false)
  var profilePath: String? = "",
  @JsonProperty("id")
  var id: Int = 0,
  @JsonProperty("order")
  var order: Int = 0
) : Parcelable, GencyclerDataType {
  constructor(source: Parcel) : this(
    source.readInt(),
    source.readString() ?: "",
    source.readInt(),
    source.readString() ?: "",
    source.readString() ?: "",
    source.readString() ?: "",
    source.readInt(),
    source.readInt()
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
    writeInt(castId)
    writeString(character)
    writeInt(gender)
    writeString(creditId)
    writeString(name)
    writeString(profilePath)
    writeInt(id)
    writeInt(order)
  }

  companion object {
    @JvmField
    val CREATOR: Parcelable.Creator<Cast> = object : Parcelable.Creator<Cast> {
      override fun createFromParcel(source: Parcel): Cast = Cast(source)
      override fun newArray(size: Int): Array<Cast?> = arrayOfNulls(size)
    }
  }
}
