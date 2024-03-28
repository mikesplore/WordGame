package com.mike.wordgame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.channels.ticker
import java.io.FileOutputStream
import java.io.IOException
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh

import androidx.compose.material.icons.rounded.Settings

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
val back = Brush.verticalGradient(
    colors = listOf(
        Color(0xff348feb),
        Color(0xff5ba7f5),
        Color(0xff07417d),
        Color(0xff03172b)
    )
)
@Composable
fun GameScreen(navController: NavController) {
    val timerValue = GlobalVariables.timer.value


    Box {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = back),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Row (modifier = Modifier
            .absolutePadding(0.dp, 20.dp)
            .height(60.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically){

            Box(modifier = Modifier
                .background(Color(0xff5ba7f5), shape = CircleShape)
                .size(45.dp),
                contentAlignment = Alignment.Center){
            Icon(painter = painterResource(id = R.drawable.pause),
                contentDescription = "pause",
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        GlobalVariables.pausegame.value = true
                    }
                    .size(30.dp))}
            Box(modifier = Modifier
                .size(50.dp)
                .background(Color.Transparent),
                contentAlignment = Alignment.Center){
                profile(name = GlobalVariables.username.value, profilesize = 50.dp,30.sp)
                Box(modifier = Modifier
                    .size(50.dp)
                    .background(Color.Transparent),
                    contentAlignment = Alignment.Center){

                }
            }
            Row(modifier = Modifier
                .background(Color(0xff5ba7f5), shape = RoundedCornerShape(30.dp))
                .height(50.dp)
                .width(100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = "${GlobalVariables.timer.value}",
                        style = TextStyle(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

            }
            Box(modifier = Modifier
                .background(Color(0xff5ba7f5), shape = CircleShape)
                .size(45.dp),
                contentAlignment = Alignment.Center){
                Icon(imageVector = Icons.Rounded.Settings, contentDescription = "settings", tint = Color.White,
                    modifier = Modifier.clickable { navController.navigate("settings") })
            }


        }
        Column(modifier = Modifier
            .height(700.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround) {

            Column (modifier = Modifier
                .height(70.dp)
                .width(200.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween){
                Text(text = GlobalVariables.selectedcategory.value.capitalize(),
                    style = TextStyle(),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                    )
                Timer()

                TimeTracker(timerValue = timerValue,100)
            }



            Row (modifier = Modifier

                .height(100.dp)
                .width(270.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text = instructions(),
                    style = TextStyle(),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
               )

            }
            Row(modifier= Modifier
                .height(100.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                RulesAndAOB()

            }
            Row(modifier = Modifier
                .height(65.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Row() {


                    TextField(
                        value = GlobalVariables.enteredword.value, textStyle = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 20.sp,
                            color = Color.Black
                        ),
                        onValueChange = {GlobalVariables.enteredword.value = it},
                        label = { Text(text = "Word")
                        }, colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xff5ba7f5),
                            focusedContainerColor = Color(0xff5ba7f5),
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.Black,




                        ),
                        shape = RoundedCornerShape(20.dp,0.dp,0.dp,20.dp),
                        singleLine = true
                    )
                    Button(
                        onClick = {
                                  if (GlobalVariables.enteredword.value.isEmpty()) {
                                    GlobalVariables.outcome.value = "Please enter a word"
                                  }
                            else{
                                if(compareWord(GlobalVariables.enteredword.value)){
                                    GlobalVariables.score.value+=5
                                    GlobalVariables.outcome.value = GlobalVariables.word.value
                                    GlobalVariables.timer.value+=10
                                    GlobalVariables.Attempts.value+=1
                                    GlobalVariables.correctGuesscount.value+=1
                                    GlobalVariables.outcomecolor.value = Color.Green
                                    GlobalVariables.enteredword.value = ""
                                    GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory.value,GlobalVariables.selectedlevel)
                                    GlobalVariables.outcometextcolor.value = Color.Black

                                }else{
                                    GlobalVariables.outcome.value = GlobalVariables.enteredword.value
                                    GlobalVariables.outcomecolor.value = Color.Red
                                    GlobalVariables.outcometextcolor.value = Color.Black
                                }
                            }

                    },
                        modifier = Modifier
                            .height(55.dp),
                        shape = RoundedCornerShape(0.dp,20.dp,20.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xff03172b))) {
                        Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Arrow forward")

                    }}

            }


            Row(modifier = Modifier
                .background(
                    color = GlobalVariables.outcomecolor.value,
                    shape = RoundedCornerShape(30.dp)
                )
                .fillMaxWidth()
                .height(150.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {

                Text(text = GlobalVariables.outcome.value,style = TextStyle(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center,
                    color = GlobalVariables.outcometextcolor.value)

            }
            Row (modifier = Modifier
                .absolutePadding(0.dp, 0.dp, 0.dp, 10.dp)
                .height(50.dp)
                .fillMaxWidth()
                .background(Color.Transparent),
                horizontalArrangement = Arrangement.Center){
                Row (modifier = Modifier.width(300.dp),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xff5ba7f5), shape = CircleShape),
                        contentAlignment = Alignment.Center){
                        Image(painter = painterResource(
                            id = R.drawable.light),
                            contentDescription = "hint",
                            modifier = Modifier.size(30.dp))
                    }
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xff5ba7f5), shape = CircleShape),
                        contentAlignment = Alignment.Center){



                        Image(painter = painterResource(id = R.drawable.skip), contentDescription = "skip",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    GlobalVariables.word.value = getRandomWord(
                                        GlobalVariables.selectedcategory.value,
                                        GlobalVariables.selectedlevel
                                    )
                                }
                        )
                    }
                }




            }


        }




    }
        GameOver()
        PauseGame()
}
    LaunchedEffect(
        GlobalVariables.outcome.value,
        GlobalVariables.outcometextcolor.value,
        GlobalVariables.outcomecolor.value) {
        delay(1000)
        GlobalVariables.outcomecolor.value = Color.Transparent
        GlobalVariables.outcome.value = "Outcome"
        GlobalVariables.outcometextcolor.value = Color.White
        
    }
}

@Composable
fun Timer(): Int{
    if (GlobalVariables.timerRunning.value) {
        LaunchedEffect(true) {
            val ticker = ticker(1000L)
            for (event in ticker) {
                if (GlobalVariables.timer.value > 0) {
                    GlobalVariables.timer.value--
                } else {
                    GlobalVariables.Gameoverdialog.value = true
                    GlobalVariables.timerRunning.value = false
                    break
                }
            }
        }
    }
    return GlobalVariables.timer.value
}
@Composable
fun TimeTracker(timerValue: Int, maxValue: Int) {
    val progress = if (maxValue != 0) {
        timerValue.toFloat() / maxValue.toFloat()
    } else {
        0f
    }
    val backgroundColor = if (timerValue < 10) Color.Red else Color.Green

    LinearProgressIndicator(
        progress = {
            progress.coerceIn(0f, 1f)
        },
        modifier = Modifier

            .height(20.dp)
            .width(200.dp),
        color = backgroundColor

    )
}





@Composable
fun GameOver() {
    AnimatedVisibility(visible = GlobalVariables.Gameoverdialog.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.6f))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(330.dp)
                    .background(Color(0xff348feb), RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = Modifier

                        .width(300.dp)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Game Over!",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center
                    )

                    Column(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .width(230.dp)
                            .height(150.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Score",
                            style = TextStyle(
                                fontFamily = FontFamily.Monospace,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Text(
                            text = "${GlobalVariables.score.value}",
                            style = TextStyle(
                                fontFamily = FontFamily.Monospace,
                                fontSize = 50.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }

                    Row(
                        modifier = Modifier.width(235.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { GlobalVariables.Gameoverdialog.value = false },
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {
                            Row(
                                modifier = Modifier.width(100.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        "Play again",
                                        style = TextStyle(
                                            fontFamily = FontFamily.Monospace,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = "",
                                        tint = Color.Black
                                    )
                                }
                            }
                        }

                        Button(
                            onClick = { GlobalVariables.Gameoverdialog.value = false },
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {
                            Row(
                                modifier = Modifier.width(90.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        "End",
                                        style = TextStyle(
                                            fontFamily = FontFamily.Monospace,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ExitToApp,
                                        contentDescription = "",
                                        tint = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



fun compareWord(guess: String): Boolean {
    return GlobalVariables.word.value.lowercase() == guess.lowercase()
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


@Composable
fun instructions(): AnnotatedString{
    val length = GlobalVariables.word.value.length
    val first = GlobalVariables.word.value.first()
    val last = GlobalVariables.word.value.last()

    val description = buildAnnotatedString {
        append("Enter a word of ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 25.sp,
            color = Color(0xff042445))
        ) {
            append("$length")
        }
        append(" letters that starts with ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color(0xff042445))
        ) {
            append(first.lowercase())
        }
        append(" and ends with ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold,color = Color(0xff042445),fontSize = 25.sp)
        ) {
            append("$last")
        }
    }
    return description
}

@Composable
fun PauseGame(){
    AnimatedVisibility(visible = GlobalVariables.pausegame.value) {
        

    Box(modifier = Modifier
        .background(brush = back)
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        Column(modifier = Modifier
            
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Game Paused",
                        style = TextStyle(),
                        fontSize = 35.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Normal,
                        color = Color.White)

                    Column(modifier = Modifier.size(250.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        profile(
                            name = GlobalVariables.username.value,
                            profilesize = 150.dp,
                            lettersize =100.sp )


                        Text(
                            text = "Score",
                            style = TextStyle(),
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        )
                        Text(
                            text = "${GlobalVariables.score.value}",
                            style = TextStyle(),
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        )
                    }
            Column(modifier = Modifier
                .height(100.dp)
                .width(200.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "High Score",
                    style = TextStyle(),
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
                Text(
                    text = "${GlobalVariables.high_score.value}",
                    style = TextStyle(),
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }

            Column(modifier = Modifier.height(200.dp),
                verticalArrangement = Arrangement.SpaceAround) {

                Row (modifier = Modifier
                    .background(Color(0xff00b0fc), shape = RoundedCornerShape(20.dp))
                    .height(40.dp)
                    .width(200.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){
                    Text(text = "Continue",
                        style = TextStyle(),
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier.clickable { GlobalVariables.pausegame.value = false }
                    )

                }
            Row (modifier = Modifier
                .background(Color(0xff00b0fc), shape = RoundedCornerShape(20.dp))
                .height(40.dp)
                .width(200.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text = "Restart",
                    style = TextStyle(),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

            }
            Row (modifier = Modifier
                .background(Color(0xff00b0fc), shape = RoundedCornerShape(20.dp))
                .height(40.dp)
                .width(200.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(text = "Exit",
                    style = TextStyle(),
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )

            }}

        }


    }
    }
}

@Composable
fun RulesAndAOB() {
    val texts = listOf(
        "Hey ${GlobalVariables.username.value}!",
        "Hit the bulb icon to display hint",
        "Bottom right button skips the current question",
        "Happy gaming!",
        "I'm still under development"
    )

    // Keep track of the current index to select the text from the list
    var textIndex by remember { mutableStateOf(0) }

    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            delay(3000)
            isVisible = false
        } else {
            delay(1000)
            textIndex = (textIndex + 1) % texts.size
            isVisible = true
        }
    }

    Column {
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(initialOffsetY = { -40 }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -40 }) + fadeOut()
        ) {
            Text(text = texts[textIndex], style = TextStyle(),
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
           )
        }
    }
}





@Preview
@Composable
fun gamepreview(){
   //GameOver()
    GameScreen(rememberNavController())
    //PauseGame()
}

