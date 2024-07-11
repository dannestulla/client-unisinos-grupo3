package br.unisinos.grupo3machine
import br.unisinos.grupo3machine.models.Machine
import br.unisinos.grupo3machine.models.QrBody
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import io.ktor.utils.io.core.readBytes

class MachineRepository {
    private val http = setHttpClient()

    private val apiUrl = "http://localhost:8080/machines"
    private val qrCodeUrl = "https://api.qr-code-generator.com/v1/create?access-token=${apiToken}"

    suspend fun save(machine: Machine) {
        http.post(apiUrl) {
            setBody(machine)
        }
    }

    suspend fun getQrCode(machineName: String): ByteArray  {
        val response = http.post(qrCodeUrl) {
            contentType(ContentType.Application.Json)
            setBody(
                QrBody(
                    "no-frame",
                    machineName,
                    "SVG",
                    "scan-me-square"
                )
            )
        }
        return response.readBytes()
    }

    private fun setHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                gson()
            }
    }
}
}