package training.android.plannin_cuisine.Entities

import android.content.ContentValues
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import training.android.plannin_cuisine.Database.*

data class Ingredient(val name: String, val unitMeasureId: Int, val id: Long) {

    constructor(name: String, unitMeasureId: Int) : this(name, unitMeasureId, -1)
}


// cl id = 1
// gr id = 2
// number id = 3

val oeuf = Ingredient("Oeuf", 3)
val sucre = Ingredient("Sucre", 2)
val huile = Ingredient("Huile", 1)
val beurre = Ingredient("Beurre", 2)
val farine = Ingredient("Farine", 2)
val levure = Ingredient("Levure", 2)
val lait = Ingredient("Lait", 1)
val riz = Ingredient("Riz", 2)
val champignon = Ingredient("Champignon(s)", 2)
val bouillonDeLegumes = Ingredient("Bouillon de légume", 3)
val vinBlanc = Ingredient("Vin blanc", 1)
val parmesan = Ingredient("Parmesan", 2)
val persil = Ingredient("Persil (brins)", 2)
val poivre = Ingredient("Poivre", 2)
val sel = Ingredient("Sel", 2)
val huileDolive = Ingredient("Huile d'olive", 1)
val cremeFraiche = Ingredient("Crème fraîche", 2)
val oignon = Ingredient("Oigon", 2)
val ail = Ingredient("Ail", 2)
val tomate = Ingredient("Tomate", 3)
val echalote = Ingredient("Echalote", 3)
val petitPainABurger = Ingredient("Petits pains à burger", 3)
val hacheDeVeau = Ingredient("Haché de veau", 2)
val comte = Ingredient("Tranche de comté", 3)
val salade = Ingredient("Salade (feuille(s))",3)
val oignonRouge = Ingredient("Oignon rouge", 3)
val sucreRoux = Ingredient("Sucre roux", 2)
val paprika = Ingredient("Paprika", 2)
val vinaigreBalsamique = Ingredient("Vinaigre balsamique", 1)
val cornichons = Ingredient("Cornichons", 3)
val ketchup = Ingredient("Ketchup", 2)
val poulet = Ingredient("Poulet (coupé en morceaux)", 3)
val poivronRouge = Ingredient("Poivron rouge", 3)
val laitDeCoco = Ingredient("Lait de coco", 1)
val rhum = Ingredient("Rhum", 1)
val coulisDeTomate = Ingredient("Coulis de tomate", 1)



fun creatAppIngredientList() {
    App.database.createIngredient(oeuf)
    App.database.createIngredient(sucre)
    App.database.createIngredient(huile)
    App.database.createIngredient(beurre)
    App.database.createIngredient(farine)
    App.database.createIngredient(levure)
    App.database.createIngredient(lait)
    App.database.createIngredient(riz)
    App.database.createIngredient(champignon)
    App.database.createIngredient(bouillonDeLegumes)
    App.database.createIngredient(vinBlanc)
    App.database.createIngredient(parmesan)
    App.database.createIngredient(persil)
    App.database.createIngredient(poivre)
    App.database.createIngredient(sel)
    App.database.createIngredient(huileDolive)
    App.database.createIngredient(cremeFraiche)
    App.database.createIngredient(oignon)
    App.database.createIngredient(ail)
    App.database.createIngredient(tomate)
    App.database.createIngredient(echalote)
    App.database.createIngredient(petitPainABurger)
    App.database.createIngredient(hacheDeVeau)
    App.database.createIngredient(comte)
    App.database.createIngredient(salade)
    App.database.createIngredient(oignonRouge)
    App.database.createIngredient(sucreRoux)
    App.database.createIngredient(paprika)
    App.database.createIngredient(vinaigreBalsamique)
    App.database.createIngredient(cornichons)
    App.database.createIngredient(ketchup)
    App.database.createIngredient(poivronRouge)
    App.database.createIngredient(laitDeCoco)
    App.database.createIngredient(rhum)
    App.database.createIngredient(poulet)
    App.database.createIngredient(coulisDeTomate)
}