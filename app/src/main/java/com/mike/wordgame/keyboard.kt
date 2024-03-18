package com.mike.wordgame

import android.graphics.Paint.Align
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController




@Composable
fun keyboard(navController: NavController) {
    var echoText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(Color(0xff1F2138))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        progressBox()
        Instructions(echoText)
        OnScreenKeyboard(
            onLetterClicked = { letter ->
                echoText += letter
                GlobalVariables.enteredword.value = echoText
            },
            onDeleteClicked = {
                if (echoText.isNotEmpty()) {
                    echoText = echoText.dropLast(1)
                }
            },
            onClearClicked = {
                echoText = ""
            }
        )

    }
}

@Composable
fun OnScreenKeyboard(
    onLetterClicked: (Char) -> Unit,
    onDeleteClicked: () -> Unit,
    onClearClicked: () -> Unit
) {

    Column {
        Grid() { letter ->
            LetterButton(letter = letter, onLetterClicked = onLetterClicked)

        }
        Row(modifier = Modifier
            .background(Color(0xff424769))
            .width(400.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            LetterButton(
                letter = '\u232B', // Unicode for backspace symbol
                onLetterClicked = { onDeleteClicked() }
            )
            LetterButton(
                letter = 'C',
                onLetterClicked = { onClearClicked() }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        val navController = rememberNavController()
        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
            AnswerBoxButton()
            SkipButton()
            EndGameButton(navController = navController)
        }



    }
}






@Composable
fun Grid(
    content: @Composable (Char) -> Unit
) {
    val qwertyLayout = listOf(
        listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
        listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'),
        listOf('Z', 'X', 'C', 'V', 'B', 'N', 'M')
    )

    val rows = mutableListOf<@Composable () -> Unit>()
    Box(modifier = Modifier){
    for (row in qwertyLayout) {
        rows.add {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                row.forEach { letter ->
                    Box(
                        modifier = Modifier
                            .size(39.dp)
                            .padding(6.dp)
                            .background(Color.White, shape = RoundedCornerShape(5.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        content(letter)
                    }
                }
            }
        }
    }
}
    Column() {
        rows.forEach { it() }
    }


}


@Composable
fun LetterButton(
    letter: Char,
    onLetterClicked: (Char) -> Unit
) {
    Text(
        text = letter.toString(),
        modifier = Modifier

            .clickable { onLetterClicked(letter) }
            .padding(4.dp),
        color = Color.Blue,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Light,

    )
}

@Composable
fun EchoBox(echoText: String) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .background(Color(0xff7077A1), shape = RoundedCornerShape(10.dp))
            .height(50.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // I will continue from here


        Text(text = echoText,
            style = TextStyle(),
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp

        )
    }
}



@Composable
fun Instructions(echoText: String) {
    val length = "mike".length
    val first = "mike".first()
    val last = "Mike".last()

    val instructionDescription = buildAnnotatedString {
        append("Enter a word of ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xffF6B17A))) {
            append("$length")
        }
        append(" letters that starts with ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xffF6B17A))) {
            append(first)
        }
        append(" and ends with ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xffF6B17A))) {
            append(last)
        }
    }

    Row (modifier = Modifier
        .height(30.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
        Text(text = "Category: ${GlobalVariables.selectedcategory.value.capitalize()}", style = TextStyle(),
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            color = Color.White)
        Text(text = "Level: ${GlobalVariables.selectedlevel.capitalize()}",style = TextStyle(),
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            color = Color.White)

    }

    Column(modifier = Modifier
        .height(200.dp)
        .width(350.dp)){
        Row(modifier = Modifier
            .height(90.dp)
            .background(
                color = GlobalVariables.outcomeColor.value,
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(text = GlobalVariables.outcome.value,
                style = TextStyle(),
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
           )
        }
        Row(modifier = Modifier
            .height(80.dp)
            .background(color = GlobalVariables.hintbackground.value)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(text = GlobalVariables.hint.value,
                style = TextStyle(),
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Row(modifier = Modifier
            .background(Color(0xff7077A1), shape = RoundedCornerShape(10.dp))
            .height(30.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            TypewriterText(
                texts = listOf(

                    "Happy Gaming!",
                    "Tap the Timer to restart game",
                    "Some words are not properly arranged",
                    "Words category is shown above",
                    "Answer button will deduct 3 seconds",
                    "Skip button will deduct 2 seconds",
                    "Correct guess adds 10 seconds",
                    "Tap outcome to reset H-score and profile"
                ), fontSize = 16.sp
            )

        }

    }

    Column(
        modifier = Modifier
            .height(200.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xff7077A1), shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = instructionDescription,
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )
        }

        EchoBox(echoText = echoText)
    }
}


@Composable
fun progressBox(){
    val navController = rememberNavController()
    Row(modifier = Modifier
        .background(Color.Transparent)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
        ){

        ScoreBox()
        TimerBox()
        HighScoreBox(navController = navController)
    }

}







@Preview
@Composable
fun PreviewGameScreen() {
    keyboard(navController = rememberNavController())
}
