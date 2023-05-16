package com.example.viewmodelsimple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodelsimple.ui.theme.ViewModelSimpleTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelSimpleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }


    class DadoViewModel : ViewModel() {

        private var _numero by mutableStateOf(0)
        val numero get() = _numero
        fun changeNumber() {
            _numero = Random.nextInt(from = 1, until = 7)


        }
    }

    @Composable
    fun MainScreen() {

        val viewModel: DadoViewModel = viewModel()


        BotonYText(viewModel.numero, { viewModel.changeNumber() })
    }

    @Composable
    fun BotonYText(numero: Int, changeNumber: () -> Unit, modifier: Modifier = Modifier) {


        Column(
            Modifier
                .fillMaxSize()
                .padding(35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {


            // con la variable en este lugar(interna)  no va afuncionar ya que compose solo va a recomponer
            //ante modificaciones de estado== es necesario declarar la variable con remember {mutableStateOf }
            // al poner la variable con mutable , la extresion Random.nextInt(from=1,until=7)}) va a dra error ya que espera un
            // una variable mutable .Para corregir, hay que usar la delegacion poniendo el by remember e importando los getValue y setValue


            Button(onClick = changeNumber ) {
                Text(text = "Tirar dado")

            }
            Text(text = numero.toString(), fontSize = 80.sp)

        }


    }


}


