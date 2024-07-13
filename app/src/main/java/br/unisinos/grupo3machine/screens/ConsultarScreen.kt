package br.unisinos.grupo3machine.screens

import QrCodeCard
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

const val CONSULTAR_SCREEN = "ConsultarScreen"

@Composable
fun ConsultaScreen() {
    val viewModel: ConsultarViewModel = viewModel()
    val machines by viewModel.machines.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lista de equipamentos:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        if (machines.isEmpty()) {
            CircularProgressIndicator()
        }
        LazyColumn {
            items(machines.size) { message ->
                QrCodeCard(
                    name = machines[message].name,
                    qrCode = BitmapFactory.decodeByteArray(
                        machines[message].qrCode,
                        0,
                        machines[message].qrCode!!.size
                    ),
                    dateCreated = machines[message].dateInserted
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ConsultaScreenPreview() {
    ConsultaScreen()
}