package com.example.mynavigation

import android.content.Context
import android.provider.Settings.Global
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import getHighScore
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import storeHighScore




import java.util.Locale

@Composable
fun Screen4(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        GameScreen(
            navController = navController,
            length = GlobalVariables.word.value.length,
            first = GlobalVariables.word.value.first(),
            last = GlobalVariables.word.value.last()
        )
    }
}


@Composable
fun GameScreen(navController: NavController, length: Int, first: Char, last: Char) {
    val context = LocalContext.current
    val length = GlobalVariables.word.value.length
    val first = GlobalVariables.word.value.first()
    val last = GlobalVariables.word.value.last()

    val description = buildAnnotatedString {
        append("Enter a word of ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,
            color = Color(0xffF6B17A))
                ) {
            append("$length")
        }
        append(" letters that starts with ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color(0xffF6B17A))
                ) {
            append(first.lowercase())
             }
        append(" and ends with ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color(0xffF6B17A))
            ) {
            append("$last")
        }
    }

    if (GlobalVariables.timerRunning.value) {
        LaunchedEffect(true) {
            val ticker = ticker(1000L)
            for (event in ticker) {
                if (GlobalVariables.timer.value > 0) {
                    if (GlobalVariables.timer.value < 12) {
                        GlobalVariables.timerbackgroundcolor.value = Color.Red
                    } else {
                        GlobalVariables.timerbackgroundcolor.value = Color(0xff7077A1)
                    }
                    GlobalVariables.timer.value--
                } else {
                    // Stop the timer
                    GlobalVariables.timerRunning.value = false

                    // Check if the current score is higher than the stored high score
                    val currentScore = GlobalVariables.score.value
                    val highScore = getHighScore(context = context)
                    if (currentScore > highScore) {
                        // Update the high score
                        storeHighScore(context = context, highscore = GlobalVariables.high_score.value)
                        val retrievedHighScore = getHighScore(context = context)
                    }

                    // Navigate to "Screen5"
                    navController.navigate("Screen5")

                    // Set outcome message
                    GlobalVariables.outcome.value = "Time is Up\nRestart game"

                    // Set outcome color
                    GlobalVariables.outcomeColor.value = Color(0xffb80d0d)

                    break // Exit the loop when timer reaches 0
                }
            }
        }
    }










    Column(
        modifier = Modifier
            .background(Color(0xff1F2138))
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier
                .absolutePadding(0.dp, 5.dp)
                .background(Color(0xff1F2138))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            ScoreBox()
            TimerBox()
            HighScoreBox()
        }
        Row() {
            Text(text = GlobalVariables.selectedcategory.capitalize(Locale.ROOT),style = TextStyle(),
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
           )
        }

        Row(
            modifier = Modifier
                .height(96.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                var hintcolor = GlobalVariables.hintcolor.value
                Row (modifier = Modifier
                    .width(250.dp)
                    .height(100.dp)
                    .background(color = hintcolor, shape = RoundedCornerShape(10.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically){

                    Text(text =GlobalVariables.hint.value, style = TextStyle(),
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp,
                        color = Color.White,

                   )
                }
            }
        }


        var guessresultcolor = GlobalVariables.outcomeColor.value

        Box(
            modifier = Modifier
                .background(
                    color = guessresultcolor,
                    shape = RoundedCornerShape(20.dp)
                )
                .width(350.dp)
                .height(100.dp),
            contentAlignment = Alignment.Center,
        ) {
                Column(modifier = Modifier
                    .height(100.dp)
                    .width(350.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                    Text(
                        text = GlobalVariables.outcome.value,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        fontSize = 30.sp,
                        color = Color.White,
                        fontFamily = FontFamily.Serif,
                        
                    )
                }

        }


        Box(
            modifier = Modifier
                .height(150.dp)
                .padding(20.dp)
                .background(Color(0xff424769), shape = RoundedCornerShape(10.dp))
                .width(350.dp),
            contentAlignment = Alignment.Center
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){


            Text(
                text = description,
                style = TextStyle(),
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            )
        }}

        val enteredWord = remember { mutableStateOf("") }

        WordField(
            onTextChanged = { enteredWord.value = it },
        )

        var inputWord = enteredWord.value.replace(" ","")


        var buttonText by remember { mutableStateOf("Check") }

        Button(
            onClick = {
                if (inputWord.isNotEmpty()){
                GlobalVariables.outcome.value = compareWord(inputWord)
                buttonText = GlobalVariables.outcome.value
                if (compareWordd(inputWord)) {
                    GlobalVariables.score.value += 5
                    GlobalVariables.outcomeColor.value = Color(0xff1e8a37)
                    GlobalVariables.hint.value = GlobalVariables.word.value
                    GlobalVariables.hintcolor.value = Color(0xff1e8a37)
                    GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory)
                    GlobalVariables.timer.value+=10
                    GlobalVariables.correctGuesscount.value+=1
                    GlobalVariables.Attempts.value+=1
                } else {
                    GlobalVariables.outcomeColor.value = Color(0xffb80d0d)
                    GlobalVariables.wrongGuesscount.value+=1
                    GlobalVariables.Attempts.value+=1
                }

                GlobalVariables.text.value = ""
            }else{
                GlobalVariables.hint.value = "Please enter a word"
                    GlobalVariables.hintcolor.value = Color(0xff042c4a)
            }
                      },modifier = Modifier,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xffF6B17A))
        ) {
            Text(
                text = buttonText,
                style = TextStyle(),
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color(0xff2D3250)
            )
        }

        LaunchedEffect(
            GlobalVariables.outcome.value,
            buttonText,
            GlobalVariables.outcomeColor.value,
            GlobalVariables.hint.value,


            )
                {
            // Wait for the outcome to change
            delay(2000) // Delay for 2000 milliseconds (2 seconds)
            // After 2 seconds, reset the outcome to "Check"
            GlobalVariables.outcome.value = "Outcome"
            buttonText = "Check"
            GlobalVariables.outcomeColor.value = Color(0xff424769)
            GlobalVariables.hint.value = ""
            GlobalVariables.hintcolor.value =Color(0xff424769)
            inputWord = ""
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AnswerBoxButton()
            SkipButton()
            val highScore = GlobalVariables.high_score.value
            EndGameButton(navController = navController)
        }
    }
}



@Composable
fun ScoreBox() {
var Score = GlobalVariables.score.value
    Box(modifier = Modifier
        .size(100.dp)
        .background(Color(0xff424769), shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center){
        Column (modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Score", style = TextStyle(), fontSize = 20.sp, fontFamily = FontFamily.Serif,color= Color.White, fontWeight = FontWeight.Bold)
            Text(text = "$Score", style = TextStyle(), fontSize = 20.sp, fontFamily = FontFamily.Serif,color= Color.White, fontWeight = FontWeight.Bold)

        }

    }
}

@Composable
fun TimerBox() {
    Box(modifier = Modifier
        .size(100.dp)
        .background(color = GlobalVariables.timerbackgroundcolor.value, shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center){
        Column (modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "${GlobalVariables.timer.value}", style = TextStyle(), fontSize = 50.sp, fontFamily = FontFamily.Serif,color= Color(0xffF6B17A), fontWeight = FontWeight.Bold)

        }

    }
}

@Composable
fun HighScoreBox() {
    if (GlobalVariables.score.value>GlobalVariables.high_score.value){
        GlobalVariables.high_score.value = GlobalVariables.score.value
    }
    Box(modifier = Modifier
        .size(100.dp)
        .background(Color(0xff424769), shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center){
        Column (modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "H-Score", style = TextStyle(), fontSize = 20.sp, fontFamily = FontFamily.Serif,color= Color.White, fontWeight = FontWeight.Bold)
            Text(text = "${GlobalVariables.high_score.value}", style = TextStyle(), fontSize = 20.sp, fontFamily = FontFamily.Serif,color= Color.White, fontWeight = FontWeight.Bold)

        }

    }
}



@Composable
fun AnswerBoxButton(){
    Button(onClick = {
                     GlobalVariables.timer.value-=2
                     GlobalVariables.hint.value = GlobalVariables.word.value
                     GlobalVariables.hintcolor.value = Color(0xffF6B17A)
                     GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory)}, modifier = Modifier.width(105.dp),shape= RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(Color(0xffF6B17A))) {
        Text(text = "Answer",style = TextStyle(), fontFamily = FontFamily.Serif, fontSize = 15.sp,color=Color(0xff2D3250))
    }
}

@Composable
fun SkipButton(){
    Button(onClick = {  GlobalVariables.timer.value-=1
                        GlobalVariables.skippedguess.value+=1
                        GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory)
                     }, modifier = Modifier.width(100.dp),shape= RoundedCornerShape(10.dp),colors = ButtonDefaults.buttonColors(Color(0xffF6B17A))) {
        Text(text = "Skip",style = TextStyle(), fontFamily = FontFamily.Serif, fontSize = 15.sp,color=Color(0xff2D3250))
    }
}

@Composable
fun EndGameButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("screen5")
            // You can pass the high score to be stored
        },
        modifier = Modifier.width(105.dp),
        shape= RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color(0xffF6B17A))
    ) {
        Text(
            text = "End",
            style = TextStyle(),
            fontFamily = FontFamily.Serif,
            fontSize = 15.sp,
            color = Color(0xff2D3250)
        )
    }
}



@Composable
fun WordField(
    onTextChanged: (String) -> Unit,
) {

    TextField(
        value = GlobalVariables.text.value,
        onValueChange = {
            GlobalVariables.text.value = it.trim()
            onTextChanged(it.trim())
        },
        label = { Text("Enter your guess", style = TextStyle(), fontSize = 16.sp, fontFamily = FontFamily.Serif, color = Color.White) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xff7077A1),
            focusedContainerColor = Color(0xff7077A1),
            cursorColor = Color(0xffF6B17A),
            focusedTextColor = Color(0xffF6B17A),
            unfocusedTextColor = Color.Black
        ),
        shape = RoundedCornerShape(10.dp)
    )
}






fun getWordLength(): Int {
    return GlobalVariables.word.value.length
}

fun getFirstLetter(): Char {
    return GlobalVariables.word.value.first()
}

fun getLastLetter(): Char {
    return GlobalVariables.word.value.last()
}

fun compareWord(guess:String): String{

    val result: String
    if (GlobalVariables.word.value.lowercase()==guess.lowercase()){
            result = "The guess was Right!"
            }
    else{ result = "The guess was wrong"

    }
    return result

}
fun compareWordd(guess: String): Boolean {
    return GlobalVariables.word.value.lowercase() == guess.lowercase()
}





@Preview(heightDp = 850)
@Composable
fun Screen4Preview(){
    Screen4(navController = rememberNavController())
}

