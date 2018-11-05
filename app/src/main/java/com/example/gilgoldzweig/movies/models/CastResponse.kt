package com.example.gilgoldzweig.movies.models

import com.fasterxml.jackson.annotation.JsonProperty

data class CastResponse(
  @JsonProperty("cast")
  var cast: List<Cast>? = emptyList(),
  @JsonProperty("crew")
  var crew: List<Crew>? = emptyList()
)
