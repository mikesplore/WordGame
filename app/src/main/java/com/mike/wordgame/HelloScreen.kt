package com.mike.wordgame


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
val backbrush  = Brush.verticalGradient(
    colors = listOf(
        Color(0x80007BFF), // Transparent Royal Blue (lighter)
        Color(0x80000080), // Transparent Dark Gray (darker)
        Color(0x80000000),

        )
)

@Composable
fun HelloScreen(navController: NavController){

    var username = GlobalVariables.username.value


    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = backbrush),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {


        Text(text = GlobalVariables.username.value.uppercase(), style = TextStyle(),
            color = Color.White,
            fontSize = 60.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            )

        TypewriterText(
            texts = listOf(
                "Hi, ${GlobalVariables.username.value.capitalize()}!",
                "Tap the Image to change avatar"
            ), fontSize = 20.sp
        )
        NameProfile(
            name = username,
            navController = navController,
            sizee = 270.dp,
            fontsize = 170.sp,
            selectedAvatar =GlobalVariables.selectedAvatar)

        ProceedToGameButton(navController = navController)
    }
}

@Composable
fun ProceedToGameButton(navController: NavController){
    Button(onClick = {

        navController.navigate("CategoryScreen") },
        modifier = Modifier
            .width(130.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF00BCD4)),
        shape = RoundedCornerShape(10.dp)) {

        Text(text = "Next  ",style = TextStyle(), fontSize = 20.sp,
            fontFamily = FontFamily.Serif,color=Color.Black,
            fontWeight = FontWeight.Bold)
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "arrow", tint = Color.Black)

    }
} //2130968591

@Composable
fun NameProfile(name: String, navController: NavController, sizee: Dp, fontsize: TextUnit, selectedAvatar: Int?) {


    var clickable by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .background(brush = backbrush, shape = CircleShape)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                // Set clickable to true when clicked
                clickable = true
            }
            .size(sizee),
        contentAlignment = Alignment.Center
    ) {
        selectedAvatar?.let { avatarResource ->
            // Display the selected avatar image if available
            Image(
                painter = painterResource(id = avatarResource),
                contentDescription = "Avatar Image",
                modifier = Modifier
                    .size(sizee)
                    .clip(CircleShape)
            )
        } ?: run {
            // If no avatar is selected, display the first letter of the name
            val first = name.first().toString().uppercase()
            Text(
                text = first,
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = fontsize,
                    color = Color(0xFF00BCD4),
                    fontFamily = FontFamily.Serif
                )
            )
        }
    }

    LaunchedEffect(clickable) {
        if (clickable) {
            navController.navigate("profile")
        }
    }
}











@Preview(heightDp = 850)
@Composable
fun HelloScreenPreview(){
    HelloScreen(navController = rememberNavController())
}