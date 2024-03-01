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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun NameScreen(navController: NavController){
    val isVisible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn()+ expandIn(),
        exit = fadeOut() + slideOutVertically()+ shrinkOut()
    ) {
    Column(modifier = Modifier
        .background(Color(0xff1F2138))
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly){
        Titlebar()
        versionNumber()
        Logo()

        NameEntryScreen(navController = navController) {

        }
    }
}}

@Composable
fun Logo() {//This is the logo
    Row(
        modifier = Modifier
            .fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color(0xff424769), shape = RoundedCornerShape(20.dp)),

            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "WG",
                style = TextStyle(),
                fontSize = 105.sp,
                fontFamily = FontFamily.Serif,
                color = Color(0xffF6B17A),
                fontWeight = FontWeight.Bold,

            )
        }
    }

}

@Composable
fun NameEntryScreen(navController: NavController, onNameEntered: (String) -> Unit) {
    var entername by remember{ mutableStateOf("Hello, there!")}

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier){
        Text(
            text = entername,
            style = TextStyle(color = GlobalVariables.enterednamecolor.value, fontSize = 30.sp, fontFamily = FontFamily.Serif)
        )}

        OutlinedTextField(
            value = GlobalVariables.username.value,
            onValueChange = { newUsername ->
                if (newUsername.all { it.isLetterOrDigit() }) {
                    GlobalVariables.username.value = newUsername
                } else {
                    entername = "Alphanumeric only"
                    GlobalVariables.enterednamecolor.value = Color.Red
                }
            },

            textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
            label = { Text("Name", style = TextStyle(), color = Color.White, fontFamily = FontFamily.Serif) },
            modifier = Modifier
                .width(220.dp)
                .padding(top = 16.dp, bottom = 8.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xff7077A1),
                focusedContainerColor = Color(0xff7077A1),
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
                    GlobalVariables.enterednamecolor.value = Color.Red


                }
            },
            modifier = Modifier.width(150.dp),
            colors = ButtonDefaults.buttonColors(Color(0xffF6B17A)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Continue", style = TextStyle(), fontFamily = FontFamily.Serif, color = Color.Black, fontWeight = FontWeight.Bold)
        }

    }
    LaunchedEffect(entername){
        delay(1000)
        entername = "Enter your username"
        GlobalVariables.enterednamecolor.value = Color.White

    }
}

@Composable
fun Titlebar() {
    var wlettercolor by remember { mutableStateOf(0xff00ffffff)}
    var olettercolor by remember { mutableStateOf(0xff00ffffff)}
    var rlettercolor by remember { mutableStateOf(0xff00ffffff)}
    var dlettercolor by remember { mutableStateOf(0xff00ffffff)}
    var glettercolor by remember { mutableStateOf(0xff00ffffff)}
    var alettercolor by remember { mutableStateOf(0xff00ffffff)}
    var mlettercolor by remember { mutableStateOf(0xff00ffffff)}
    var elettercolor by remember { mutableStateOf(0xff00ffffff)}

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
        wlettercolor = 0xffF6B17A
        delay(90)
        olettercolor = 0xffffffff
        delay(80)
        rlettercolor = 0xffffffff
        delay(70)
        dlettercolor = 0xffffffff
        delay(60)
        glettercolor = 0xffF6B17A
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
fun versionNumber(){
    Box(modifier = Modifier.padding(5.dp)){
        Text(text = GlobalVariables.Version.value,
            style = TextStyle(),
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Light,
            color = GlobalVariables.versiontextcolor.value
        )
    }
    LaunchedEffect(GlobalVariables.Version.value) {
        delay(4500)
        GlobalVariables.Version.value = "\t\t\t\t\t\t\tThis is a Test Version\n" +
                " Words are not properly sorted!"
        GlobalVariables.versiontextcolor.value = Color.Red
    }
}


@Preview(heightDp = 850)
@Composable
fun NameScreenPreview(){
    NameScreen(navController = rememberNavController())
}
