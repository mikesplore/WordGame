package com.mike.wordgame

fun getRandomWord(category: String, selectedLevel: String): String {
    return when (selectedLevel.lowercase()) {
        "beginner" -> beginnerLists[category]?.random() ?: "Start"
        "medium" -> mediumLists[category]?.random() ?: "Start"
        "hard" -> hardLists[category]?.random() ?: "Start"
        "expert" -> expertLists[category]?.random() ?: "Start"
        "master" -> masterLists[category]?.random() ?: "Start"
        "impossible" -> impossibleLists[category]?.random() ?: "Start"
        else -> "Start"
    }
}




// Lists for beginner level
private val beginnerLists = mapOf(
    "fruits" to listOf("App", "Ban", "Gra", "Kiw", "Pea", "Plu", "Dat"),
    "animals" to listOf("Dog", "Cat", "Lion", "Bear", "Wolf", "Fox", "Deer", "Horse", "Cow",
    "Goat", "Duck", "Swan", "Owl", "Eagle", "Frog", "Snake", "Rat", "Pig"),
    "countries" to listOf("Peru", "Iraq", "Iran", "Italy", "Fiji", "Ghana", "Japan", "Kenya", "Laos", "Mali", "Naur", "Nepa", "Oman", "Peru", "Togo", "Yeme", "Zamb"),
    "cars" to listOf("Ford", "Audi", "BMW", "Mini", "Fiat", "Lada", "Lexus", "Acura", "Opel", "Dats", "Tata", "Skod", "Fiat", "Suzuki"),
    "stationery" to listOf("Pen", "Ruler", "Tape", "Clip", "Note"),
    "phones" to listOf("Noki", "LG", "Sony", "Mi", "Oppo", "Vivo", "Real", "ASUS", "TCL", "Poco", "Infi", "Sharp", "CAT"),
    "electronics" to listOf("PC", "TV", "Fan", "Micr", "Oven", "Iron", "Blow", "VCR", "DVD", "CD", "PS4", "Xbox", "Wii", "LED", "LCD"),
    "computer parts" to listOf("CPU", "GPU", "RAM", "SSD", "Case", "Cool", "Fan", "Heat", "Sink", "Cabl", "Modu", "Switch", "Hub", "UPS"))

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

private val hardLists = mapOf(
    "fruits" to listOf(
        "Durian", "Jackfruit", "Mangosteen", "Rambutan", "Soursop",
        "Longan", "Lychee", "Dragon Fruit", "Star Fruit", "Passion Fruit",
        "Guava", "Pineapple", "Kiwi", "Fig", "Date"
    ),
    "animals" to listOf(
        "Elephant", "Giraffe", "Rhino", "Hippo", "Penguin",
        "Tiger", "Lion", "Leopard", "Kangaroo", "Cheetah",
        "Gorilla", "Zebra", "Camel", "Panda", "Polar Bear"
    ),
    "countries" to listOf(
        "Argentina", "Bangladesh", "Canada", "Ethiopia", "Indonesia",
        "Russia", "Germany", "Mexico", "Brazil", "Nigeria",
        "South Africa", "Egypt", "Saudi Arabia", "Australia", "Turkey"
    ),
    "cars" to listOf(
        "Mercedes", "Chevrolet", "Hyundai", "Renault", "Volkswagen",
        "Toyota", "Honda", "Ford", "BMW", "Audi",
        "Volvo", "Subaru", "Tesla", "Lexus", "Mitsubishi"
    ),
    "stationery" to listOf(
        "Marker", "Scissors", "Stapler", "Highlighter", "Binder",
        "Eraser", "Glue", "Tape", "Calculator", "Pencil",
        "Notebook", "Ruler", "Sharpener", "Clipboard", "Compass"
    ),
    "phones" to listOf(
        "BlackBerry", "Motorola", "HTC", "Lenovo", "ZTE",
        "OnePlus", "Xiaomi", "Google Pixel", "Huawei", "Sony Xperia",
        "Nokia", "Apple iPhone", "Samsung Galaxy", "LG", "Oppo"
    ),
    "electronics" to listOf(
        "Refrigerator", "Television", "Camera", "Projector", "Router",
        "Smartwatch", "Tablet", "Headphones", "Drone", "Smart Speaker",
        "Game Console", "Printer", "Monitor", "Smart Home Hub", "Fitness Tracker"
    ),
    "computer parts" to listOf(
        "Motherboard", "Power supply", "Graphics card", "Network card", "Sound card",
        "Processor", "RAM", "Hard Drive", "Solid State Drive", "Cooling Fan",
        "Case", "Optical Drive", "Heat Sink", "Expansion Card", "USB Flash Drive"
    )
)


private val expertLists = mapOf(
    "fruits" to listOf(
        "Açaí", "Ackee", "Salak", "Cupuaçu", "Pitaya",
        "Durian", "Jackfruit", "Mangosteen", "Rambutan", "Soursop",
        "Longan", "Lychee", "Dragon Fruit", "Star Fruit", "Passion Fruit"
    ),
    "animals" to listOf(
        "Quokka", "Okapi", "Axolotl", "Quetzal", "Zebu",
        "Elephant", "Giraffe", "Rhino", "Hippo", "Penguin",
        "Tiger", "Lion", "Leopard", "Kangaroo", "Cheetah"
    ),
    "countries" to listOf(
        "Bhutan", "Luxembourg", "Maldives", "Seychelles", "Liechtenstein",
        "Argentina", "Bangladesh", "Canada", "Ethiopia", "Indonesia",
        "Russia", "Germany", "Mexico", "Brazil", "Nigeria"
    ),
    "cars" to listOf(
        "Bugatti", "Lamborghini", "Maserati", "McLaren", "Rolls-Royce",
        "Mercedes", "Chevrolet", "Hyundai", "Renault", "Volkswagen",
        "Toyota", "Honda", "Ford", "BMW", "Audi"
    ),
    "stationery" to listOf(
        "Chalk", "Eraser", "Correction fluid", "Graphite", "Binder clip",
        "Marker", "Scissors", "Stapler", "Highlighter", "Binder"
    ),
    "phones" to listOf(
        "OnePlus", "Xiaomi", "Huawei", "Google Pixel", "Apple iPhone",
        "BlackBerry", "Motorola", "HTC", "Lenovo", "ZTE"
    ),
    "electronics" to listOf(
        "Virtual reality headset", "Drone", "Smartwatch", "Bluetooth speaker", "Electric scooter",
        "Refrigerator", "Television", "Camera", "Projector", "Router"
    ),
    "computer parts" to listOf(
        "Central processing unit", "Random access memory", "Solid-state drive", "Optical drive", "Computer case",
        "Motherboard", "Power supply", "Graphics card", "Network card", "Sound card"
    )
)



private val masterLists = mapOf(
    "fruits" to listOf("Mangosteen", "Lychee", "Dragonfruit", "Passionfruit", "Rambutan"),
    "animals" to listOf("Platypus", "Komodo Dragon", "Narwhal", "Chameleon", "Quokka"),
    "countries" to listOf("Slovakia", "Mauritius", "Kyrgyzstan", "Bhutan", "Eswatini"),
    "cars" to listOf("Pagani", "Koenigsegg", "Bentley", "Aston Martin", "Ferrari"),
    "stationery" to listOf("Calligraphy pen", "Quill", "Dip pen", "Fountain pen", "Nib holder"),
    "phones" to listOf("Vertu", "Sirin Labs", "Turing", "Goldvish", "Ulysse Nardin"),
    "electronics" to listOf("Thermal imaging camera", "Particle accelerator", "Fusion reactor", "Cryogenic freezer", "Electron microscope"),
    "computer parts" to listOf("Quantum processor", "Optical computing chip", "Neuromorphic processor", "Quantum computer", "Optical computing device")
)

private val impossibleLists = mapOf(
    "fruits" to listOf(
        "Durian", "Ackee", "Jabuticaba", "Salak", "Cupuaçu",
        "Mangosteen", "Rambutan", "Langsat", "Santol", "Durian",
        "Jackfruit", "Horned Melon", "Yangmei", "Pitaya", "Soursop"
    ),
    "animals" to listOf(
        "Platypus", "Axolotl", "Quokka", "Okapi", "Kakapo",
        "Tarsier", "Coelacanth", "Fossa", "Maned Wolf", "Solenodon",
        "Numbat", "Quoll", "Sifaka", "Tuatara", "Zorilla"
    ),
    "countries" to listOf(
        "Liechtenstein", "Andorra", "Nauru", "Palau", "Tuvalu",
        "San Marino", "Monaco", "Marshall Islands", "Saint Kitts and Nevis", "Dominica",
        "Antigua and Barbuda", "Seychelles", "Saint Vincent and the Grenadines", "Grenada", "Saint Lucia"
    ),
    "cars" to listOf(
        "Zenvo", "Lykan", "Apollo", "W Motors", "SSC",
        "Koenigsegg", "Pagani", "Bugatti", "Lamborghini", "Ferrari",
        "McLaren", "Aston Martin", "Porsche", "Rolls-Royce", "Bentley"
    ),
    "stationery" to listOf(
        "Correction tape", "Rotary cutter", "Bone folder", "Scriber", "Folding knife",
        "Embossing tool", "Craft knife", "Quilling needle", "Glass cutting blade", "Stencil brush",
        "Palette knife", "Wood burning tool", "Craft punch", "Gouache brush", "Chalk marker"
    ),
    "phones" to listOf(
        "Turing Phone", "Sirin Labs Solarin", "Savelli", "Vertu Signature", "Caviar iPhone",
        "Gresso Regal", "Lamborghini Alpha-One", "Goldvish Eclipse", "Tonino Lamborghini 88 Tauri", "Mobiado Grand Touch Executive"
    ),
    "electronics" to listOf(
        "Neodymium-magnet", "Plasma-globe", "Van-de-Graaff generator", "Magnetron", "Nuclear-magnetic-resonance-machine",
        "Ferrofluid-display", "Laser-microphone", "Electron microscope", "Ion thruster", "Particle accelerator",
        "Gallium nitride transistor", "Quantum dot display", "Optical-tweezers", "Piezoelectric sensor", "Synchrotron"
    ),
    "computer parts" to listOf(
        "Nanotransistor", "Quantum annealing chip", "Molecular transistor", "Spintronics device", "Quantum computer chip",
        "Bionic chip", "Neuromorphic chip", "Biocomputer", "DNA computer", "Optical computer",
        "Biochips", "Neural network processor", "Memristor", "Quantum repeater", "Magnetic logic gate"
    )
)


