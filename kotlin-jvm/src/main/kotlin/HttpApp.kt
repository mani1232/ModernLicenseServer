import io.jooby.SslOptions
import io.jooby.caffeine.CaffeineSessionStore
import io.jooby.kt.Kooby
import io.jooby.netty.NettyServer
import io.jooby.rocker.RockerModule
import webApi.v1.V1
import webApi.v2.V2
import java.util.*

class HttpApp : Kooby({
    install(NettyServer())
    install(RockerModule())

    val messages = mutableListOf("init")

    serverOptions {
        server = "Netty"
        sessionStore = CaffeineSessionStore()
        compressionLevel = 6
        ssl = SslOptions.selfSigned("X509")
        //isHttpsOnly = true // Only https?
        defaultHeaders = true
        isHttp2 = true
        securePort = 8443
        port = 8080
    }

    get("/*") {
        test.template(this.ctx.protocol, messages.toTypedArray())
    }

    ws("/chat") {
        configurer.onMessage { ws, message ->
            messages.add(message.value())
            ws.send(message.value())
        }
        configurer.onConnect { ws ->
            log.info("Connected")
        }

        configurer.onClose { ws, statusCode ->
            log.info(statusCode.code.toString())
        }

        configurer.onError { ws, cause ->
            log.info(cause.message)
        }
    }

    mount("/v1", V1())
    mount("/v2", V2()) // in dev, more features than v1
})