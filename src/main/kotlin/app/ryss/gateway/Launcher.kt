package app.ryss.gateway

import app.ryss.gateway.config.Config
import app.ryss.gateway.config.Environment
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
suspend fun main() {
    val cfg = Config()
    val debug = cfg.environment == Environment.DEVELOPMENT
    val logLevel = Level.valueOf(cfg.logLevel)

    val rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
    rootLogger.level = logLevel

    if (!debug) {
        Sentry.init("${cfg.sentryDSN}?stacktrace.app.packages=app.ryss")
    } else {
        Sentry.init()
    }

    GatewayService(cfg).start()
}