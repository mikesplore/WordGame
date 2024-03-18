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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Define a ViewModel to hold the selected avatar state
class AvatarViewModel {
    var selectedAvatar: MutableState<Int?> = mutableStateOf(null)
}

// Global variable to hold the selected avatar image resource ID
var selectedAvatarResource: Int? = null // Selected avatar resource ID
@Composable
fun AvatarScreen(navController: NavController) {
    val context = LocalContext.current
    val upperbrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF007BFF), // Royal Blue (fully opaque)
            Color(0x80007BFF), // Transparent Royal Blue (lighter)
            Color(0x80000060), // Transparent Dark Gray (darker)
            Color(0x80000000)  // Fully Transparent
        )
    )
    val lowerbrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF007BFF), // Royal Blue (fully opaque)
            Color(0x80007BFF), // Transparent Royal Blue (lighter)
            Color(0x80000060), // Transparent Dark Gray (darker)
            Color(0x80000000)  // Fully Transparent
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = upperbrush),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier

                .height(150.dp)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Avatars",
                style = TextStyle(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontSize = 39.sp,
                color = Color.White
            )
        }

    Column(modifier = Modifier
        .background(brush = lowerbrush, shape = RoundedCornerShape(40.dp,40.dp))
        .height(700.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally) {


        Text(
            text = "Select an Avatar",
            style = TextStyle(),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 29.sp,
            color = Color.White
        )
        Avatar(AvatarViewModel())
        Button(
            onClick = {
                GlobalVariables.selectedAvatar?.let {
                    saveDetails(
                        context = context,
                        name = GlobalVariables.username.value,
                        imageResource = it
                    )

                }
                navController.navigate("Usernames")
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF00BCD4)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Next  ",
                style = TextStyle(),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Serif,
                fontSize = 15.sp,
                color = Color.Black
            )
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward, contentDescription = "",
                tint = Color.Black
            )
        }
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
        R.drawable.avatar8,
        R.drawable.avatar9,
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.avatar10,
        R.drawable.images5,
        R.drawable.image5,
        R.drawable.images4

    )

    // Define the number of columns in the grid
    val columns = 4

    // Calculate the number of rows in the grid
    val rows = (images.size + columns - 1) / columns

    // Create a grid with the specified number of rows and columns
    Grid(columns = columns, rows = rows, images = images) { imageResource ->
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
    }*/

}

@Composable
fun Grid(
    columns: Int,
    rows: Int,
    images: List<Int>,
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


    // Call the AvatarScreen composable with the ViewModel
    AvatarScreen(navController = rememberNavController())
}

