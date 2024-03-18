package com.mike.wordgame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController: NavController){
    val context = LocalContext.current
    val navController = rememberNavController()
    Column(modifier = Modifier
        .background(Color(0xff1F2138))
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
        ) {
        Row(modifier = Modifier
            .absolutePadding(0.dp, 20.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(text = "Player Profile",style = TextStyle(),
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                color = Color.White

            )
            Box(modifier = Modifier.width(20.dp))


        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.absolutePadding(0.dp,20.dp), horizontalAlignment = Alignment.CenterHorizontally) {


            NameProfile(
                name = GlobalVariables.username.value,
                navController = navController,
                sizee = 250.dp,
                fontsize = 190.sp,
                selectedAvatar = GlobalVariables.selectedAvatar
            )
            Box(modifier = Modifier
                .height(50.dp)

                .fillMaxWidth(),
                contentAlignment = Alignment.Center){
                Text(text = GlobalVariables.username.value,style = TextStyle(),
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White)
            }

            Column (modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .background(Color(0xff7077A1), shape = RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.SpaceEvenly){
                Text(text = "Total Words Guessed: 1200",style = TextStyle(),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(text = "High Score: 1399",style = TextStyle(),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(text = "Highest time reached: 500",style = TextStyle(),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }


    }
}
@Preview
@Composable
fun profileview(){
    Profile(rememberNavController())
}