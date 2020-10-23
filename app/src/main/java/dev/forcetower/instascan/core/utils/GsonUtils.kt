package dev.forcetower.instascan.core.utils

import com.google.gson.JsonDeserializer
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object GsonUtils {
    @JvmStatic
    val ZDT_DESERIALIZER: JsonDeserializer<ZonedDateTime> = JsonDeserializer { json, _, _ ->
        val jsonPrimitive = json.asJsonPrimitive
        try {
            val string = jsonPrimitive.asString
            val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
            ZonedDateTime.parse(string, parser)
        } catch (e: Throwable) {
            null
        }
    }
}