package training.android.plannin_cuisine.Entities

import training.android.plannin_cuisine.Database.App

class RecipeIngredient(val recipeId: Int, val ingredientId: Int, var quantity: Int) {

    constructor(ingredientId: Int, quantity: Int) : this(3, ingredientId, quantity)
}

val crepeRecipeIngredient1 = RecipeIngredient(1, 5, 300)
val crepeRecipeIngredient2 = RecipeIngredient(1, 1, 3)
val crepeRecipeIngredient3 = RecipeIngredient(1, 2, 10)
val crepeRecipeIngredient4 = RecipeIngredient(1, 3, 5)
val crepeRecipeIngredient5 = RecipeIngredient(1, 4, 50)
val crepeRecipeIngredient6 = RecipeIngredient(1, 7, 60)
val crepeRecipeIngredient7 = RecipeIngredient(1, 34, 5)

val risottoRecipeIngredient1 = RecipeIngredient(2, 9, 125)
val risottoRecipeIngredient2 = RecipeIngredient(2, 8, 100)
val risottoRecipeIngredient3 = RecipeIngredient(2, 16, 10)
val risottoRecipeIngredient4 = RecipeIngredient(2, 4, 5)
val risottoRecipeIngredient5 = RecipeIngredient(2, 10, 1)
val risottoRecipeIngredient6 = RecipeIngredient(2, 11, 10)
val risottoRecipeIngredient7 = RecipeIngredient(2, 12, 10)
val risottoRecipeIngredient8 = RecipeIngredient(2, 18, 1)
val risottoRecipeIngredient9 = RecipeIngredient(2, 19, 1)
val risottoRecipeIngredient10 = RecipeIngredient(2, 21, 1)
val risottoRecipeIngredient11 = RecipeIngredient(2, 13, 2)
val risottoRecipeIngredient12 = RecipeIngredient(2, 15, 5)
val risottoRecipeIngredient13 = RecipeIngredient(2, 14, 5)

val miniBurgerRecipeIngredient1 = RecipeIngredient(22, 2)
val miniBurgerRecipeIngredient2 = RecipeIngredient(23, 80)
val miniBurgerRecipeIngredient3 = RecipeIngredient(9, 50)
val miniBurgerRecipeIngredient4 = RecipeIngredient(24, 2)
val miniBurgerRecipeIngredient5 = RecipeIngredient(25, 2)
val miniBurgerRecipeIngredient6 = RecipeIngredient(26, 2)
val miniBurgerRecipeIngredient7 = RecipeIngredient(27, 10)
val miniBurgerRecipeIngredient8 = RecipeIngredient(28, 5)
val miniBurgerRecipeIngredient9 = RecipeIngredient(29, 10)
val miniBurgerRecipeIngredient10 = RecipeIngredient(30, 2)
val miniBurgerRecipeIngredient11 = RecipeIngredient(31, 10)
val miniBurgerRecipeIngredient12 = RecipeIngredient(16, 5)
val miniBurgerRecipeIngredient13 = RecipeIngredient(15, 5)
val miniBurgerRecipeIngredient14 = RecipeIngredient(14, 5)

val PancakesRecipeIngredient1 = RecipeIngredient(4, 5, 60)
val PancakesRecipeIngredient2 = RecipeIngredient(4, 2, 7)
val PancakesRecipeIngredient3 = RecipeIngredient(4, 1, 1)
val PancakesRecipeIngredient4 = RecipeIngredient(4, 6, 25)
val PancakesRecipeIngredient5 = RecipeIngredient(4, 4, 16)
val PancakesRecipeIngredient6 = RecipeIngredient(4, 15, 5)
val PancakesRecipeIngredient7 = RecipeIngredient(4, 7, 7)

val pouletCocoReunionnaisRecipeIngredient1 = RecipeIngredient(5, 35, 1)
val pouletCocoReunionnaisRecipeIngredient2 = RecipeIngredient(5, 33, 10)
val pouletCocoReunionnaisRecipeIngredient3 = RecipeIngredient(5, 36, 6)
val pouletCocoReunionnaisRecipeIngredient4 = RecipeIngredient(5, 18, 1)
val pouletCocoReunionnaisRecipeIngredient5 = RecipeIngredient(5, 32, 1)
val pouletCocoReunionnaisRecipeIngredient6 = RecipeIngredient(5, 16, 5)
val pouletCocoReunionnaisRecipeIngredient7 = RecipeIngredient(5, 14, 5)
val pouletCocoReunionnaisRecipeIngredient8 = RecipeIngredient(5, 15, 5)


val RecipeIngredientList = mutableListOf<RecipeIngredient>(
    crepeRecipeIngredient1,
    crepeRecipeIngredient2,
    crepeRecipeIngredient3,
    crepeRecipeIngredient4,
    crepeRecipeIngredient5,
    crepeRecipeIngredient6,
    crepeRecipeIngredient7,
    risottoRecipeIngredient1,
    risottoRecipeIngredient2,
    risottoRecipeIngredient3,
    risottoRecipeIngredient4,
    risottoRecipeIngredient5,
    risottoRecipeIngredient6,
    risottoRecipeIngredient7,
    risottoRecipeIngredient8,
    risottoRecipeIngredient9,
    risottoRecipeIngredient10,
    risottoRecipeIngredient11,
    risottoRecipeIngredient12,
    risottoRecipeIngredient13,
    miniBurgerRecipeIngredient1,
    miniBurgerRecipeIngredient2,
    miniBurgerRecipeIngredient3,
    miniBurgerRecipeIngredient4,
    miniBurgerRecipeIngredient5,
    miniBurgerRecipeIngredient6,
    miniBurgerRecipeIngredient7,
    miniBurgerRecipeIngredient8,
    miniBurgerRecipeIngredient9,
    miniBurgerRecipeIngredient10,
    miniBurgerRecipeIngredient11,
    miniBurgerRecipeIngredient12,
    miniBurgerRecipeIngredient13,
    miniBurgerRecipeIngredient14,
    PancakesRecipeIngredient1,
    PancakesRecipeIngredient2,
    PancakesRecipeIngredient3,
    PancakesRecipeIngredient4,
    PancakesRecipeIngredient5,
    PancakesRecipeIngredient6,
    PancakesRecipeIngredient7,
    pouletCocoReunionnaisRecipeIngredient1,
    pouletCocoReunionnaisRecipeIngredient2,
    pouletCocoReunionnaisRecipeIngredient3,
    pouletCocoReunionnaisRecipeIngredient4,
    pouletCocoReunionnaisRecipeIngredient5,
    pouletCocoReunionnaisRecipeIngredient6,
    pouletCocoReunionnaisRecipeIngredient7,
    pouletCocoReunionnaisRecipeIngredient8
)