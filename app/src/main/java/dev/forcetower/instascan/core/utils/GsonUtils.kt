package dev.forcetower.instascan.core.utils

import com.google.gson.JsonDeserializer
import dev.forcetower.instascan.core.source.local.DateConverters
import java.time.ZonedDateTime

object GsonUtils {
    @JvmStatic
    val ZDT_DESERIALIZER: JsonDeserializer<ZonedDateTime> = JsonDeserializer { json, _, _ ->
        val jsonPrimitive = json.asJsonPrimitive
        try {
            val string = jsonPrimitive.asString
            ZonedDateTime.parse(string, DateConverters.ZDT_PATTERN)
        } catch (e: Throwable) {
            null
        }
    }
}