package com.mike.wordgame


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

val backbrush  = Brush.verticalGradient(
    colors = listOf(
        Color(0xff2196F3), // Blue
        Color(0xff2196F3), // Light Blue
        Color(0xff00008B)

    )
)

@Composable
fun HelloScreen(navController: NavController){
    val username = GlobalVariables.username.value
Column(modifier = Modifier
    .background(brush = back)
    .fillMaxSize(),
    verticalArrangement = Arrangement.SpaceAround,
    horizontalAlignment = Alignment.CenterHorizontally) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = GlobalVariables.username.value.capitalize(),
            style = TextStyle(),
            fontFamily = FontFamily.SansSerif,
            fontSize = 70.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold)


    }
    Box(modifier = Modifier
        .height(200.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center){
        profile(name = username,200.dp,170.sp)
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp),
        contentAlignment = Alignment.Center){
       ProceedToGameButton(navController = navController)
    }



    }


}

@Composable
fun ProceedToGameButton(navController: NavController){
    Button(onClick = {

        navController.navigate("CategoryScreen") },
        modifier = Modifier
            .width(130.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(Color(0xff2196F3)),
        shape = RoundedCornerShape(10.dp)) {

        Text(text = "Next  ",style = TextStyle(), fontSize = 20.sp,
            fontFamily = FontFamily.Serif,color=Color.Black,
            fontWeight = FontWeight.Bold)
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "arrow", tint = Color.Black)

    }
} //2130968591


@Composable
fun profile(name: String, profilesize: Dp, lettersize: TextUnit) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
    }

    val displayContent: @Composable () -> Unit = if (imageUri != null) {
        // Display image if an image is selected
        {
            Box(
                modifier = Modifier
                    .size(profilesize)
                    .background(Color.White, shape = CircleShape)
                    .border(3.dp, color = Color(0xff67C6E3), shape = CircleShape)
                    .clickable {
                        launcher.launch("image/*")
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data = imageUri)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = "profile",
                    modifier = Modifier
                        .size(profilesize)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    } else {
        // Display first letter of the name if no image is selected
        {
            Box(
                modifier = Modifier
                    .size(profilesize)
                    .background(brush = backbrush , shape = CircleShape)
                    .border(3.dp, color = Color(0xff67C6E3), shape = CircleShape)
                    .clickable {
                        launcher.launch("image/*")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name.take(1).capitalize(),
                    style = TextStyle(
                        fontSize = lettersize,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black
                )
            }
        }
    }


        displayContent()

}



@Preview(heightDp = 850)
@Composable
fun HelloScreenPreview(){
    HelloScreen(navController = rememberNavController())
}

