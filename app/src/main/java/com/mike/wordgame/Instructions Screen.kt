package com.mike.wordgame


import android.content.Context
import android.widget.Toast

import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

fun saveDataToInternalStorage(context: Context, message: String) {
    try {
        val fos: FileOutputStream =
            context.openFileOutput("demoFile.txt", Context.MODE_APPEND) // Open file in append mode
        fos.write(message.toByteArray())
        fos.write("\n".toByteArray()) // Add a newline character to separate entries
        fos.flush()
        fos.close()
        Toast.makeText(context, "Data saved successfully..", Toast.LENGTH_SHORT).show()
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error saving data", Toast.LENGTH_SHORT).show()
    }
}


fun readDataFromInternalStorage(context: Context): String {
    return try {
        val fin: FileInputStream = context.openFileInput("demoFile.txt")
        val temp = StringBuilder()
        var a: Int
        while (fin.read().also { a = it } != -1) {
            temp.append(a.toChar())
        }
        fin.close()
        temp.toString()
    } catch (e: IOException) {
        e.printStackTrace()
        "Error reading data"
    }
}


//function call to store data
//saveDataToInternalStorage(context = context, message = textInputValue)




@Preview
@Composable
fun preview(){
}

