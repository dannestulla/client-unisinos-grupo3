package br.unisinos.grupo3machine

import br.unisinos.grupo3machine.models.Machine
import br.unisinos.grupo3machine.models.QrBody
import br.unisinos.grupo3machine.models.ResponseMachine
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson

class MachineRepository {
    private val http = setHttpClient()
    private val apiUrl = "https://machines-api-3cfaff9bf0d3.herokuapp.com/"
    private val localUrl = "http://localhost:8080/"
    private val qrCodeUrl = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data="

    suspend fun save(machine: Machine) {
        http.post(apiUrl+"machines") {
            contentType(ContentType.Application.Json)
            setBody(machine)
        }
    }

    suspend fun getQrCode(machineName: String): ByteArray {
        val response = http.get(qrCodeUrl+machineName)
        return response.readBytes()
    }

    suspend fun getAll(): List<ResponseMachine> {
        val response : List<ResponseMachine> = http.get(apiUrl+"machines").body()
        return response
    }

    private fun setHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                gson()
            }
        }
    }
}