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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    val context = LocalContext.current

    // Constants for button colors
    val buttonBackgroundColor = Color(0xffF6B17A)
    val buttonTextColor = Color(0xff2D3250)

    Column(
        modifier = Modifier
            .background(Color(0xff1F2138))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        // Game Summary Box
        Box(
            modifier = Modifier
                .height(100.dp)
                .shadow(
                    30.dp,
                    shape = RoundedCornerShape(20.dp),
                    clip = true,
                    spotColor = Color(0xffF6B17A)
                )
                .width(350.dp)
                .absolutePadding(0.dp, 30.dp)
                .background(Color(0xff424769), shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Game Summary",
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            )
        }

        GlobalVariables.Attempts.value  = GlobalVariables.wrongGuesscount.value+GlobalVariables.correctGuesscount.value
        Box(modifier = Modifier
            .background(Color(0xff7077A1), shape = RoundedCornerShape(10.dp))
            .height(100.dp)
            .width(200.dp), contentAlignment = Alignment.Center){
            Text(text = "Attempts : ${GlobalVariables.Attempts.value}", style = TextStyle(),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp)
        }

        // Score Box
        Column(modifier = Modifier
            .background(Color(0xff1F2138), shape = RoundedCornerShape(20.dp))
            .height(300.dp)
            .width(350.dp)) {
            Box(modifier = Modifier
                .width(350.dp)
                .height(50.dp)
                .background(Color(0xff7077A1), shape = RoundedCornerShape(20.dp)),
                 contentAlignment = Alignment.Center) {
                Text(text = "Correct words:   ${GlobalVariables.correctGuesscount.value}",
                    style = TextStyle(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )


            }
            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier
                .width(350.dp)
                .height(50.dp)
                .background(Color(0xff7077A1), shape = RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center) {
                Text(text = "Incorrect words:   ${GlobalVariables.wrongGuesscount.value}",
                    style = TextStyle(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier
                .width(350.dp)
                .height(50.dp)
                .background(Color(0xff7077A1), shape = RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center) {
                Text(text = "Skipped words:   ${GlobalVariables.skippedguess.value}",
                    style = TextStyle(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier
                .width(350.dp)
                .height(100.dp)
                .background(Color(0xff7077A1), shape = RoundedCornerShape(20.dp)), horizontalArrangement = Arrangement.SpaceBetween) {
                Box(modifier = Modifier
                    .height(100.dp)
                    .width(120.dp)
                    .background(Color(0xff7077A1), shape = RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Score", style = TextStyle(),
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(text = "${GlobalVariables.score.value}",style = TextStyle(),
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                }
                }
                Box(modifier = Modifier
                    .height(100.dp)
                    .width(140.dp)
                    .background(Color(0xff7077A1), shape = RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center

                ){
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "High Score",style = TextStyle(),
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(text = "${GlobalVariables.high_score.value}",style = TextStyle(),
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                            )
                    }
                }

            }

        }

        // Thanks Box
        Thanksbox()

        // Buttons Row
        Row(
            modifier = Modifier
                .height(80.dp)
                .padding(10.dp)
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
                    GlobalVariables.word.value = getRandomWord(GlobalVariables.selectedcategory,GlobalVariables.selectedlevel)

                },
                modifier = Modifier.width(150.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(buttonBackgroundColor)
            ) {
                Text(
                    text = "Play again",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 19.sp,
                        color = buttonTextColor,
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
                colors = ButtonDefaults.buttonColors(buttonBackgroundColor)
            ) {
                Text(
                    text = "Exit game",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp,
                        color = buttonTextColor,
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
        Text(text = "Thanks for playing, ${GlobalVariables.thanksname.value.capitalize(Locale.ROOT)}", style = TextStyle(),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 16.sp,
            color = Color.White
       )
    }
}



fun exitApplication() {

            val activity: MainActivity = MainActivity()
            activity.finish()
            exitProcess(0)

     }


@Preview(heightDp = 850)
@Composable
fun GameSummaryScreenPreview(){
    GameSummaryScreen(navController = rememberNavController())

}