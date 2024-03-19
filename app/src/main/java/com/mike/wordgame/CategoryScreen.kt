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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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

    GlobalVariables.selectedcategory.value=selectedCategory
    SelectedCategoryname = GlobalVariables.selectedcategory.value


    Column(
        modifier = Modifier
            .background(brush = backbrush)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
            Column(modifier= Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "arrow back",
                        modifier = Modifier.clickable { navController.popBackStack() }
                    )
                }

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
                SelectedCategoryname.capitalize()
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
            Button(onClick = {

                navController.navigate("LevelScreen") },
                modifier = Modifier
                    .width(130.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xff2196F3)),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                shape = RoundedCornerShape(10.dp)) {

                Text(text = "Next  ",style = TextStyle(), fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,color=Color.Black,
                    fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "arrow", tint = Color.Black)

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
            Color(if (selectedCategory == category) 0xff00008B else 0xff2196F3)
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
    ) {
        Text(text = label,
            style = TextStyle(), fontFamily = FontFamily.Serif
       )

    }
}















@Preview()
@Composable
fun CategoryScreenPreview(){
CategoryScreen(navController = rememberNavController())}