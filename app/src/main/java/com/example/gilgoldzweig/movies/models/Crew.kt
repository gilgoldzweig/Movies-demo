package com.example.gilgoldzweig.movies.models

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Crew(
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
  @JsonProperty("department")
  var department: String = "",
  @JsonProperty("job")
  var job: String = ""
) : Parcelable {
  constructor(source: Parcel) : this(
    source.readInt(),
    source.readString() ?: "",
    source.readString() ?: "",
    source.readString() ?: "",
    source.readInt(),
    source.readString() ?: "",
    source.readString() ?: ""
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
    writeInt(gender)
    writeString(creditId)
    writeString(name)
    writeString(profilePath)
    writeInt(id)
    writeString(department)
    writeString(job)
  }

  companion object {
    @JvmField
    val CREATOR: Parcelable.Creator<Crew> = object : Parcelable.Creator<Crew> {
      override fun createFromParcel(source: Parcel): Crew = Crew(source)
      override fun newArray(size: Int): Array<Crew?> = arrayOfNulls(size)
    }
  }
}
