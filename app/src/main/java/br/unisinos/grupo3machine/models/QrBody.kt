package br.unisinos.grupo3machine.models

data class QrBody(
    val frame_name: String,
    val qr_code_text: String,
    val image_format: String,
    val qr_code_logo: String,
)
