package com.mike.wordgame

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
fun Settings(navController: NavController){
    val forebrush  = Brush.verticalGradient(
        colors = listOf(
            Color(0xff00008B), // Blue
            Color(0xff2196F3), // Light Blue 0xff00008B
            Color(0xff2196F3)

        )
    )

    Column(modifier = Modifier
        .background(brush = forebrush)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row (modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically){
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Arrow back", tint = Color.White,
                modifier = Modifier.clickable { navController.popBackStack() })
            Row (modifier = Modifier.width(20.dp)){

            }
            Text(text = "Settings", style = TextStyle(),
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White)
            Row (modifier = Modifier.width(30.dp)){

            }

        }
        Column(modifier = Modifier
            .background(brush = backbrush, shape = RoundedCornerShape(30.dp, 30.dp))
            .fillMaxWidth()
            .height(750.dp)) {
            Spacer(modifier = Modifier.height(25.dp),)
            Row(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceAround) {
                Text(text = "Game level", style = TextStyle(),
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "arrow", modifier = Modifier.size(40.dp))

            }
            HorizontalDivider(color = Color.DarkGray)

            Row(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Text(text = "How to play", style = TextStyle(),
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "arrow", modifier = Modifier.size(40.dp))

            }
            HorizontalDivider(color = Color.DarkGray)

            Row(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Text(text = "Timer", style = TextStyle(),
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                    ToggleButton()
            }
            HorizontalDivider(color = Color.DarkGray)

            Row(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Text(text = "Sound Effects", style = TextStyle(),
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                    ToggleButton()
            }
            HorizontalDivider(color = Color.DarkGray)

            Row(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Text(text = "Words Category", style = TextStyle(),
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "arrow", modifier = Modifier.size(40.dp))

            }
            HorizontalDivider(color = Color.DarkGray)

            Row(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Text(text = "Log Out", style = TextStyle(),
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)

            }
            HorizontalDivider(color = Color.DarkGray)

            Row(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Text(text = "Delete Account", style = TextStyle(),
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)

            }
            HorizontalDivider(color = Color.DarkGray)

        }

    }

}

@Preview
@Composable
fun SettingsPreview(){
    Settings(navController = rememberNavController())
}

@Composable
fun ToggleButton() {
    var selected by remember { mutableStateOf(false) }

    val alignment = if (selected) {
        Alignment.TopEnd
    } else {
        Alignment.TopStart
    }


        Box(
            modifier = Modifier
                .background(
                    Color(if (selected) 0xff00008B else 0xff6c7175),
                    shape = RoundedCornerShape(30.dp)
                )
                .width(70.dp)
                .height(35.dp),
            contentAlignment = alignment
        ) {
            Box(modifier = Modifier
                .background(Color(if (selected) 0xff2196F3 else 0xff00008B), shape = CircleShape)
                .size(35.dp)
                .clickable { selected = !selected }){

            }
        }

    }

