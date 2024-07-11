package br.unisinos.grupo3machine.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.unisinos.grupo3machine.models.Machine
import br.unisinos.grupo3machine.MachineRepository
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun CadastrarScreen(machineRepository: MachineRepository) {
    var machineName by remember { mutableStateOf("") }
    var qrCode by remember { mutableStateOf<Bitmap?>(null) }
    var enabled by remember { mutableStateOf(true) }
    val async = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cadastre o equipamento:", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            label = { Text("Nome da Maquina") },
            value = machineName,
            onValueChange = { newValue ->
                machineName = newValue
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(
            enabled = enabled,
            onClick = {
                async.launch {
                    enabled = false
                    val response = machineRepository.getQrCode(machineName)
                    qrCode = BitmapFactory.decodeByteArray(response, 0, response.size)
                }
            }
        ) {
            Text("Gerar QR Code")
        }
        qrCode?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "QR Code"
            )
        }
        Button(onClick = {

            async.launch {
                machineRepository.save(
                    Machine(
                        machineName,
                        qrCode.toString(),
                        Date().toString()
                    )
                )
            }
        }) {
            Text("Salvar")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@Preview(showSystemUi = true)
fun CadastrarScreenPreview() {
    CadastrarScreen(machineRepository = MachineRepository())
}