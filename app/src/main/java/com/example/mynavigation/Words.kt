package com.example.mynavigation

fun getRandomWord(category: String, selectedLevel: String): String {
    return when (selectedLevel.lowercase()) {
        "beginner" -> beginnerLists[category]?.random() ?: "Start"
        "medium" -> mediumLists[category]?.random() ?: "Start"
        else -> "Start"
    }
}




// Lists for beginner level
private val beginnerLists = mapOf(
    "fruits" to listOf("App", "Ban", "Gra", "Kiw", "Pea", "Plu", "Dat"),


    "animals" to listOf("Dog", "Cat", "Lion", "Bear", "Wolf", "Fox", "Deer", "Horse", "Cow", "Goat", "Duck", "Swan", "Owl", "Eagle", "Frog", "Snake", "Rat", "Pig"),


    "countries" to listOf("Peru", "Iraq", "Iran", "Italy", "Fiji", "Ghana", "Japan", "Kenya", "Laos", "Mali", "Naur", "Nepa", "Oman", "Peru", "Togo", "Yeme", "Zamb"),


    "cars" to listOf("Ford", "Audi", "BMW", "Mini", "Fiat", "Lada", "Lexus", "Acura", "Opel", "Dats", "Tata", "Skod", "Fiat", "Suzuki"),


    "stationery" to listOf("Pen", "Ruler", "Tape", "Clip", "Note"),


    "phones" to listOf("Noki", "LG", "Sony", "Mi", "Oppo", "Vivo", "Real", "ASUS", "TCL", "Poco", "Infi", "Sharp", "CAT"),


    "electronics" to listOf("PC", "TV", "Fan", "Micr", "Oven", "Iron", "Blow", "VCR", "DVD", "CD", "PS4", "Xbox", "Wii", "LED", "LCD"),


    "computer parts" to listOf("CPU", "GPU", "RAM", "SSD", "Case", "Cool", "Fan", "Heat", "Sink", "Cabl", "Modu", "Switch", "Hub", "UPS")
)

// Lists for medium level
private val mediumLists = mapOf(
    "fruits" to listOf("Apple", "Pear", "Mango", "Lemon", "Lime", "Plum", "Grape", "Kiwi", "Peach", "Guava", "Cherry", "Dates", "Lychee", "Melon", "Papaya", "Banana", "Fig", "Peach", "Prune", "Berry"),
    "animals" to listOf("Dog", "Cat", "Lion", "Bear", "Wolf", "Fox", "Rabbit", "Deer", "Horse", "Cow", "Goat", "Duck", "Swan", "Owl", "Panda", "Fish", "Hawk", "Toad", "Mule", "Lamb"),
    "countries" to listOf("Japan", "India", "China", "Italy", "France", "Spain", "Brazil", "Egypt", "Mexico", "Chile", "Peru", "Cuba", "Ghana", "Kenya", "Nepal", "Yemen", "Niger", "Haiti", "Chad", "Laos"),
    "cars" to listOf("Ford", "Honda", "Tesla", "Audi", "Lexus", "Jeep", "Fiat", "Volvo", "Saab", "Kia", "Acura", "Alfa", "BMW", "Ferrari", "Fiat", "Buick", "Lamb", "Maser", "Mazda", "Nissan"),
    "stationery" to listOf("Pen", "Pencil", "Ruler", "Marker", "Eraser", "Glue", "Tape", "Paper", "Binder", "Crayon", "Chalk", "Ink", "Note", "Clip", "Brush"),
    "phones" to listOf("Nokia", "iPhone", "Samsung", "Pixel", "Sony", "Huawei", "OnePlus", "LG", "Xiaomi", "Motor", "Asus", "Google", "Oppo", "Vivo", "Honor"),
    "electronics" to listOf("Phone", "Laptop", "Tablet", "Watch", "Head", "Speaker", "Drone", "Console", "Mouse", "Drive", "Light", "Cable", "Clock", "Fan", "Iron"),
    "computer parts" to listOf("CPU", "GPU", "RAM", "SSD", "HDD", "Case", "Cool", "Fan", "Card", "Chip", "Port", "Chip", "Hub", "DVD", "WIFI")
)

//list of hard
//list of experts
//other lists

