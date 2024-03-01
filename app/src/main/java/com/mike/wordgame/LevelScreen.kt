package com.mike.wordgame

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController




@Composable

fun LevelScreen(navController: NavController){
    var isVisible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + expandIn(),
        exit = fadeOut() + slideOutVertically() + shrinkOut()
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff1F2138)),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .absolutePadding(0.dp, 30.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "GAME LEVEL", style = TextStyle(),
                    color = Color.White,
                    fontSize = 50.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,

                    )

            }


            Gamelevels()

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                Button(
                    onClick = {
                        GlobalVariables.timer.value = 60
                        GlobalVariables.score.value = 0
                        navController.navigate("GameScreen") },
                    colors = ButtonDefaults.buttonColors(Color(0xffF6B17A)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .absolutePadding(0.dp, 0.dp, 0.dp, 30.dp)
                        .width(200.dp)


                ) {
                    Text(
                        text = "Play", style = TextStyle(),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp,
                        color = Color(0xff1F2138)
                    )

                }
            }
        }
    }
}
@Composable
fun Gamelevels(){
var selectedlevel by remember {mutableStateOf("Select game level to play")}
var selectedlevelname by remember{ mutableStateOf("")}

    GlobalVariables.selectedlevel = selectedlevel
    selectedlevelname = GlobalVariables.selectedlevel
    // Levels Buttons

    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){
        Text(text = selectedlevelname, style = TextStyle(),
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

    }
    Column(
        modifier = Modifier
            .absolutePadding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Level Buttons Row 1
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(0.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LevelButton("Beginner", "Beginner", selectedlevel) { selectedlevel = it }



            LevelButton("Medium", "Medium", selectedlevel) { selectedlevel = it }
        }

        // Level Buttons Row 2
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(0.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            LevelButton("Hard", "Hard", selectedlevel) { selectedlevel = it }

            LevelButton("Master", "Master", selectedlevel) { selectedlevel = it }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(0.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LevelButton("Expert", "Expert", selectedlevel) { selectedlevel = it }
            LevelButton("Impossible", "Impossible", selectedlevel) { selectedlevel = it }
        }


    }
}

@Composable
fun LevelButton(
    label: String,
    level: String,
    selectedLevel: String,
    onlevelSelected: (String) -> Unit
) {
    Button(
        onClick = { onlevelSelected(level) },
        modifier = Modifier.width(121.dp),
        colors = ButtonDefaults.buttonColors(
            Color(if (selectedLevel == level) 0xffF6B17A else 0xff7077A1)
        )
    ) {
        Text(text = label,
            style = TextStyle(), fontFamily = FontFamily.Serif
        )

    }
}

@Preview
@Composable
fun LevelSCreen(){
    LevelScreen(navController = rememberNavController())
}
