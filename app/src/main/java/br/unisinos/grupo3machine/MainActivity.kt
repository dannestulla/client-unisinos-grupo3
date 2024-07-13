package br.unisinos.grupo3machine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.unisinos.grupo3machine.screens.CADASTRAR_SCREEN
import br.unisinos.grupo3machine.screens.CONSULTAR_SCREEN
import br.unisinos.grupo3machine.screens.CadastrarScreen
import br.unisinos.grupo3machine.screens.ConsultaScreen
import br.unisinos.grupo3machine.screens.MAIN_SCREEN
import br.unisinos.grupo3machine.screens.MainScreen
import br.unisinos.grupo3machine.ui.theme.MachineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MachineTheme {
                Scaffold { _ ->
                    val navigation = rememberNavController()

                    // Navigation between screens
                    NavHost(navController = navigation, startDestination = MAIN_SCREEN) {
                        composable(MAIN_SCREEN) {
                            MainScreen(navigation)
                        }
                        composable(CONSULTAR_SCREEN) {
                            ConsultaScreen()
                        }
                        composable(CADASTRAR_SCREEN) {
                            CadastrarScreen()
                        }
                    }
                }
            }
        }
    }
}
