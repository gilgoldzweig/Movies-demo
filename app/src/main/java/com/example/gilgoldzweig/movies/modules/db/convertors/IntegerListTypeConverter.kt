package com.example.gilgoldzweig.movies.modules.db.convertors

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Converts a list of Ints to a string and reversed
 * must be created in order to save to database
 * **/
object IntegerListTypeConverter {

  @TypeConverter
  @JvmStatic
  fun fromStringToList(data: String?): List<Int> {
    if (data == null || data.length <= 2) return emptyList() //In case the list is empty
    val dataWithTail = data.substring(1, data.length - 1) //removing tailing chars of the list '[',']'

    return dataWithTail.split(", ").map(Integer::parseInt)

  }

  @TypeConverter
  @JvmStatic
  fun fromListToString(data: List<Int>?): String = data?.toString() ?: ""


}
