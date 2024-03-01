package com.example.mynavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.util.Locale

@Composable
fun HelloScreen(navController: NavController){
    var isVisible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn()+ expandIn(),
        exit = fadeOut() + slideOutVertically()+ shrinkOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff1F2138)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Greetings(displayName = GlobalVariables.username.value)
            NameProfile(name = GlobalVariables.username.value)
            Ruleclick(navController = navController)
            ProceedToGameButton(navController = navController)
        }
    }
}
@Composable
fun Greetings(displayName: String) {
    Box(
        modifier = Modifier
            .padding(top = 30.dp)
            .height(150.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello ${displayName.capitalize(Locale.ROOT)}!",
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 50.sp,
                color = Color.White,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            ),
            maxLines = 2, // Ensure the text stays on a single line
            overflow = TextOverflow.Ellipsis, // Add ellipsis if the text overflows
            softWrap = true // Disable text wrapping
        )
        GlobalVariables.thanksname.value = displayName
    }
}


@Composable
fun Ruleclick(navController: NavController) {

    Button(
        onClick = {
            navController.navigate("CategoryScreen")
        },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50.dp)
    ) {
        Text(
            text = "",
            style = TextStyle(),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
            fontFamily = FontFamily.Serif,
            color = Color.White
        )
    }
}

@Composable
fun ProceedToGameButton(navController: NavController){
    Button(onClick = {

        navController.navigate("CategoryScreen") },
        modifier = Modifier
            .width(250.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(Color(0xffF6B17A)),
        shape = RoundedCornerShape(10.dp)) {
        Text(text = "Go to Category",style = TextStyle(), fontSize = 20.sp, fontFamily = FontFamily.Serif,color=Color.Black, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun NameProfile(name: String) {
    val first = name.first()
    Box(
        modifier = Modifier
            .background(Color(0xff7077A1), shape = CircleShape)
            .size(270.dp),
        contentAlignment = Alignment.Center
    ) {
        val text = first.toString()
        Text(
            text = text.uppercase(),
            style = TextStyle(),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 150.sp,
            color = Color.White,
            fontFamily = FontFamily.Serif,

        )
    }
}







@Preview(heightDp = 850)
@Composable
fun HelloScreenPreview(){
    HelloScreen(navController = rememberNavController())
}