package com.mike.wordgame


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.io.File

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedUsernames(navController: NavController) {
    val context = LocalContext.current

    // Read saved details from the file (name and image resource)
    val savedDetails = remember { readDetails(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {


                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                    Text(
                        "Saved Profiles",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                        Box(modifier = Modifier.width(30.dp))
                }}, colors = TopAppBarColors(
                    containerColor = Color(0xff7077A1),
                    scrolledContainerColor = Color(0xff7077A1),
                    navigationIconContentColor = Color(0xff7077A1),
                    actionIconContentColor = Color(0xff7077A1),
                    titleContentColor = Color.White
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(brush = back)
                .padding(horizontal = 16.dp)
                .padding(top = 57.dp), // Adjust top padding to push content below app bar
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Column() {
            savedDetails.forEach { (name, imageResource) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        color = Color.White
                    )
                }
            }
        }

            Row(
                modifier = Modifier
                    .absolutePadding(0.dp, 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        cleanusernameFile(context = context)
                    },
                    modifier = Modifier.padding(top = 30.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffF6B17A))
                ) {
                    Text(
                        "Clear List",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Light,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    )
                }

                Button(
                    onClick = {
                        cleanHighScoreFile(context = context)
                    },
                    modifier = Modifier.padding(top = 30.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xffF6B17A))
                ) {
                    Text(
                        "Clear H-Score",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Light,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}










//function call to store data
//saveDataToInternalStorage(context = context, message = textInputValue)
fun saveDetails(context: Context, name: String, imageResource: Int) {
    try {
        val fos: FileOutputStream =
            context.openFileOutput("usernameFile.txt", Context.MODE_APPEND) // Open file in append mode
        val entry = "$name,$imageResource\n" // Combine name and imageResource with a comma separator
        fos.write(entry.toByteArray()) // Write the combined entry to the file
        fos.close()
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error saving username and image", Toast.LENGTH_SHORT).show()
    }
}




fun readDetails(context: Context): List<Pair<String, Int>> {
    return try {
        val fin: FileInputStream = context.openFileInput("usernameFile.txt")
        val temp = StringBuilder()
        var a: Int
        while (fin.read().also { a = it } != -1) {
            temp.append(a.toChar())
        }
        fin.close()
        temp.toString().split("\n")
            .filter { it.isNotBlank() }
            .map { entry ->
                val parts = entry.split(",")
                Pair(parts[0], parts[1].toInt())
            }
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}


//clear the names file
fun cleanusernameFile(context: Context) {
    try {
        val file = File(context.filesDir,"usernameFile.txt")
        file.writeText("")
        Toast.makeText(context, "Username file cleared Successfully.", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error cleaning file", Toast.LENGTH_SHORT).show()
    }
}


fun cleanHighScoreFile(context: Context) {
    try {
        val file = File(context.filesDir,"HighScore.txt")
        file.writeText("")
        Toast.makeText(context, "High score file cleared successfully.", Toast.LENGTH_SHORT).show()    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error cleaning file", Toast.LENGTH_SHORT).show()    }
}
@Preview
@Composable
fun Preview(){
    SavedUsernames(navController = rememberNavController())
}

