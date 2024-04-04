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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.util.Locale
import kotlin.system.exitProcess

@Composable
fun GameSummaryScreen(navController: NavController){
    val isVisible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn()+ expandIn(),
        exit = fadeOut() + slideOutVertically()+ shrinkOut()
    ) {
    ResultScreen(navController = navController)

}}

@Composable
fun ResultScreen(navController: NavController) {
    val viewModel = remember { ProfileViewModel() }
    val context = LocalContext.current
    LaunchedEffect(Unit) {



    }


    Column(
        modifier = Modifier
            .background(brush = back)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Game Summary Box
        Row(modifier = Modifier
            .absolutePadding(0.dp,30.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            profile(name = GlobalVariables.username.value, profilesize = 50.dp, lettersize =30.sp , viewModel = viewModel)

        }

        GlobalVariables.Attempts.value  = GlobalVariables.wrongGuesscount.value+GlobalVariables.correctGuesscount.value
        Column(modifier = Modifier
            .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
            .size(250.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Row(modifier = Modifier
                .height(40.dp)
                .width(200.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {


            Text(text = "Attempts :", style = TextStyle(),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp)
            Text(text = "${GlobalVariables.Attempts.value}", style = TextStyle(),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp)}
            Row(modifier = Modifier
                .height(40.dp)
                .width(200.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {


                Text(text = "Attempts :", style = TextStyle(),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp)
                Text(text = "${GlobalVariables.Attempts.value}", style = TextStyle(),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp)}
            Row(modifier = Modifier
                .height(40.dp)
                .width(200.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {


                Text(text = "Attempts :", style = TextStyle(),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp)
                Text(text = "${GlobalVariables.Attempts.value}", style = TextStyle(),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp)}

        }




        // Thanks Box
        Thanksbox()

        // Buttons Row
        Row(
            modifier = Modifier
                .height(80.dp)
                .absolutePadding(10.dp, 0.dp, 10.dp, 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Play Again Button
            Button(
                onClick = {
                    navController.navigate("GameScreen")
                    GlobalVariables.score.value = 0
                    GlobalVariables.timer.value = 60
                    GlobalVariables.timerRunning.value = true
                    GlobalVariables.correctGuesscount.value=0
                    GlobalVariables.wrongGuesscount.value=0
                    GlobalVariables.skippedguess.value=0
                    GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory.value,GlobalVariables.selectedlevel)

                },
                modifier = Modifier.width(150.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color(0xff2196F3))
            ) {
                Text(
                    text = "Play again",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 19.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }

            // Exit Game Button
            Button(
                onClick = {
                    exitApplication()
                },
                modifier = Modifier.width(150.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color(0xff2196F3))
            ) {
                Text(
                    text = "Exit game",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}


@Composable
fun Thanksbox(){
    Box(modifier = Modifier
        .height(50.dp)
        .background(Color(0xff424769), shape = RoundedCornerShape(10.dp))
        .width(300.dp),
        contentAlignment = Alignment.Center){
        Text(text = "Thanks for playing, ${GlobalVariables.username.value.capitalize(Locale.ROOT)}", style = TextStyle(),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 16.sp,
            color = Color.White
       )
    }
}



fun exitApplication() {

            val activity=MainActivity()
            activity.finish()
            exitProcess(0)

     }


@Preview(heightDp = 850)
@Composable
fun GameSummaryScreenPreview(){
    GameSummaryScreen(navController = rememberNavController())

}