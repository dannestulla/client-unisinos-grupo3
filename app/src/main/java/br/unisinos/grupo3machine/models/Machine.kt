package br.unisinos.grupo3machine.models

import android.graphics.Bitmap
import java.util.Date

data class Machine(
    val name: String = "",
    val qrCode: ByteArray? = null,
    val dateInserted: String = Date().toString()
)

data class ResponseMachine(
    val name: String = "",
    var qrCode: String? = null,
    val dateInserted: String = Date().toString()
)
