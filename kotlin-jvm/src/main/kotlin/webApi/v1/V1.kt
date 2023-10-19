package webApi.v1

import TestData
import io.jooby.kt.Kooby
import jakarta.json.bind.JsonbBuilder
import jakarta.json.bind.JsonbConfig

class V1 : Kooby({
    val json = JsonbBuilder.create(JsonbConfig().withNullValues(true).withFormatting(true).withEncoding("UTF-8"))

    get("/*") {
        json.toJson(TestData("V1 api wrong request"))
    }
})