package br.unisinos.grupo3machine.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

const val CADASTRAR_SCREEN = "CadastrarScreen"

@Composable
fun CadastrarScreen() {
    val viewModel: RegistrarViewModel = viewModel()
    val machine by viewModel.machine.collectAsState()
    var enabled by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cadastre o equipamento:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            label = { Text("Nome da Maquina") },
            value = machine.name,
            onValueChange = { newValue ->
                viewModel.updateName(newValue)
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(
            enabled = enabled,
            onClick = {
                if (machine.name.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Nome do equipamento não pode ser vazio",
                        Toast.LENGTH_LONG
                    ).show()
                    return@ElevatedButton
                }
                enabled = false
                viewModel.getQrCode()
            }
        ) {
            Text("Gerar QR Code")
        }
        Button(onClick = {
            if (machine.qrCode == null) {
                Toast.makeText(context, "Gerar QR Code antes de salvar", Toast.LENGTH_LONG).show()
                return@Button
            }
            if (machine.name.isEmpty()) {
                Toast.makeText(context, "Nome do equipamento não pode ser vazio", Toast.LENGTH_LONG)
                    .show()
                return@Button
            }
            viewModel.save()
            Toast.makeText(context, "Novo equipamento adicionado com sucesso!", Toast.LENGTH_LONG)
                .show()
        }) {
            Text("Salvar")
        }
        Spacer(modifier = Modifier.height(30.dp))
        if (machine.qrCode != null) {
            Image(
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                bitmap = viewModel.transformToImage(machine.qrCode!!),
                contentDescription = "QR Code"
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun CadastrarScreenPreview() {
    CadastrarScreen()
}