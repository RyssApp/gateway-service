package app.ryss.gateway.config

import ch.qos.logback.classic.Level
import io.github.cdimascio.dotenv.dotenv

/**
 * Configuration of gateway service.
 */
class Config {

    private val dotenv = dotenv {
        ignoreIfMissing = true
    }

    /**
     * The [Environment] of the current instance.
     */
    val environment: Environment =
        Environment.valueOf(dotenv["${PREFIX}ENVIRONMENT"] ?: Environment.PRODUCTION.toString())

    /**
     * The [Level] of the logger.
     */
    val logLevel: String = dotenv["${PREFIX}LOG_LEVEL"] ?: Level.INFO.levelStr

    /**
     * Whether metric exporting should be enabled-
     */
    val enableMetrics: Boolean = dotenv["${PREFIX}ENABLE_METRICS"]?.toBoolean() ?: true

    /**
     * The host address of InfluxDB.
     */
    val influxDbAddress: String = dotenv["${PREFIX}INFLUXDB_ADDRESS"] ?: "http://localhost:9999"

    /**
     * The token for InfluxDB.
     */
    val influxDbToken: String = dotenv["${PREFIX}INFLUXDB_TOKEN"] ?: ""

    /**
     * The InfluxDB organization name.
     */
    val influxDbOrg: String = dotenv["${PREFIX}INFLUXDB_ORG"] ?: ""

    /**
     * The InfluxDB bucket name.
     */
    val influxDbBucket: String = dotenv["${PREFIX}INFLUXDB_BUCKET"] ?: ""

    /**
     * Whether the GraphiQL UI should be enabled.
     */
    val enableGraphiQL: Boolean = dotenv["${PREFIX}ENABLE_GRAPHIQL"]?.toBoolean() ?: false

    /**
     * The sentry DSN.
     */
    val sentryDSN: String = dotenv["${PREFIX}SENTRY_DSN"] ?: ""

    /**
     * The port the webserver should run on.
     */
    val port: Int = dotenv["${PREFIX}PORT"]?.toInt() ?: 3500

    companion object {
        private const val PREFIX = "GATEWAY_SERVICE_"
    }
}