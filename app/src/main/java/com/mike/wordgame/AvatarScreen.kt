package com.mike.wordgame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Define a ViewModel to hold the selected avatar state
class AvatarViewModel {
    var selectedAvatar: MutableState<Int?> = mutableStateOf(null)
}

// Global variable to hold the selected avatar image resource ID
var selectedAvatarResource: Int? = null // Selected avatar resource ID
@Composable
fun AvatarScreen(navController: NavController){

Column(modifier = Modifier
    .fillMaxSize()
    .background(Color(0xff1F2138)),
    verticalArrangement = Arrangement.SpaceEvenly,
    horizontalAlignment = Alignment.CenterHorizontally){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Text(text = "Avatars",
            style = TextStyle(),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 39.sp,
            color = Color.White
       )

    }
    Text(text = "Select an Avatar",
        style = TextStyle(),
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        fontSize = 29.sp,
        color = Color.White)
    Avatar(AvatarViewModel())
Button(onClick = { navController.navigate("HelloScreen")},
    colors = ButtonDefaults.buttonColors(Color(0xffF6B17A)),
    shape = RoundedCornerShape(10.dp)) {
    Text(text = "Continue",
        style = TextStyle(),
        fontWeight = FontWeight.Light,
        fontFamily = FontFamily.Serif,
        fontSize = 15.sp,
        color = Color.Black)
    
}
}
}
@Composable
fun Avatar(viewModel: AvatarViewModel) {
    // Define a list of image resources
    val images = listOf(
        R.drawable.avatar,
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4,
        R.drawable.avatar5,
        R.drawable.avatar7,
        R.drawable.avatar6,
        R.drawable.avatar8
    )

    // Define the number of columns in the grid
    val columns = 3

    // Calculate the number of rows in the grid
    val rows = (images.size + columns - 1) / columns

    // Create a grid with the specified number of rows and columns
    Grid(columns = columns, rows = rows, images = images, viewModel = viewModel) { imageResource ->
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(4.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .clickable {
                    // Update the selected avatar variable in the ViewModel with the resource ID of the clicked image
                    viewModel.selectedAvatar.value = imageResource
                    // Assign the selected avatar's resource ID to the variable
                    selectedAvatarResource = imageResource
                    GlobalVariables.selectedAvatar = imageResource
                }
        )

    }

    // Display the selected avatar, if any
   /* viewModel.selectedAvatar.value?.let { avatarResource ->
        Text("Selected Avatar: $avatarResource")
    }
        */
}

@Composable
fun Grid(
    columns: Int,
    rows: Int,
    images: List<Int>,
    viewModel: AvatarViewModel,
    content: @Composable (imageResource: Int) -> Unit
) {
    Column {
        repeat(rows) { rowIndex ->
            Row {
                repeat(columns) { columnIndex ->
                    val index = rowIndex * columns + columnIndex
                    if (index < images.size) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                        ) {
                            content(images[index])
                        }
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun AvatarScreenPreview() {
    // Create an instance of the AvatarViewModel
    val viewModel = AvatarViewModel()

    // Call the AvatarScreen composable with the ViewModel
    AvatarScreen(navController = rememberNavController())
}

