import android.content.Context

fun storeHighScore(context: Context, highscore: Int) {
    val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    with (sharedPref.edit()) {
        putInt("HIGH_SCORE", highscore)
        apply()
    }
}

fun getHighScore(context: Context): Int {
    val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    return sharedPref.getInt("HIGH_SCORE", 0)
}
