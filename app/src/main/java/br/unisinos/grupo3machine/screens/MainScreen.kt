package br.unisinos.grupo3machine.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

const val MAIN_SCREEN = "MainScreen"

@Composable
fun MainScreen(navigation: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navigation.navigate(CADASTRAR_SCREEN)
        }) {
            Text(text = "Cadastrar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navigation.navigate(CONSULTAR_SCREEN)
        }) {
            Text(text = "Consultar")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navigation = NavHostController(LocalContext.current))
}