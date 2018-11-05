package com.example.gilgoldzweig.movies.consts

object Genre {
  const val ACTION = 28
  const val ADVENTURE = 12
  const val ANIMATION = 16
  const val COMEDY = 35
  const val CRIME = 80
  const val DOCUMENTARY = 99
  const val DRAMA = 18
  const val MUSIC = 10402
  const val MYSTERY = 9648
  const val ROMANCE = 10749
  const val SCIENCE_FICTION = 878
  const val TV_MOVIE = 10770
  const val THRILLER = 53
  const val WAR = 10752
  const val WESTERN = 37
  const val FAMILY = 10751
  const val FANTASY = 14
  const val FOREIGN = 10769
  const val HISTORY = 36
  const val HORROR = 27

  fun getGenreName(genreId: Int): String {

    return when (genreId) {
      ACTION -> "Action"
      ADVENTURE -> "Adventure"
      ANIMATION -> "Animation"
      COMEDY -> "Comedy"
      CRIME -> "Crime"
      DOCUMENTARY -> "Documentary"
      DRAMA -> "Drama"
      FAMILY -> "Family"
      FANTASY -> "Fantasy"
      FOREIGN -> "Foreign"
      HISTORY -> "History"
      HORROR -> "Horror"
      MUSIC -> "Music"
      MYSTERY -> "Mystery"
      ROMANCE -> "Romance"
      SCIENCE_FICTION -> "Science Fiction"
      TV_MOVIE -> "TV Movie"
      THRILLER -> "Thriller"
      WAR -> "War"
      WESTERN -> "Western"
      else -> "Unknown"
    }
  }

  fun generateGenresString(genreIds: List<Int>): String {
    var movieGenres = ""
    genreIds.forEachIndexed { index, id ->
      movieGenres += getGenreName(id)
      if (index < genreIds.size - 1) {
        movieGenres += ", "
      }
    }
    return movieGenres
  }

}
