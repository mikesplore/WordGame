package com.example.mynavigation

fun getRandomWord(category: String): String {
    return when (category.lowercase()) {
        "fruits" -> {
            val fruitList = listOf(
                "Apple", "Banana", "Orange", "Grape", "Kiwi",
                "Pineapple", "Strawberry", "Watermelon", "Blueberry", "Peach",
                "Mango", "Pear", "Cherry", "Lemon", "Lime",
                "Apricot", "Plum", "Raspberry", "Blackberry", "Pomegranate",
                "Avocado", "Coconut", "Fig", "Guava", "Lychee",
                "Nectarine", "Passionfruit", "Persimmon", "Tangerine", "Dragonfruit",
                "Cranberry", "Cantaloupe", "Kiwifruit", "Grapefruit", "Starfruit",
                "Honeydew", "Papaya", "Pumpkin", "Mandarin", "Clementine",
                "Melon", "Raisin", "Date", "Gourd", "Ginger",
                "Plaintain", "Pitaya", "Quince", "Soursop", "Kumquat"
            )

            fruitList.random()
        }

        "animals" -> {
            val animalList = listOf(
                "Dog", "Cat", "Elephant", "Lion", "Tiger",
                "Monkey", "Giraffe", "Zebra", "Kangaroo", "Koala",
                "Penguin", "Panda", "Sloth", "Bear", "Wolf",
                "Fox", "Rabbit", "Deer", "Horse", "Cow",
                "Sheep", "Goat", "Chicken", "Duck", "Turkey",
                "Swan", "Owl", "Eagle", "Pigeon", "Sparrow",
                "Parrot", "Flamingo", "Peacock", "Crocodile", "Alligator",
                "Turtle", "Frog", "Snake", "Lizard", "Iguana",
                "Chameleon", "Gecko", "Tortoise", "Hamster", "Guinea Pig",
                "Rat", "Mouse", "Rabbit", "Ferret", "Hedgehog"
            )
            animalList.random()
        }

        "countries" -> {
            val countryList = listOf(
                "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua", "Argentina", "Armenia", "Australia", "Austria",
                "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan",
                "Bolivia", "Bosnia", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Chad", "Chile", "China",
                "Colombia", "Comoros", "Congo", "Croatia", "Cuba", "Cyprus", "Denmark", "Djibouti", "Dominica",
                "Dominican", "East", "Ecuador", "Egypt", "Equatorial", "Eritrea", "Estonia", "Eswatini", "Ethiopia",
                "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada",
                "Guatemala", "Guinea", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran",
                "Iraq", "Ireland", "Israel", "Italy", "Ivory", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya",
                "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
                "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall",
                "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
                "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Zealand", "Nicaragua", "Niger", "Nigeria", "Norway",
                "Oman", "Pakistan", "Panama", "Papua", "Paraguay", "Peru", "Philippines", "Poland", "Portugal",
                "Qatar", "Romania", "Russia", "Rwanda", "Saint", "Samoa", "San", "Sao", "Saudi", "Senegal",
                "Serbia", "Seychelles", "Sierra", "Singapore", "Slovakia", "Slovenia", "Solomon", "Somalia",  "Spain",
                "Sri", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand",
                "Togo", "Tonga", "Trinidad", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United",
                "Uruguay", "Uzbekistan", "Vanuatu", "Vatican", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
            )


            countryList.random()
        }

        "cars" -> {
            val carList = listOf(
                "Toyota", "Honda", "Ford", "Chevrolet", "Volkswagen",
                "Nissan", "BMW", "MercedesBenz", "Audi", "Hyundai",
                "Kia", "Subaru", "Mazda", "Volvo", "Lexus",
                "Acura", "Jeep", "Tesla", "Land Rover", "Porsche",
                "Ferrari", "Lamborghini", "Maserati", "Bugatti", "Bentley",
                "RollsRoyce", "Jaguar", "Lincoln", "Cadillac", "Chrysler",
                "Dodge", "Ram", "Buick", "GMC", "Aston Martin",
                "Infiniti", "Mini", "Alfa Romeo", "Genesis", "Lotus",
                "McLaren", "Smart", "Suzuki", "Fiat", "Mitsubishi",
                "Peugeot", "Citroën", "Renault", "Dacia", "SEAT"
            )

            carList.random()
        }

        "stationery" -> {
            val stationeryList = listOf(
                "Pen", "Pencil", "Eraser", "Ruler", "Notebook",
                "Marker", "Highlighter", "Sharpener", "Scissors", "Glue",
                "Stapler", "Tape", "Paperclip", "Binder", "Calculator",
                "Folder", "IndexCard", "RubberBand", "Chalk", "Whiteboard",
                "Paintbrush", "Watercolor", "Sketchbook", "Crayon", "Clipboard",
                "DrawingPin", "PushPin", "CorrectionFluid", "Compass", "Protractor",
                "FountainPen", "BallpointPen", "MechanicalPencil", "ColoredPencil", "GraphitePencil",
                "PencilCase", "NotePad", "SpiralNotebook", "PostItNote", "StickyNote",
                "IndexDivider", "PresentationBinder", "Laminator", "StampPad", "InkPad",
                "Stamp", "Ink", "StampPad", "StampHolder", "StampDispenser"
            )

            stationeryList.random()
        }

        "phones" -> {
            val phone = listOf(
                "Samsung", "iPhone", "Nokia", "Motorola", "Itel",
                "Google", "Huawei", "LG", "Sony", "Xiaomi",
                "OnePlus", "Oppo", "Vivo", "Realme", "Asus",
                "Lenovo", "BlackBerry", "HTC", "ZTE", "Alcatel",
                "Honor", "Meizu", "TCL", "Poco", "Infinix",
                "Sharp", "Panasonic", "LeEco", "Gionee", "Coolpad",
                "Micromax", "Yota", "Vertu", "CAT", "Wiko",
                "Razer", "Essential", "Fairphone", "Moto", "Elephone"
            )

            phone.random()
        }
        "electronics" ->{
            val electronics = listOf(
            "Smartphone", "Laptop", "Tablet", "Smartwatch", "Headphones",
            "Speaker", "Camera", "Drone", "Console", "Earbuds",
            "Router", "Mouse", "Drive", "Keyboard", "Recorder",
            "Projector", "Thermostat", "Toothbrush", "Cleaner", "Bulb",
            "Charger", "Flashdrive", "Lock", "Microwave", "Refrigerator",
            "Blender", "Dishwasher", "Printer", "Scanner", "Monitor",
            "Television", "Stereo", "Clock", "Alarm", "Intercom",
            "Speakerphone", "Remote", "Gamepad", "Joystick", "Modem",
            "Switch", "Hub", "Server", "NAS", "Firewall",
            "Drill", "Router", "Iron", "Fan", "Oven"
        )

        electronics.random()
    }
        "computer parts" -> {
            val computerPartList = listOf(
            "CPU", "GPU", "RAM", "Motherboard", "HardDrive",
            "SSD", "PowerSupply", "Case", "CoolingFan", "CPUCooler",
            "GraphicsCard", "Memory", "Storage", "OpticalDrive", "NetworkCard",
            "SoundCard", "ExpansionCard", "HeatSink", "ThermalPaste", "Cable",
            "Monitor", "Keyboard", "Mouse", "Speakers", "Webcam",
            "Microphone", "Headset", "Printer", "Scanner", "Projector",
            "UPS", "EthernetCable", "USBHub", "BluetoothAdapter", "WiFiAdapter",
            "ExternalHardDrive", "DockingStation", "FlashDrive", "MemoryCard", "CardReader"
        )
        computerPartList.random()
    }
        else -> "Start"

    }
}





