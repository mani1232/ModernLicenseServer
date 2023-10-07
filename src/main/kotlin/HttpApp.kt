import io.jooby.SslOptions
import io.jooby.caffeine.CaffeineSessionStore
import io.jooby.kt.Kooby
import io.jooby.netty.NettyServer
import io.jooby.rocker.RockerModule
import jakarta.json.bind.JsonbBuilder
import jakarta.json.bind.JsonbConfig

class HttpApp : Kooby({
    install(NettyServer())
    val jsonb = JsonbBuilder.create(JsonbConfig().withNullValues(true).withFormatting(true).withEncoding("UTF-8"))
    install(RockerModule())

    serverOptions {
        server = "Netty"
        sessionStore = CaffeineSessionStore()
        compressionLevel = 6
        ssl = SslOptions.selfSigned("X509")
        //isHttpsOnly = true
        defaultHeaders = true
        isHttp2 = true
        securePort = 8443
        port = 8080
    }

    coroutine {
        get("/test") {
            "ok"
        }
        get("/*") {
            test.template(this.ctx.protocol)
        }
        get("/object") { //Json example
            jsonb.toJson(TestData("TestTExt"))
        }
    }
})