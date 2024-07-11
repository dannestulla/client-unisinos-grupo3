package br.unisinos.grupo3machine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.unisinos.grupo3machine.screens.CadastrarScreen
import br.unisinos.grupo3machine.ui.theme.MachineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MachineTheme {
                val navigation = rememberNavController()
                Scaffold() { _ ->
                    val machineRepository = MachineRepository()
                    NavHost(navController = navigation, startDestination = "CadastrarScreen") {
                        composable("CadastrarScreen") {
                            CadastrarScreen(machineRepository)
                        }
                    }
                }
            }
        }
    }
}

