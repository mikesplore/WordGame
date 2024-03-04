package com.mike.wordgame


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint.Align
import android.widget.Toast
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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

    // Read saved names from the file
    val savedNames = remember { readNameFile(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Saved Usernames",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }, colors = TopAppBarColors(
                    containerColor = Color(0xff7077A1),
                    scrolledContainerColor = Color(0xff7077A1),
                    navigationIconContentColor = Color(0xff7077A1),
                    actionIconContentColor = Color(0xff7077A1),
                    titleContentColor = Color.White

                ),


            )
        }
    )

    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff1F2138))
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Display saved names and NameProfiles
            Spacer(modifier = Modifier.height(25.dp))
            savedNames.forEach { name ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NameProfile(
                        name = name,
                        navController = navController,
                        sizee = 50.dp,
                        fontsize = 20.sp,
                        selectedAvatar = GlobalVariables.selectedAvatar
                    )
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

            Button(
                onClick = {
                    cleanFile(context = context)
                },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color(0xffF6B17A))
            ) {
                Text(
                    "Clear",
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
            }
        }
    }
}







//function call to store data
//saveDataToInternalStorage(context = context, message = textInputValue)
fun saveNameToFile(context: Context, name: String) {
    try {
        val fos: FileOutputStream =
            context.openFileOutput("usernameFile.txt", Context.MODE_APPEND) // Open file in append mode
        fos.write("$name\n".toByteArray()) // Add a newline character to separate entries
        fos.close()
        Toast.makeText(context, "Username saved successfully", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error saving username", Toast.LENGTH_SHORT).show()
    }
}



fun readNameFile(context: Context): List<String> {
    return try {
        val fin: FileInputStream = context.openFileInput("usernameFile.txt")
        val temp = StringBuilder()
        var a: Int
        while (fin.read().also { a = it } != -1) {
            temp.append(a.toChar())
        }
        fin.close()
        temp.toString().split("\n").filter { it.isNotBlank() }
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}

//clear the names file
fun cleanFile(context: Context) {
    try {
        val file = File(context.filesDir,"usernameFile.txt")
        file.writeText("") // Write an empty string to clear the file
        println("File cleaned successfully.")
    } catch (e: Exception) {
        e.printStackTrace()
        println("Error cleaning the file.")
    }
}

@Preview
@Composable
fun preview(){
    SavedUsernames(navController = rememberNavController())
}

