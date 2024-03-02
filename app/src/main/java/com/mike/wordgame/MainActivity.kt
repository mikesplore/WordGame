package com.mike.wordgame

import android.annotation.SuppressLint
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
import com.mike.wordgame.ui.theme.WordGameTheme

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
    var selectedlevel: String = "easy"
    var word: MutableState<String> = mutableStateOf(getRandomWord(selectedcategory,GlobalVariables.selectedlevel))
    var timer: MutableState<Int> = mutableStateOf(60)
    var timerRunning: MutableState<Boolean> = mutableStateOf(true)
    var enterednamecolor: MutableState<Color> = mutableStateOf(Color.White)
    var timerbackgroundcolor: MutableState<Color> = mutableStateOf(Color(0xff7077A1))
    var correctGuesscount: MutableState<Int> = mutableStateOf(0)
    var wrongGuesscount: MutableState<Int> = mutableStateOf(0)
    var skippedguess: MutableState<Int> = mutableStateOf(0)
    var Attempts: MutableState<Int> = mutableStateOf(0)
    var Version: MutableState<String> = mutableStateOf("1.0.6")
    var versiontextcolor: MutableState<Color> = mutableStateOf(Color.White)
    var buttontext: MutableState<String> = mutableStateOf("Check")
    var selectedword: MutableState<String> = mutableStateOf("anonymous")
    var username: MutableState<String> = mutableStateOf("")
    var enteredword: MutableState<String> = mutableStateOf("")












}

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordGameTheme  {
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
    NavHost(navController = navController, startDestination = "NameScreen") {
        composable("GameScreen") { GameScreen(navController) }
        composable("GameSummaryScreen") { GameSummaryScreen(navController) }
        composable("CategoryScreen") { CategoryScreen(navController) }
        composable("NameScreen") { NameScreen(navController) }
        composable("LevelScreen") { LevelScreen(navController) }
        composable("HelloScreen") { HelloScreen(navController ) }

        }
    }

@Preview
@Composable
fun Myscreenpreview(){
    MyScreens()
}




