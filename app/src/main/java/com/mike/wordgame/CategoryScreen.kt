package com.mike.wordgame

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.util.Locale

@Composable
fun CategoryScreen(navController: NavController) {
    val isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn()+ expandIn(),
        exit = fadeOut() + slideOutVertically()+ shrinkOut()
    ) {
        CategorySCreen(navController = navController)

    }
}

@Composable
fun CategorySCreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf("Select a category") }
    var SelectedCategoryname by remember { mutableStateOf("") }

    GlobalVariables.selectedcategory=selectedCategory
    SelectedCategoryname = GlobalVariables.selectedcategory


    Column(
        modifier = Modifier
            .background(Color(0xff1F2138))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Header
            Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                NameProfile(name = GlobalVariables.username.value, navController = navController, sizee = 70.dp, 50.sp,GlobalVariables.selectedAvatar)
                Text(text = "CATEGORY",
                    style = TextStyle(
                        fontSize = 45.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily.Serif)
                    )
            }

        // Select a Category Text
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            TypewriterText(texts = listOf(
                "$SelectedCategoryname"
            ), fontSize = 30.sp)
        }



        // Categories Buttons
        Column(
            modifier = Modifier
                .absolutePadding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Category Buttons Row 1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(0.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryButton("Animals", "animals", selectedCategory) { selectedCategory = it }
                CategoryButton("Fruits", "fruits", selectedCategory) { selectedCategory = it }
            }

            // Category Buttons Row 2
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(0.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryButton("Countries", "countries", selectedCategory) { selectedCategory = it }
                CategoryButton("Cars", "cars", selectedCategory) { selectedCategory = it }
            }

            // Category Buttons Row 3
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(0.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryButton("Phones", "phones", selectedCategory) { selectedCategory = it }
                CategoryButton("Stationery", "stationery", selectedCategory) { selectedCategory = it }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(0.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryButton("CompParts", "computer parts", selectedCategory) { selectedCategory = it }
                CategoryButton("Electronics", "electronics", selectedCategory) { selectedCategory = it }
            }
        }

        // Play Game Button
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate("LevelScreen") },
                modifier = Modifier
                    .width(300.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(10.dp), // Rounded corners
                colors = ButtonDefaults.buttonColors(Color(0xffF6B17A)),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ) {
                Text(
                    text = "Select game level",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun CategoryButton(
    label: String,
    category: String,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    Button(
        onClick = { onCategorySelected(category) },
        modifier = Modifier.width(121.dp),
        colors = ButtonDefaults.buttonColors(
            Color(if (selectedCategory == category) 0xffF6B17A else 0xff7077A1)
        )
    ) {
        Text(text = label,
            style = TextStyle(), fontFamily = FontFamily.Serif
       )

    }
}

@Composable
fun hellostyle(name:String){
val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
val scale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 8f,
    animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
    label = "scale"
)
Box(modifier = Modifier
    .fillMaxWidth()
    .height(50.dp)) {
    Text(
        text = name,

        modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                transformOrigin = TransformOrigin.Center
            }
            .align(Alignment.Center),
        // Text composable does not take TextMotion as a parameter.
        // Provide it via style argument but make sure that we are copying from current theme
        style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated),
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Serif,

    )
}}













@Preview()
@Composable
fun CategoryScreenPreview(){
CategoryScreen(navController = rememberNavController())}