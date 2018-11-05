package com.example.gilgoldzweig.movies.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MovieDetails(
  @JsonProperty("overview")
  var overview: String = "",
  @JsonProperty("original_language")
  var originalLanguage: String? = "",
  @JsonProperty("original_title")
  var originalTitle: String? = "",
  @JsonProperty("imdb_id")
  var imdbId: String? = "",
  @JsonProperty("runtime")
  var runtime: Int? = 0,
  @JsonProperty("video")
  var video: Boolean? = false,
  @JsonProperty("title")
  var title: String = "",
  @JsonProperty("poster_path")
  var posterPath: String? = "",
  @JsonProperty("backdrop_path")
  var backdropPath: String? = "",
  @JsonProperty("spoken_languages")
  var spokenLanguages: List<Language>? = emptyList(),
  @JsonProperty("revenue")
  var revenue: Int = 0,
  @JsonProperty("casts")
  var castResponse: CastResponse,
  @JsonProperty("production_companies")
  var productionCompanies: List<ProductionCompany>? = emptyList(),
  @JsonProperty("release_date")
  var releaseDate: String = "",
  @JsonProperty("popularity")
  var popularity: Double? = 0.0,
  @JsonProperty("vote_average")
  var voteAverage: Double = 0.0,
  @JsonProperty("tagline")
  var tagline: String = "",
  @JsonProperty("id")
  var id: Int = 0,
  @JsonProperty("adult")
  var adult: Boolean = false,
  @JsonProperty("vote_count")
  var voteCount: Int = 0,
  @JsonProperty("budget")
  var budget: Int = 0,
  @JsonProperty("homepage", required = false)
  var homepage: String? = "",
  @JsonProperty("videos")
  var videos: VideoResponse? = VideoResponse()
)
