package com.example.personalcostdashboard.data

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        // Convert the stored timestamp into a Date object if present
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        // Extract the epoch time from the Date to store in the database
        return date?.time
    }
}
