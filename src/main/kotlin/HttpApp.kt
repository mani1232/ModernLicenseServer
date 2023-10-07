import io.jooby.SslOptions
import io.jooby.caffeine.CaffeineSessionStore
import io.jooby.kt.Kooby
import io.jooby.netty.NettyServer
import io.jooby.rocker.RockerModule
import webApi.v1.V1
import webApi.v2.V2

class HttpApp : Kooby({
    install(NettyServer())
    install(RockerModule())

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
        test.template(this.ctx.protocol)
    }

    mount("/v1", V1())
    mount("/v2", V2()) // in dev, more features than v1
})