package com.aliendev.alcoolgasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliendev.alcoolgasolina.ui.theme.AlcoolGasolinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create by JetPack Compose
        setContent {
            AlcoolGasolinaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable // Me permite utilizar as função do JetPack
fun App() {
    // Sobrepõe os componentes que estão dentro dele.
//    Box {
//        Text(text= "Alcool or Gasoline")
//        Text(text= "Gasoline")
//    }
    // Coloca um item ao lado do outro
//    Row {
//        Text(text= "Alcool or Gasoline")
//        Text(text= "Gasoline")
//    }

    var valueGasoline by remember {mutableStateOf("")}
    var valueAlcohol by remember {mutableStateOf("")}



//    val isAlcohol = valueAlcohol.toDouble() / valueGasoline.toDouble() > 0.7;
    // Coloca os items cada um em uma linha vertical
    Column(
        Modifier
            .background(color = Color(0xFF28BCFF)) // Define a cor
            .fillMaxSize(), // Preenche 100% da tela
        verticalArrangement = Arrangement.Center, // Deixar os items centralizados
        horizontalAlignment = Alignment.CenterHorizontally // Centraliza
    ) {
        Text(text= "What is the best option ?", style = TextStyle(
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        ))
            Spacer(modifier = Modifier.size(16.dp)) // Espaçamento

        AnimatedVisibility(visible = valueAlcohol.isNotBlank() && valueGasoline.isNotBlank()) {
            // Quando os campos não estiverem vazios
            if (valueAlcohol.isNotBlank() && valueGasoline.isNotBlank()) {
                val isGasoline = valueAlcohol.toDouble() / valueGasoline.toDouble() > 0.7;
                val alcoholOrGasoline = when(isGasoline) {
                    true -> "GASOLINE"
                    else -> {
                        "ALCOHOL"
                    }
                }
                val alcoholOrGasolineColor = when(isGasoline) {
                    true -> Color.Red
                    else -> {
                        Color.Green
                    }
                }
                Text(text= alcoholOrGasoline, style = TextStyle(
                    color = alcoholOrGasolineColor,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                ))
            }
        }

            Spacer(modifier = Modifier.size(16.dp))// Espaçamento


        // Gerenciamento de estado

        // mutableStateOf
        //  -> Permite fazer a modificação
        //  -> Inicia a composição e recomposição do cód.
        //        -> Vai executar e desenhar a tela
        //        -> Mesma coisa, mas aplicando as modificações necessárias.. passando o state como vazio

        // remember
        // -> Tecnica para garantir que ele crie o estado, mas quando tiver que fazer a atualização
        // ele, não faça a inicialização novamente.





        TextField(
            value = valueGasoline, // acessando o valor
            onValueChange = {  // valor digitado
                valueGasoline = it // passando o valor para o state
            },
            label = {
                Text(text = "Gasoline (price per liter)")
            }
        )
            Spacer(modifier = Modifier.size(16.dp))// Espaçamento
        TextField(
            value = valueAlcohol,
            onValueChange = {
                valueAlcohol = it // it -> valor subentendido por ser um unico parametro
            },
            label = {
                Text(text = "Alcohol (price per liter)")
            }
        )
    }
}

// Add Preview
@Preview
@Composable
fun AppPreview() {
    AlcoolGasolinaTheme {
        App()
    }
}