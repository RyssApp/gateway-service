package app.ryss.gateway

import app.ryss.gateway.core.GatewayService
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.sentry.Sentry
import org.slf4j.LoggerFactory

/**
 * Gateway entry point.
 */
fun main() {
    val debug = System.getenv("ENVIRONMENT") == "DEVELOPMENT"
    val logLevel = Level.valueOf(System.getenv("GATEWAY_SERVICE_LOG_LEVEL"))
    val enableGraphiQL = System.getenv("GATEWAY_SERVICE_ENABLE_GRAPHQL") == "true"

    val rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
    rootLogger.level = logLevel

    if (!debug) {
        Sentry.init(
            "${System.getenv("GATEWAY_SERVICE_SENTRY_DSN")}?stacktrace.app.packages=app.ryss"
        )
    } else {
        Sentry.init()
    }

    val application = GatewayService(enableGraphiQL, debug)
    val port = System.getenv("GATEWAY_SERVICE_PORT")?.toInt() ?: 3500
    embeddedServer(Netty, module = application::mainModule, port = port).start(wait = true)
}