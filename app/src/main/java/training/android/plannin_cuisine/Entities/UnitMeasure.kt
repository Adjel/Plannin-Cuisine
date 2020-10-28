package training.android.plannin_cuisine.Entities

import training.android.plannin_cuisine.Database.App

class UnitMeasure(val name: String, val symbol: String, val id: Long) {
    
    constructor(name: String, symbol: String) : this(name, symbol, -1) 
}

val centiliters = UnitMeasure("Centiliters","cl") // id = 1
val grams = UnitMeasure("Grams","gr") // id = 2
val number = UnitMeasure("Number", "unities") // id = 3


fun creatAppUnitMeasure() {
    App.database.createUnitMeasure(centiliters)
    App.database.createUnitMeasure(grams)
    App.database.createUnitMeasure(number)
}