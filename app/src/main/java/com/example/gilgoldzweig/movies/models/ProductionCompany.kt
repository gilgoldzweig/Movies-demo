package com.example.gilgoldzweig.movies.models

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductionCompany(
  @JsonProperty("logo_path")
  var logoPath: String? = "",
  @JsonProperty("name")
  var name: String = "",
  @JsonProperty("id")
  var id: Int = 0,
  @JsonProperty("origin_country")
  var originCountry: String = ""
) : Parcelable {
  constructor(source: Parcel) : this(
    source.readString(),
    source.readString() ?: "",
    source.readInt(),
    source.readString() ?: ""
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
    writeString(logoPath)
    writeString(name)
    writeInt(id)
    writeString(originCountry)
  }

  companion object {
    @JvmField
    val CREATOR: Parcelable.Creator<ProductionCompany> =
      object : Parcelable.Creator<ProductionCompany> {
        override fun createFromParcel(source: Parcel): ProductionCompany = ProductionCompany(source)
        override fun newArray(size: Int): Array<ProductionCompany?> = arrayOfNulls(size)
      }
  }
}
