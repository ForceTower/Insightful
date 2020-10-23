package dev.forcetower.instascan.core.source.local

import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateConverters {
    val ZDT_PATTERN: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")

    @TypeConverter
    @JvmStatic
    fun zonedDateTimeToString(date: ZonedDateTime?): String? {
        return date?.format(ZDT_PATTERN)
    }

    @TypeConverter
    @JvmStatic
    fun stringToZonedDateTime(string: String?): ZonedDateTime? {
        string ?: return null
        return ZonedDateTime.parse(string, ZDT_PATTERN)
    }
}