package app.ryss.gateway.config

/**
 * Environment types of gateway service.
 */
enum class Environment {
    /**
     * Development environment.
     * - Disables sentry
     * - Stores rate-limits in memory
     */
    DEVELOPMENT,

    /**
     * Production environment
     * - Uses ratelimiting service
     * - Uses sentry
     */
    PRODUCTION
}