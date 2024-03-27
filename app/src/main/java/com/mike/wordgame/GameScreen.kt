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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream

@Composable
fun GameScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff333A73)),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Row (modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(Color(0xff387ADF)),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically){

            profile(name = GlobalVariables.username.value, profilesize = 50.dp,30.sp)
            Row(modifier = Modifier.width(100.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Points: ${GlobalVariables.score.value}",
                        style = TextStyle(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

            }


            Row (modifier = Modifier.width(90.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "restart", tint = Color.White,
                    modifier = Modifier.clickable {
                        GlobalVariables.pausegame.value = true
                    })

                
                Icon(imageVector = Icons.Outlined.Refresh, contentDescription = "exit", tint = Color.White,
                    modifier = Modifier
                        .clickable {
                            GlobalVariables.timer.value = 60
                            GlobalVariables.word.value= getRandomWord(GlobalVariables.selectedcategory.value,GlobalVariables.selectedlevel)
                            GlobalVariables.score.value = 0
                            GlobalVariables.correctGuesscount.value = 0
                            GlobalVariables.skippedguess.value = 0
                            GlobalVariables.wrongGuesscount.value = 0
                        })
            }

        }
        Column(modifier = Modifier
            .height(700.dp)
            .fillMaxWidth()) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .height(300.dp)) {

                Column(modifier = Modifier
                    .width(220.dp)
                    .height(400.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.padding(10.dp)){
                        Text(text = instructions(),
                            style = TextStyle(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Default,
                            textAlign = TextAlign.Center,
                            color = Color.White)}


                }
                Column(modifier = Modifier
                    .background(Color(0xffFBA834), shape = RoundedCornerShape(30.dp))
                    .height(400.dp)
                    .width(160.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround) {
                    Text(text = GlobalVariables.selectedlevel.capitalize(),
                        style = TextStyle(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Center,
                        color = Color.White)

                    Text(text = Timer().toString(),
                        style = TextStyle(),
                        fontFamily = FontFamily.Default,
                        fontSize = 90.sp)
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "High Score",
                        style = TextStyle(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Center,
                        color = Color.White
                        )
                        Text(text = "${GlobalVariables.high_score.value}",
                            style = TextStyle(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Default,
                            textAlign = TextAlign.Center,
                            color = Color.White)
                    }

                }

            }
            Spacer(modifier = Modifier.height(50.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(text = GlobalVariables.selectedcategory.value.capitalize(),style = TextStyle(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center)

            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(modifier = Modifier
                .height(65.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Row() {


                    TextField(
                        value = GlobalVariables.enteredword.value,
                        onValueChange = {GlobalVariables.enteredword.value = it},
                        label = { Text(text = "Word")
                        }, colors = TextFieldDefaults.colors(


                        ),
                        shape = RoundedCornerShape(20.dp,0.dp,0.dp,20.dp)
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


                                    GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory.value,GlobalVariables.selectedlevel)

                                }else{
                                    GlobalVariables.outcome.value = GlobalVariables.enteredword.value
                                }
                            }

                    },
                        modifier = Modifier
                            .height(56.dp),
                        shape = RoundedCornerShape(0.dp,20.dp,20.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xff4CAF50))) {
                        Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Arrow forward")

                    }}

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {

                Text(text = GlobalVariables.outcome.value,style = TextStyle(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center,
                    color = Color.Black)

            }

        }


        Row (modifier = Modifier
            .absolutePadding(0.dp, 0.dp, 0.dp, 10.dp)
            .height(50.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
            horizontalArrangement = Arrangement.Center){
                    Row (modifier = Modifier.width(300.dp),
                        horizontalArrangement = Arrangement.SpaceBetween){
                    Image(painter = painterResource(id = R.drawable.light), contentDescription = "hint",)

                    Image(painter = painterResource(id = R.drawable.skip), contentDescription = "skip",
                        modifier = Modifier.clickable { GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory.value,GlobalVariables.selectedlevel) })}
            GameOver()
            PauseGame()


        }

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
fun GameOver() {
    AnimatedVisibility(visible = GlobalVariables.Gameoverdialog.value) {
    Column {


            AlertDialog(
                onDismissRequest = { GlobalVariables.Gameoverdialog.value = false },
                title = {
                    Row (modifier = Modifier.width(230.dp),
                        horizontalArrangement = Arrangement.Center){


                    Text(text = "Game Over!",
                        style =  TextStyle(),
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 30.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                   )
                    }
                },

                text = {
                       Column(modifier = Modifier
                           .width(230.dp)
                           .height(150.dp),
                           verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.CenterHorizontally
                       ) {
                           Text(text = "Score",
                               style = TextStyle(),
                               fontWeight = FontWeight.Bold,
                               fontSize = 20.sp,
                               fontFamily = FontFamily.Monospace,
                               color = Color.Black,

                          )
                           Text(text = "${GlobalVariables.score.value}",
                               style = TextStyle(),
                               fontWeight = FontWeight.Bold,
                               fontSize = 50.sp,
                               fontFamily = FontFamily.Monospace,
                               color = Color.Black,)
                       }
                },
                confirmButton = {
                    Row (modifier = Modifier.width(235.dp),
                        horizontalArrangement = Arrangement.SpaceBetween){


                        Button(onClick = { GlobalVariables.Gameoverdialog.value = false },
                            colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                            Row(modifier= Modifier.width(90.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically) {

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                    Text(
                                        "Restart",
                                        style = TextStyle(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily.Monospace,
                                        color = Color.Black
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = "",
                                        tint = Color.Black
                                    )
                                }
                            }

                        }
                    Button(onClick = {GlobalVariables.Gameoverdialog.value = false },
                        colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                        Row(modifier= Modifier.width(90.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically) {

                            Column (horizontalAlignment = Alignment.CenterHorizontally){

                            Text(
                                "End",
                                style = TextStyle(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Monospace,
                                color = Color.Black
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
                },
                shape = RoundedCornerShape(20.dp),
                containerColor = Color(0xffF5F5DC),
                modifier = Modifier
                    .width(370.dp)
                    .height(350.dp)
                    .background(Color.Transparent)
                    .padding(16.dp)
            )

    }
    }
}

@Composable
fun PauseGame() {
    AnimatedVisibility(visible = GlobalVariables.pausegame.value) {
        Column {


            AlertDialog(
                onDismissRequest = { GlobalVariables.pausegame.value = false },
                title = {
                    Row (modifier = Modifier.width(230.dp),
                        horizontalArrangement = Arrangement.Center){


                        Text(text = "Game Paused!",
                            style =  TextStyle(),
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 30.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                },

                text = {
                    Column(modifier = Modifier
                        .width(230.dp)
                        .height(170.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row (modifier = Modifier
                            .background(Color(0xff387ADF), shape = RoundedCornerShape(10.dp))
                            .height(40.dp)
                            .width(230.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center){
                            Text(text = "Continue",
                                style =  TextStyle(),
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 30.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.clickable { GlobalVariables.pausegame.value = false }
                                )

                        }
                        Row (modifier = Modifier
                            .background(Color(0xff387ADF), shape = RoundedCornerShape(10.dp))
                            .height(40.dp)
                            .width(230.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center){
                            Text(text = "Restart",
                                style =  TextStyle(),
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 30.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.clickable { GlobalVariables.pausegame.value = false }
                            )

                        }

                        Row (modifier = Modifier
                            .background(Color(0xff387ADF), shape = RoundedCornerShape(10.dp))
                            .height(40.dp)
                            .width(230.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center){
                            Text(text = "Main Menu",
                                style =  TextStyle(),
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 30.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.clickable { GlobalVariables.pausegame.value = false }
                            )

                        }


                    }
                },
                confirmButton = {

                },
                shape = RoundedCornerShape(20.dp),
                containerColor = Color.Transparent,
                modifier = Modifier
                    .width(370.dp)
                    .height(350.dp)
                    .background(Color.Transparent)
                    .padding(16.dp)
            )

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
            color = Color(0xff00b0fc))
        ) {
            append("$length")
        }
        append(" letters that starts with ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold,fontSize = 25.sp,color = Color(0xff00b0fc))
        ) {
            append(first.lowercase())
        }
        append(" and ends with ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold,color = Color(0xff00b0fc),fontSize = 25.sp)
        ) {
            append("$last")
        }
    }
    return description
}
@Preview
@Composable
fun gamepreview(){
   // GameOver()
    GameScreen(rememberNavController())
    //PauseGame()
}

