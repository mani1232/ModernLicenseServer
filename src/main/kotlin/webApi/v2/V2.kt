package webApi.v2

import io.jooby.kt.Kooby
import jakarta.json.bind.JsonbBuilder
import jakarta.json.bind.JsonbConfig

class V2 : Kooby({
    val json = JsonbBuilder.create(JsonbConfig().withNullValues(true).withFormatting(true).withEncoding("UTF-8"))

    get("/*") {
        json.toJson("Api v2 in dev")
    }
})