package com.mike.wordgame

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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
    var selectedcategory: MutableState<String> = mutableStateOf("")
    var selectedlevel: String = "hard"
    var word: MutableState<String> = mutableStateOf(getRandomWord(selectedcategory.value, selectedlevel))
    var timer: MutableState<Int> = mutableStateOf(20)
    var timerRunning: MutableState<Boolean> = mutableStateOf(true)
    var correctGuesscount: MutableState<Int> = mutableStateOf(0)
    var wrongGuesscount: MutableState<Int> = mutableStateOf(0)
    var skippedguess: MutableState<Int> = mutableStateOf(0)
    var Attempts: MutableState<Int> = mutableStateOf(0)
    var username: MutableState<String> = mutableStateOf("")
    var enteredword: MutableState<String> = mutableStateOf("")
    var hintbackground: MutableState<Color> = mutableStateOf(Color(0x00FFFFFF))
    var Gameoverdialog: MutableState<Boolean> = mutableStateOf(false)


}
class BackgroundMusic(context: Context) {
    private val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.game_music_loop)

    fun start() {
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    fun stop() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}

class MainActivity : ComponentActivity() {
    private lateinit var backgroundMusic: BackgroundMusic

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backgroundMusic = BackgroundMusic(this)
        backgroundMusic.start()

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
    override fun onDestroy() {
        super.onDestroy()

        // Stop and release background music
        backgroundMusic.stop()
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
        composable("Usernames") { SavedUsernames(navController) }
        composable("profile") { Profile(navController) }



    }
    }

@Preview
@Composable
fun Myscreenpreview(){
    MyScreens()
}




