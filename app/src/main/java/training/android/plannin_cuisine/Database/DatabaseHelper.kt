package training.android.plannin_cuisine.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.provider.BaseColumns._ID
import android.util.Log
import androidx.annotation.RequiresApi
import training.android.plannin_cuisine.Entities.*


val TAG = "DatabaseHelper"

/**     Déclaration du nom et de la version de la base de données
 */


const val DATABASE_VERSION = 1
const val DATABASE_NAME = "CuisinDatabase"


/** DECLARATION DES TABLES DE LA BASE DE DONNEES (AINSI QUE DE LEURS ELEMENTS)  suivies de la délcaration de la STRING qui sera excécutée par SQLite
 */


/**     Déclaration de la table de la classe Ingredient et de ses colonnes
 */

const val TABLE_INGREDIENT_NAME = "ingredient"
const val COLUMN_INGREDIENT_NAME = "name"
const val COLUMN_INGREDIENT_UNITMEASUREID = "unitmeasureid"

private const val SQL_CREATE_INGREDIENT_TABLES_ENTRIES =
    "CREATE TABLE ${TABLE_INGREDIENT_NAME} (" +
            "$_ID INTEGER PRIMARY KEY," +
            "${COLUMN_INGREDIENT_NAME} TEXT," +
            "${COLUMN_INGREDIENT_UNITMEASUREID} INTEGER)"

private const val SQL_DELETE_INGREDIENT_TABLES_ENTRIES =
    "DROP TABLE IF EXISTS ${TABLE_INGREDIENT_NAME}"


const val TABLE_UNITMEASURE_NAME = "unitmeasure"
const val COLUMN_UNITMEASURE_NAME = "name"
const val COLUMN_UNITMEASURE_SYMBOL = "symbol"

private const val SQL_CREATE_UNITMEASURE_TABLES_ENTRIES =
    "CREATE TABLE ${TABLE_UNITMEASURE_NAME} (" +
            "$_ID INTEGER PRIMARY KEY," +
            "${COLUMN_UNITMEASURE_NAME} TEXT," +
            "${COLUMN_UNITMEASURE_SYMBOL} TEXT)"

private const val SQL_DELETE_UNITMEASURE_TABLE_ENTRIES =
    "DROP TABLE IF EXISTS ${TABLE_UNITMEASURE_NAME}"


const val TABLE_INGREDIENTOFLIST_NAME = "ingredientoflist"
const val COLUMN_INGREDIENTOFLIST_INGREDIENTID = "ingredientid"
const val COLUMN_INGREDIENTOFLIST_QUANTITY = "quantity"

private const val SQL_CREATE_INGREDIENTOFLIST_TABLE_ENTRIES =
    "CREATE TABLE ${TABLE_INGREDIENTOFLIST_NAME} (" +
            "$_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${COLUMN_INGREDIENTOFLIST_INGREDIENTID} INTEGER UNIQUE," +
            "${COLUMN_INGREDIENTOFLIST_QUANTITY} INTEGER)"


private const val SQL_DELETE_INGREDIENTOFLIST_TABLE_ENTRIES =
    "DROP TABLE IF EXISTS ${TABLE_INGREDIENTOFLIST_NAME}"


const val TABLE_RECIPEINGREDIENT_NAME = "recipeingredient"
const val COLUMN_RECIPEINGREDIENT_RECIPEID = "recipeid"
const val COLUMN_RECIPEINGREDIENT_INGREDIENTID = "ingredientid"
const val COLUMN_RECIPEINGREDIENT_QUANTITY = "quantity"


private const val SQL_CREATE_RECIPEINGREDIENT_TABLES_ENTRIES =
    "CREATE TABLE $TABLE_RECIPEINGREDIENT_NAME (" +
            "$_ID INTEGER PRIMARY KEY," +
            "$COLUMN_RECIPEINGREDIENT_RECIPEID INTEGER," +
            "$COLUMN_RECIPEINGREDIENT_INGREDIENTID INTEGER," +
            "$COLUMN_RECIPEINGREDIENT_QUANTITY INTEGER)"

private const val SQL_DELETE_RECIPEINGREDIENT_TABLE_ENTRIES =
    "DROP TABLE IF EXISTS $TABLE_RECIPEINGREDIENT_NAME"


const val TABLE_RECIPE_NAME = "recipe"
const val COLUMN_RECIPE_NAME = "name"
const val COLUMN_RECIPE_DESCRIPTION = "description"
const val COLUMN_RECIPE_RECIPEINGREDIENTID = "ingredientid"
const val COLUMN_RECIPE_ID = "id"

private const val SQL_CREATE_RECIPE_TABLES_ENTRIES =
    "CREATE TABLE $TABLE_RECIPE_NAME (" +
            "$_ID INTEGER PRIMARY KEY," +
            "$COLUMN_RECIPE_NAME TEXT," +
            "$COLUMN_RECIPE_DESCRIPTION TEXT," +
            "$COLUMN_RECIPE_RECIPEINGREDIENTID INTEGER," +
            "$COLUMN_RECIPE_ID INTEGER UNIQUE)"

private const val SQL_DELETE_RECIPE_TABLE_ENTRIES = "DROP TABLE IF EXISTS ${TABLE_RECIPE_NAME}"


const val TABLE_RECIPEWISHLIST_NAME = "recipewihlist"
const val COLUMN_RECIPEWISHLIST_RECIPEID = "recipeid"
const val COLUMN_RECIPEWISHLIST_NUMBEROFPERSON = "numberofperson"
const val COLUMN_RECIPEWISHLIST_ID = "id"


private const val SQL_CREATE_RECIPEWISHLIST_TABLES_ENTRIES =
    "CREATE TABLE ${TABLE_RECIPEWISHLIST_NAME} (" +
            "$_ID INTEGER PRIMARY KEY," +
            "${COLUMN_RECIPEWISHLIST_RECIPEID} INTEGER," +
            "${COLUMN_RECIPEWISHLIST_NUMBEROFPERSON} INTEGER," +
            "${COLUMN_RECIPEWISHLIST_ID} INTEGER)"


private const val SQL_DELETE_RECIPEWISHLIST_TABLE_ENTRIES =
    "DROP TABLE IF EXISTS ${TABLE_RECIPEWISHLIST_NAME}"


/**         Déclaration des constantes de lecture des données
 */

private const val INGREDIENT_QUERY_TABLE = "SELECT * FROM $TABLE_INGREDIENT_NAME"
private const val INGREDIENTOFLIST_QUERY_TABLE = "SELECT * FROM $TABLE_INGREDIENTOFLIST_NAME"
private const val RECIPE_QUERY_TABLE = "SELECT * FROM $TABLE_RECIPE_NAME"
private const val RECIPEWISHLIST_QUERY_TABLE = "SELECT * FROM $TABLE_RECIPEWISHLIST_NAME"


/**         Déclaration de la classe Database
 */

class CuisinDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_INGREDIENT_TABLES_ENTRIES)
        db?.execSQL(SQL_CREATE_UNITMEASURE_TABLES_ENTRIES)
        db?.execSQL(SQL_CREATE_INGREDIENTOFLIST_TABLE_ENTRIES)
        db?.execSQL(SQL_CREATE_RECIPEINGREDIENT_TABLES_ENTRIES)
        db?.execSQL(SQL_CREATE_RECIPE_TABLES_ENTRIES)
        db?.execSQL(SQL_CREATE_RECIPEWISHLIST_TABLES_ENTRIES)

        Log.d(TAG, "database crée")
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_INGREDIENT_TABLES_ENTRIES)
        db?.execSQL(SQL_DELETE_UNITMEASURE_TABLE_ENTRIES)
        db?.execSQL(SQL_DELETE_INGREDIENTOFLIST_TABLE_ENTRIES)
        db?.execSQL(SQL_DELETE_RECIPEINGREDIENT_TABLE_ENTRIES)
        db?.execSQL(SQL_DELETE_RECIPE_TABLE_ENTRIES)
        db?.execSQL(SQL_DELETE_RECIPEWISHLIST_TABLE_ENTRIES)
        onCreate(db)
    }


    fun createIngredient(ingredient: Ingredient) {

        val values = ContentValues()
        values.put(COLUMN_INGREDIENT_NAME, ingredient.name)
        values.put(COLUMN_INGREDIENT_UNITMEASUREID, ingredient.unitMeasureId)

        App.database.writableDatabase.insert(TABLE_INGREDIENT_NAME, null, values)
    }

    fun readIngredientById(ingredientOfList: IngredientOfList): Ingredient {

        // selectQuery est un clause, le code dans la rawQuery est exécutée lorsque la clause est vérifiée

        val selectQuery =
            "SELECT * FROM $TABLE_INGREDIENT_NAME WHERE $_ID = ${ingredientOfList.ingredientId}"

        var ingredients = Ingredient("", 0)

        readableDatabase.rawQuery(selectQuery, null).use { cursor ->
            while (cursor.moveToNext()) {
                val ingredient = Ingredient(
                    cursor.getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENT_UNITMEASUREID)),
                    cursor.getLong(cursor.getColumnIndex(_ID))
                )
                ingredients = ingredient
            }
        }
        Log.d(TAG, "Ici je recupère un ou plusieurs ingrédients ")
        return ingredients
    }

    fun readUnitMeasureById(ingredient: Ingredient): UnitMeasure {

        var unitMeasures = UnitMeasure("", "")

        val selectQuery =
            "SELECT * FROM $TABLE_UNITMEASURE_NAME WHERE $_ID = ${ingredient.unitMeasureId}"

        readableDatabase.rawQuery(selectQuery, null).use { cursor ->
            while (cursor.moveToNext()) {
                val unitMeasure = UnitMeasure(
                    cursor.getString(cursor.getColumnIndex(COLUMN_UNITMEASURE_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_UNITMEASURE_SYMBOL)),
                    cursor.getLong(cursor.getColumnIndex(_ID))
                )
                unitMeasures = unitMeasure
            }
        }
        Log.d(TAG, "Ici je recupère une unité de mesure")
        return unitMeasures
    }


    fun getAllIngredient(): ArrayList<Ingredient> {

        val ingredients = ArrayList<Ingredient>()

        readableDatabase.rawQuery(INGREDIENT_QUERY_TABLE, null).use { cursor ->
            while (cursor.moveToNext()) {
                val ingredient = Ingredient(
                    cursor.getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENT_UNITMEASUREID)),
                    cursor.getLong(cursor.getColumnIndex(_ID))
                )
                ingredients.add(ingredient)
            }
        }

        return ingredients
    }


    fun createUnitMeasure(unitMeasure: UnitMeasure) {

        val values = ContentValues()
        values.put(COLUMN_UNITMEASURE_NAME, unitMeasure.name)
        values.put(COLUMN_UNITMEASURE_SYMBOL, unitMeasure.symbol)

        writableDatabase.insert(TABLE_UNITMEASURE_NAME, null, values)

        Log.d(TAG, "Creation des mesures ")
    }


    fun createRecipe(recipe: Recipe) {

        val values = ContentValues()
        values.put(COLUMN_RECIPE_NAME, recipe.name)
        values.put(COLUMN_RECIPE_DESCRIPTION, recipe.description)
        values.put(COLUMN_RECIPE_RECIPEINGREDIENTID, recipe.recipeIngredientId)
        values.put(COLUMN_RECIPE_ID, recipe.id)

        writableDatabase.insert(TABLE_RECIPE_NAME, null, values)
    }


    fun createRecipeIngredient(RecipeIngredientList: MutableList<RecipeIngredient>) {

        for (RecipeIngredient in RecipeIngredientList) {

            val values = ContentValues()
            values.put(COLUMN_RECIPEINGREDIENT_RECIPEID, RecipeIngredient.recipeId)
            values.put(COLUMN_RECIPEINGREDIENT_INGREDIENTID, RecipeIngredient.ingredientId)
            values.put(COLUMN_RECIPEINGREDIENT_QUANTITY, RecipeIngredient.quantity)

            writableDatabase.insert(TABLE_RECIPEINGREDIENT_NAME, null, values)
        }
    }


    fun saveIngredientOfList(ingredient: Ingredient): Boolean {

        val values = ContentValues()
        values.put(COLUMN_INGREDIENTOFLIST_INGREDIENTID, ingredient.id)

        val succes = writableDatabase.insert(TABLE_INGREDIENTOFLIST_NAME, null, values)
        Log.d(TAG, "Try to save an ingredient")

        return succes > 0 // si false retourné, l'ingrédient n'a pas été ajouté
    }


    fun getAllIngredientOfList(): ArrayList<IngredientOfList> {

        val ingredientsOfList = ArrayList<IngredientOfList>()

        readableDatabase.rawQuery(INGREDIENTOFLIST_QUERY_TABLE, null).use { cursor ->

            // le rwaquery renvoie un cursor d'un ligne, tant qu'il peut passer à la colonne suivante, on récupère les données de la colonne sur laquelle il pointe
            while (cursor.moveToNext()) {
                val ingredientOfList = IngredientOfList(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENTOFLIST_INGREDIENTID)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENTOFLIST_QUANTITY)),
                )
                // on ajoute chaque donnée à un ingrédient qu'on ajoute lui-même à une liste que l'on va récupérer
                ingredientsOfList.add(ingredientOfList)
            }
        }
        return ingredientsOfList
    }


    fun saveQuantityOfIngredientOfList(ingredientOfList: IngredientOfList): Boolean {

        val values = ContentValues()
        values.put(COLUMN_INGREDIENTOFLIST_QUANTITY, ingredientOfList.quantity)

        val succes = writableDatabase.update(TABLE_INGREDIENTOFLIST_NAME, values, "ingredientid=" + ingredientOfList.ingredientId, null)
        Log.d(TAG, "Save a quantity to an IngredientOfList")

        return succes > 0
    }




    fun deleteIngredientOfList(ingredientOfListArrayList: ArrayList<IngredientOfList>) : Boolean {

        var succes = -1

        for (ingredientOfList in ingredientOfListArrayList) {

            // values est une intance de ContentValues() qui contient les valeurs à modifier dans la colonne passée en paramètre

            val values = ContentValues()
            values.put(COLUMN_RECIPEINGREDIENT_INGREDIENTID, ingredientOfList.ingredientId)

            // la variable succes (de type long) renvoie le résultat de l'action faite, la fonction retourne true si la valeur est supérieur à 0 (action réussie)
            // l'action delete sera exéctuée dans la table donnée en paramètre dans les colonnes où la clause est vraie (ici su l'ingredient id correspond à la valeur
            // du paramètre de la fonction

             succes = writableDatabase.delete(TABLE_INGREDIENTOFLIST_NAME, "ingredientid="+ ingredientOfList.ingredientId, null)
        }
        return succes > 0
    }


    fun getAllRecipe() : ArrayList<Recipe> {

        val allRecipe = ArrayList<Recipe>()

        readableDatabase.rawQuery(RECIPE_QUERY_TABLE, null).use { cursor ->
            while (cursor.moveToNext()) {
                val recipe = Recipe(
                cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_DESCRIPTION)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPE_RECIPEINGREDIENTID)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPE_ID))
                )
                allRecipe.add(recipe)
            }
        }
        return allRecipe
    }



    fun saveRecipeInRecipeWishList(recipe: Recipe) : Boolean {

        val values = ContentValues()
        values.put(COLUMN_RECIPEWISHLIST_RECIPEID, recipe.id)

       val id = writableDatabase.insert(TABLE_RECIPEWISHLIST_NAME, null, values)

        val valuesId = ContentValues()
        values.put(COLUMN_RECIPEWISHLIST_ID, id.toInt())

        val succes = writableDatabase.update(TABLE_RECIPEWISHLIST_NAME, values, "$_ID=" +id, null)

        return succes > 0
    }



    fun getRecipeById(recipeOfWishList: RecipeWishList) : Recipe {

        val selectQuery = "SELECT * FROM $TABLE_RECIPE_NAME WHERE $COLUMN_RECIPE_ID = ${recipeOfWishList.recipeId}"

        var recipe = Recipe("","", 0, 0)

        readableDatabase.rawQuery(selectQuery, null).use { cursor ->
            while (cursor.moveToNext()) {
                val recipesOfWishList = Recipe(
                    cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_DESCRIPTION)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPE_RECIPEINGREDIENTID)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPE_ID))
                )
                recipe = recipesOfWishList
            }
        }
        return recipe
    }


    fun getRecipeIngredientListByRecipeId(recipeId: Recipe) : ArrayList<RecipeIngredient> {

        val selectQuery = "SELECT * FROM $TABLE_RECIPEINGREDIENT_NAME WHERE $COLUMN_RECIPEINGREDIENT_RECIPEID = ${recipeId.recipeIngredientId}"

        val recipeIngredientList = ArrayList<RecipeIngredient>()

        readableDatabase.rawQuery(selectQuery, null).use { cursor ->
            while (cursor.moveToNext()) {
                val recipeIngredient = RecipeIngredient(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPEINGREDIENT_RECIPEID)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENTOFLIST_INGREDIENTID)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPEINGREDIENT_QUANTITY))
                )
                recipeIngredientList.add(recipeIngredient)
            }
        }
        return recipeIngredientList
    }


    fun getIngredientByRecipeIngredientIdList(recipeIngredient: ArrayList<RecipeIngredient>): ArrayList<Ingredient> {

        val ingredientsList = ArrayList<Ingredient>()
        recipeIngredient.forEach {

            val selectQuery =
                "SELECT * FROM $TABLE_INGREDIENT_NAME WHERE $_ID = ${it.ingredientId}"

            readableDatabase.rawQuery(selectQuery, null).use { cursor ->
                while (cursor.moveToNext()) {
                    val ingredient = Ingredient(
                        cursor.getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENT_UNITMEASUREID)),
                        cursor.getLong(cursor.getColumnIndex(_ID))
                    )
                    ingredientsList.add(ingredient)
                }
            }
        }
        return ingredientsList
    }


    fun getAllRecipeWishList() : ArrayList<RecipeWishList> {

        val recipeWishList = ArrayList<RecipeWishList>()

        readableDatabase.rawQuery(RECIPEWISHLIST_QUERY_TABLE, null).use { cursor ->
            while (cursor.moveToNext()) {
                val recipeWish = RecipeWishList(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPEWISHLIST_RECIPEID)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPEWISHLIST_NUMBEROFPERSON)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPEWISHLIST_ID))
                )
                recipeWishList.add(recipeWish)
            }
        }
        return recipeWishList
    }


    fun deleteRecipeInWiwhlist(checkedRecipeList: ArrayList<RecipeWishList>) : Boolean {

        var succes = -1

        for (checkedRecipe in checkedRecipeList) {

            val values = ContentValues()
            values.put(COLUMN_RECIPEINGREDIENT_INGREDIENTID, checkedRecipe.id)

            succes = writableDatabase.delete(TABLE_RECIPEWISHLIST_NAME, "$COLUMN_RECIPEWISHLIST_ID=" + checkedRecipe.id, null)
        }
        return succes > 0
    }








    fun getRecipeIngredientListByRecipeIdList(recipeIdList : ArrayList<RecipeWishList>) : ArrayList<RecipeIngredient> {

        val recipeIngredientList = ArrayList<RecipeIngredient>()

        recipeIdList.forEach {

            val selectQuery =
                "SELECT * FROM $TABLE_RECIPEINGREDIENT_NAME WHERE $COLUMN_RECIPEINGREDIENT_RECIPEID = ${it.recipeId}"

            readableDatabase.rawQuery(selectQuery, null).use { cursor ->
                while (cursor.moveToNext()) {
                    val recipeIngredient = RecipeIngredient(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPEINGREDIENT_RECIPEID)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENTOFLIST_INGREDIENTID)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPEINGREDIENT_QUANTITY))
                    )
                    recipeIngredientList.add(recipeIngredient)
                }
            }
        }
        return recipeIngredientList
    }





    fun getAllIngredientListByIngredientId(ingredientOfListArrayList: ArrayList<IngredientOfList> = getAllIngredientOfList())
            : ArrayList<Ingredient> {

        val ingredientListArray = ArrayList<Ingredient>()

        ingredientOfListArrayList.forEach {

            val selectQuery =
                "SELECT * FROM $TABLE_INGREDIENT_NAME WHERE $_ID = ${it.ingredientId}"

            readableDatabase.rawQuery(selectQuery, null).use { cursor ->
                while (cursor.moveToNext()) {
                    val ingredient = Ingredient(
                        cursor.getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENT_UNITMEASUREID)),
                        cursor.getLong(cursor.getColumnIndex(_ID))
                    )
                    ingredientListArray.add(ingredient)
                }
            }
        }
        return ingredientListArray
    }


    /*
    fun getShopListArray(recipeOfWishListArrayList: ArrayList<RecipeWishList>
                         = getAllRecipeWishList()
                         , ingredientOfListArrayList: ArrayList<IngredientOfList> = getAllIngredientOfList())
    : List<Ingredient> {


        val allIngredientOfListAlreadyUserHas = getAllIngredientListByIngredientId()


        val allIngredientOfListUserNeed  = getIngredientByRecipeIngredientIdList(getRecipeIngredientListByRecipeIdList(getAllRecipeWishList()))


        val getShopListArray = allIngredientOfListUserNeed - allIngredientOfListAlreadyUserHas


        return getShopListArray
    }

     */


    @RequiresApi(Build.VERSION_CODES.N)
    fun getIngredientOfListUserNeed(recipeIngredientArrayList: ArrayList<RecipeIngredient> = getRecipeIngredientListByRecipeIdList(getAllRecipeWishList()))
            : ArrayList<IngredientOfList> {

        val ingredientOfListUserNeed = ArrayList<IngredientOfList>()
        val toRemove = ArrayList<IngredientOfList>()

        recipeIngredientArrayList.forEach {
            val ingredientOfList = IngredientOfList(
                it.ingredientId,
                it.quantity
            )

            ingredientOfListUserNeed.add(ingredientOfList)
        }


        for (unique in 0..ingredientOfListUserNeed.size -1) {

            for (isUnique in 0..ingredientOfListUserNeed.size -1) {

                if (unique != isUnique) {

                    if (ingredientOfListUserNeed[unique].ingredientId == ingredientOfListUserNeed[isUnique].ingredientId) {

                        ingredientOfListUserNeed[unique].quantity = ingredientOfListUserNeed[unique].quantity?.plus(ingredientOfListUserNeed[isUnique].quantity!!)

                        ingredientOfListUserNeed[isUnique].quantity = 0
                    }
                }
            }
        }

        ingredientOfListUserNeed.removeIf { it -> it.quantity == 0 }

        ingredientOfListUserNeed.forEach { it -> Log.i("IngredientNeedId", "${it.ingredientId}, ${it.quantity}") }

        return ingredientOfListUserNeed
    }




    @RequiresApi(Build.VERSION_CODES.N)
    fun getShopListArray() : ArrayList<IngredientOfList> {

        val shopList = ArrayList<IngredientOfList>()

        val allIngredientOfListAlreadyUserHas = getAllIngredientOfList()

        val allIngredientOfListUserNeed = getIngredientOfListUserNeed()


        for (need in 0..allIngredientOfListUserNeed.size -1) {

            for (has in 0..allIngredientOfListAlreadyUserHas.size -1) {

                if (allIngredientOfListUserNeed[need].ingredientId == allIngredientOfListAlreadyUserHas[has].ingredientId) {


                    val result =
                        allIngredientOfListUserNeed[need].quantity?.minus(
                            allIngredientOfListAlreadyUserHas[has].quantity!!
                        )

                    if (result!! <= 0 ) {

                        allIngredientOfListUserNeed[need].quantity = 0

                    } else {
                        allIngredientOfListUserNeed[need].quantity = result
                    }
                }
            }

        }

        allIngredientOfListUserNeed.removeIf { it -> it.quantity == 0 }

        return allIngredientOfListUserNeed
    }

}


