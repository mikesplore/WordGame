package com.example.mynavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynavigation.ui.theme.MyNavigationTheme

object GlobalVariables {
    var outcome: MutableState<String> = mutableStateOf("Outcome")
    var score: MutableState<Int> = mutableStateOf(0)
    var text: MutableState<String> = mutableStateOf("")
    var high_score: MutableState<Int> = mutableStateOf(0)
    var outcomeColor: MutableState<Color> = mutableStateOf(Color(0xff424769))
    var hintcolor: MutableState<Color> = mutableStateOf(Color(0xff424769))
    var thanksname: MutableState<String> = mutableStateOf("anonymous")
    var hint: MutableState<String> = mutableStateOf("")
    var selectedcategory: String = "Mike"
    var word: MutableState<String> = mutableStateOf(getRandomWord(selectedcategory))
    var timer: MutableState<Int> = mutableStateOf(30)
    var timerRunning: MutableState<Boolean> = mutableStateOf(true)
    var enterednamecolor: MutableState<Color> = mutableStateOf(Color.White)
    var timerbackgroundcolor: MutableState<Color> = mutableStateOf(Color(0xff7077A1))
    var correctGuesscount: MutableState<Int> = mutableStateOf(0)
    var wrongGuesscount: MutableState<Int> = mutableStateOf(0)
    var skippedguess: MutableState<Int> = mutableStateOf(0)
    var correctpercentage: MutableState<Int> = mutableStateOf(0)
    var Attempts: MutableState<Int> = mutableStateOf(0)




}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNavigationTheme  {
                Scaffold(modifier = Modifier
                    .background(Color(0xffffffff), shape = RoundedCornerShape(20.dp))
                    .fillMaxSize()) {
                    MyScreens()
                }
            }
        }
    }
}


@Composable
fun MyScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen4") { Screen4(navController) }
        composable("screen5") { Screen5(navController) }
        composable("screen3") { Screen3(navController) }
        composable("screen1") { Screen1(navController) }
        composable("profile/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            Screen2(navController, name)
        }
    }
}
@Preview
@Composable
fun myscreenpreview(){
    MyScreens()
}




