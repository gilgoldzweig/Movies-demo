package com.example.gilgoldzweig.movies.models

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Language(
  @JsonProperty("name")
  var name: String = ""
) : Parcelable {
  constructor(source: Parcel) : this(
    source.readString() ?: ""
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
    writeString(name)
  }

  companion object {
    @JvmField
    val CREATOR: Parcelable.Creator<Language> = object : Parcelable.Creator<Language> {
      override fun createFromParcel(source: Parcel): Language = Language(source)
      override fun newArray(size: Int): Array<Language?> = arrayOfNulls(size)
    }
  }
}
