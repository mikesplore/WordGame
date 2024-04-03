package com.mike.wordgame

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.mutableIntStateOf


@Composable
fun NameScreen(navController: NavController){
    val backbrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1E88E5), // Blue
            Color(0xFF42A5F5), // Light Blue
            Color(0xFF90CAF9)  // Lighter Blue
        )
    )
    val isVisible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
    Column(modifier = Modifier
        .background(brush = back)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly){
        Titlebar()
        TypewriterTextSample()
        Logo()

        NameEntryScreen(navController = navController) {

        }
    }
}}

@Composable
fun NameEntryScreen(navController: NavController, onNameEntered: (String) -> Unit) {
    var entername by remember { mutableStateOf("Hello, there!")}
    var clicked by remember { mutableStateOf(true )}
    var textcolor by remember{(mutableStateOf(Color.White))}

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = entername,
            style = TextStyle(color =textcolor, fontSize = 30.sp, fontFamily = FontFamily.Serif),
            modifier = Modifier.clickable { clicked = !clicked }
        )
        AnimatedVisibility(visible = clicked) {
            

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(135.dp)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            // Display the text "Hello there" always


            // Display the text input field only when clicked is true

            OutlinedTextField(
                value = GlobalVariables.username.value,
                onValueChange = { newUsername ->
                    if (newUsername.all { it.isLetterOrDigit() }) {
                        GlobalVariables.username.value = newUsername
                    } else {
                        entername = "Alphanumeric only"
                        textcolor = Color.Red

                    }
                },
                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                label = {
                    Text(
                        "Name",
                        style = TextStyle(),
                        color = Color.White,
                        fontFamily = FontFamily.Serif
                    )
                },
                modifier = Modifier
                    .width(220.dp)
                    .padding(top = 16.dp, bottom = 8.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0x80009Bff),
                    focusedContainerColor = Color(0x80009Bff),
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp)
            )


            Button(
                onClick = {
                    if (GlobalVariables.username.value.trim().isNotEmpty()) {
                        onNameEntered(GlobalVariables.username.value.trim())
                        navController.navigate("HelloScreen")
                    } else {
                        entername = "No username entered"
                        textcolor = Color.Red
                    }
                },
                modifier = Modifier.width(130.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF00BCD4)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Next  ",style = TextStyle(), fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,color=Color.Black,
                    fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "arrow", tint = Color.Black)
            }
        }
    }
    LaunchedEffect(entername) {
        delay(2000)
        textcolor = Color.White
        entername = "Enter your name"
    }
}}



@Composable
fun Titlebar() {
    var wlettercolor by remember { mutableLongStateOf(0xff00ffffff)}
    var olettercolor by remember { mutableLongStateOf(0xff00ffffff) }
    var rlettercolor by remember { mutableLongStateOf(0xff00ffffff) }
    var dlettercolor by remember { mutableLongStateOf(0xff00ffffff) }
    var glettercolor by remember { mutableLongStateOf(0xff00ffffff) }
    var alettercolor by remember { mutableLongStateOf(0xff00ffffff) }
    var mlettercolor by remember { mutableLongStateOf(0xff00ffffff)}
    var elettercolor by remember { mutableLongStateOf(0xff00ffffff) }

    val titledescription = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(wlettercolor), fontSize = 80.sp)) {
            append("W")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(olettercolor), fontSize = 30.sp)) {
            append("O")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(rlettercolor), fontSize = 30.sp)) {
            append("R")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(dlettercolor), fontSize = 30.sp)) {
            append("D")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(glettercolor), fontSize = 80.sp)) {
            append("G")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(alettercolor), fontSize = 30.sp)) {
            append("A")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(mlettercolor), fontSize = 30.sp)) {
            append("M")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(elettercolor), fontSize = 30.sp)) {
            append("E")
        }


    }
    LaunchedEffect(wlettercolor, olettercolor, rlettercolor, dlettercolor, glettercolor, alettercolor, mlettercolor, elettercolor) {
        delay(100)
        wlettercolor = 0xFF90EE90
        delay(90)
        olettercolor = 0xffffffff
        delay(80)
        rlettercolor = 0xffffffff
        delay(70)
        dlettercolor = 0xffffffff
        delay(60)
        glettercolor = 0xFF90EE90
        delay(50)
        alettercolor = 0xffffffff
        delay(55)
        mlettercolor = 0xffffffff
        delay(75)
        elettercolor = 0xffffffff


    }



    Box(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = titledescription,
            style = TextStyle(),
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif,

        )
    }
}

@Composable
fun TypewriterTextSample() {
    Column() {
        TypewriterText(
            texts = listOf(

                "This is a Test Version!",
                "Tap the instruction to enter your name"
            ), fontSize = 16.sp
        )
    }
}

@Composable
fun TypewriterText(
    texts: List<String>,
    fontSize: TextUnit, // Add fontSize parameter
) {
    var textIndex by remember {
        mutableStateOf(0)
    }
    var textToDisplay by remember {
        mutableStateOf("")
    }

    LaunchedEffect(
        key1 = texts,
    ) {
        while (textIndex < texts.size) {
            texts[textIndex].forEachIndexed { charIndex, _ ->
                textToDisplay = texts[textIndex]
                    .substring(
                        startIndex = 0,
                        endIndex = charIndex + 1,
                    )
                delay(100)
            }
            textIndex = (textIndex + 1) % texts.size
            delay(1000)
        }
    }

    Text(
        text = textToDisplay,
        fontSize = fontSize, // Use fontSize parameter
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        color = Color.White
    )
}



@Composable
fun Logo() {
    // Define a list of image resources
    val images = listOf(
        R.drawable.images5

    )

    // Keep track of the current index to select the image from the list
    var imageIndex by remember { mutableIntStateOf(0) }

    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            // Delay for 3 seconds before toggling visibility
            delay(3000)
            isVisible = false
        } else {
            // Delay for 1 second before showing the next image
            delay(1000)
            // Change the image index to select the next image
            imageIndex = (imageIndex + 1) % images.size
            isVisible = true
        }
    }

    Box(
        modifier = Modifier
            .clickable {
                // When the box is clicked, increment the image index
                imageIndex = (imageIndex + 1) % images.size
            }
            .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
            .size(300.dp)
            .padding(16.dp)
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp)), // Clip the box with rounded corners
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = images[imageIndex]),
                    contentDescription = "Images",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }



}



@Preview()
@Composable
fun NameScreenPreview(){
    NameScreen(navController = rememberNavController())
}

