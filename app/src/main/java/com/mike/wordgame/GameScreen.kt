package com.mike.wordgame


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import java.io.FileOutputStream
import java.io.IOException
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.util.Locale

@Composable
fun GameScreen(navController: NavController) {
    val isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn()+ expandIn(),
        exit = fadeOut() + slideOutVertically()+ shrinkOut()
    ) {
        Gamescreen(
            navController = navController
        )
    }
}


@Composable
fun Gamescreen(navController: NavController) {
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

                    // Set outcome message
                    GlobalVariables.outcome.value = "Time is UP!"
                    GlobalVariables.outcomeColor.value = Color.Red

                    break
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
            HighScoreBox(navController = navController)
        }
        Row() {
            Text(text = GlobalVariables.selectedcategory.value.capitalize(Locale.ROOT),style = TextStyle(),
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
           )
        }


        //outcome box

        Column (modifier = Modifier
            .height(200.dp)
            .background(Color(0xff424769), shape = RoundedCornerShape(20.dp))
            .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally){


        Button(onClick = {
                        navController.navigate("Usernames")

        },
            modifier = Modifier
                .width(430.dp)
                .height(85.dp),
            shape = RectangleShape,

            colors = ButtonDefaults.buttonColors(GlobalVariables.outcomeColor.value)
                
        ) {
            Text(text = GlobalVariables.outcome.value, style = TextStyle(),
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
                )
            
        }
            Row (modifier = Modifier
                .height(85.dp)
                .background(color = GlobalVariables.hintbackground.value)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text =GlobalVariables.hint.value,
                    style = TextStyle(),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp,
                    color = Color.Black)


            }

            Row (modifier = Modifier
                .height(25.dp)
                .background(color = Color(0xff7077A1), shape = RoundedCornerShape(20.dp))
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                TypewriterText(
                    texts = listOf(

                        "Happy Gaming!",
                        "Tap the Timer to restart game",
                        "Some words are not properly arranged",
                        "Words category is shown above",
                        "Answer button will deduct 3 seconds",
                        "Skip button will deduct 2 seconds",
                        "Correct guess adds 10 seconds",
                        "Tap outcome to reset H-score and profile"
                    ), fontSize = 16.sp
                )
                

            }




        }


        Box(
            modifier = Modifier
                .height(200.dp)
                .padding(20.dp)
                .background(Color(0xff424769), shape = RoundedCornerShape(10.dp))
                .fillMaxWidth(),
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




        var inputWord = GlobalVariables.enteredword.value.replace(" ","")



        Button(
            onClick = {
                    if(GlobalVariables.timer.value>0){
                if (inputWord.isNotEmpty()){
                GlobalVariables.outcome.value = compareWord(inputWord)
                GlobalVariables.buttontext.value = GlobalVariables.outcome.value
                if (compareWordd(inputWord)) {
                    GlobalVariables.score.value += 5
                    GlobalVariables.outcomeColor.value = Color(0xff1e8a37)
                    GlobalVariables.hint.value = GlobalVariables.word.value
                    GlobalVariables.hintcolor.value = Color(0xff1e8a37)
                    GlobalVariables.hintbackground.value = Color(0xffF6B17A)
                    GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory.value, GlobalVariables.selectedlevel)
                    GlobalVariables.timer.value+=10
                    GlobalVariables.correctGuesscount.value+=1
                    GlobalVariables.Attempts.value+=1

                } else {
                    GlobalVariables.outcomeColor.value = Color(0xffb80d0d)
                    GlobalVariables.wrongGuesscount.value+=1
                    GlobalVariables.Attempts.value+=1
                    GlobalVariables.hint.value= showCorrectLetters(GlobalVariables.word.value,inputWord)
                }

                GlobalVariables.text.value = ""
            }else{
                GlobalVariables.hint.value = "Please enter a word"
                    GlobalVariables.hintcolor.value = Color(0xff042c4a)
            }
           }else{
                        GlobalVariables.timerRunning.value = false
                        GlobalVariables.outcomeColor.value = Color.Red
                        GlobalVariables.outcome.value  = "Restart the game"
                    }

                      },modifier = Modifier,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xffF6B17A))
        ) {
            Text(
                text = GlobalVariables.buttontext.value,
                style = TextStyle(),
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color(0xff2D3250)
            )
        }

        LaunchedEffect(
            GlobalVariables.outcome.value,
            GlobalVariables.buttontext.value,
            GlobalVariables.outcomeColor.value,
            GlobalVariables.hint.value,
            GlobalVariables.hintbackground.value


            )
                {
            // Wait for the outcome to change
            delay(2000) // Delay for 2000 milliseconds (2 seconds)
            // After 2 seconds, reset the outcome to "Check"
                    GlobalVariables.hintbackground.value = Color.Transparent
            GlobalVariables.outcome.value = "Outcome"
            GlobalVariables.buttontext.value = "Check"
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
            EndGameButton(navController = navController)
        }
    }
}



@Composable
fun ScoreBox() {
val score = GlobalVariables.score.value
    Box(modifier = Modifier
        .size(100.dp)
        .background(Color(0xff424769), shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center){
        Column (modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Score", style = TextStyle(), fontSize = 20.sp, fontFamily = FontFamily.Serif,color= Color.White, fontWeight = FontWeight.Bold)
            Text(text = "$score", style = TextStyle(), fontSize = 20.sp, fontFamily = FontFamily.Serif,color= Color.White, fontWeight = FontWeight.Bold)

        }

    }
}

@Composable
fun TimerBox() {
    Button(onClick = {
                     GlobalVariables.timer.value = 60
                     GlobalVariables.score.value = 0
                     GlobalVariables.timerRunning.value = true
                     GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory.value,GlobalVariables.selectedlevel)

    },
        modifier = Modifier
            .size(100.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(GlobalVariables.timerbackgroundcolor.value)

    ) {
        Text(text = "${GlobalVariables.timer.value}", style = TextStyle(),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp,
            color = Color.White

        )
    }
}

@Composable
fun HighScoreBox(navController: NavController) {

    val context = LocalContext.current
    LaunchedEffect(Unit) {

        GlobalVariables.high_score.value = getIntFromInternalStorage(context)

    }


     // Update the high score if the current score is greater
    if (GlobalVariables.score.value > GlobalVariables.high_score.value) {
        saveIntToInternalStorage(context,GlobalVariables.score.value)
    }
/*
    // Retrieve the high score from internal storage
    val highScore = getHighScoreFromInternalStorage(context)
*/
    // Display the high score in a box

    Box(

        modifier = Modifier
            .size(100.dp)
            .clickable { navController.navigate("profile") }
            .background(Color(0xff424769), shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "H-Score",
                style = TextStyle(),
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "${GlobalVariables.high_score.value}", // Display the retrieved high score
                style = TextStyle(),
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Call this function to save the high score to internal storage


// Function to get high score from internal storage




@Composable
fun AnswerBoxButton(){
    Button(onClick = {
                        if(GlobalVariables.timer.value>=0){
                     GlobalVariables.timer.value-=2
                     GlobalVariables.hint.value = GlobalVariables.word.value
                     GlobalVariables.hintcolor.value = Color(0xffF6B17A)
                     GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory.value, GlobalVariables.selectedlevel)
                     }
                     else{
                            GlobalVariables.timerRunning.value = false
                            GlobalVariables.outcomeColor.value = Color.Red
                            GlobalVariables.outcome.value  = "Restart the game"
                     }},

        modifier = Modifier.width(105.dp),shape= RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(Color(0xffF6B17A))) {
        Text(text = "Answer",style = TextStyle(), fontFamily = FontFamily.Serif, fontSize = 15.sp,color=Color(0xff2D3250))
    }
}

@Composable
fun SkipButton(){
    Button(onClick = {  if (GlobalVariables.timer.value!=0){
                        GlobalVariables.timer.value-=1
                        GlobalVariables.skippedguess.value+=1
                        GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory.value,GlobalVariables.selectedlevel)
                        }else{

                            GlobalVariables.timerRunning.value = false
                            GlobalVariables.outcomeColor.value = Color.Red
                            GlobalVariables.outcome.value  = "Restart the game"


                        }
                     }, modifier = Modifier.width(100.dp),shape= RoundedCornerShape(10.dp),colors = ButtonDefaults.buttonColors(Color(0xffF6B17A))) {
        Text(text = "Skip",style = TextStyle(), fontFamily = FontFamily.Serif, fontSize = 15.sp,color=Color(0xff2D3250))
    }
}

@Composable
fun EndGameButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("GameSummaryScreen")
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
            showCorrectLetters(GlobalVariables.word.value, GlobalVariables.text.value)
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
fun GameScreenPreview(){
    GameScreen(navController = rememberNavController())
}

fun showCorrectLetters(actualWord: String, guessedWord: String): String {
    var result = ""
    for ((actualLetter, guessedLetter) in actualWord.zip(guessedWord)) {
        result += if (actualLetter.lowercase() == guessedLetter.lowercase()) {
            "$actualLetter "
        } else {
            "_ "
        }
    }
    return result.trim().lowercase()
}

// Function to save data to internal storage
fun saveIntToInternalStorage(context: Context, value: Int) {
    try {
        val fos: FileOutputStream = context.openFileOutput("HighScore.txt", Context.MODE_PRIVATE)
        val dataOutputStream = DataOutputStream(fos)
        dataOutputStream.writeInt(value)
        dataOutputStream.close()
        fos.close()
    } catch (e: IOException) {
        e.printStackTrace()
        // Handle the error, e.g., show a toast message
        Toast.makeText(context, "Error saving score", Toast.LENGTH_SHORT).show()

    }
}
fun getIntFromInternalStorage(context: Context): Int {
    var value = 0 // Default value if the file or data is not found
    try {
        val fis: FileInputStream = context.openFileInput("HighScore.txt")
        val dataInputStream = DataInputStream(fis)
        value = dataInputStream.readInt()
        dataInputStream.close()
        fis.close()
    } catch (e: IOException) {
        e.printStackTrace()
        // Handle the error, e.g., show a toast message
        Toast.makeText(context, "High Score initialized to zero", Toast.LENGTH_SHORT).show()

    }
    return value
}



