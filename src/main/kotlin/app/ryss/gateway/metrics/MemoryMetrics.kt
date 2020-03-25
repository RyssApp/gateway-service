package app.ryss.gateway.metrics

import com.influxdb.client.InfluxDBClient
import com.influxdb.client.domain.WritePrecision
import com.influxdb.client.write.Point
import mu.KotlinLogging
import java.net.InetAddress
import java.time.Instant
import java.util.*
import kotlin.concurrent.schedule

/**
 * MemoryMetrics posts the amount of total heap and the amount of allocated heap every second to InfluxDB.
 */
class MemoryMetrics(client: InfluxDBClient, bucket: String, org: String) {

    private val log = KotlinLogging.logger { }

    init {
        Timer().schedule(0, 1000) {
            client.writeApi.writePoint(bucket, org, Point.measurement("memory").apply {
                addTag("host", InetAddress.getLocalHost().hostName)
                addField("total", Runtime.getRuntime().totalMemory())
                addField("allocated", Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
                time(Instant.now().toEpochMilli(), WritePrecision.MS)
                return@apply
            })
            log.debug { "Posted Memory Metrics." }
        }
    }
}