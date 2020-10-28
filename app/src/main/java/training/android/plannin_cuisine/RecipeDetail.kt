package training.android.plannin_cuisine

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.Ingredient
import training.android.plannin_cuisine.Entities.Recipe
import training.android.plannin_cuisine.Entities.RecipeIngredient
import training.android.plannin_cuisine.Utils.SaveRecipeInListDialogFragment

class RecipeDetail: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_detail)

        val recipe: Recipe = intent.getParcelableExtra<Recipe>("recipeDetail")!!
        val recipeIngredientList: ArrayList<RecipeIngredient> = database.getRecipeIngredientListByRecipeId(recipe)
        val ingredientName = database.getIngredientByRecipeIngredientIdList(recipeIngredientList)


        val title = findViewById<TextView>(R.id.recipe_detail_title)
        val recipeDetailIngredientList = findViewById<TextView>(R.id.recipe_detail_ingredientList)
        val recipeDetailDescription = findViewById<TextView>(R.id.recipe_detail_description)

        findViewById<ImageButton>(R.id.add_recipe_detail).setOnClickListener() {
            val saveRecipeDialog = SaveRecipeInListDialogFragment(recipe, this)
            saveRecipeDialog.show(supportFragmentManager, "RECIPEDETAIL")
        }



        title.text = recipe.name
        recipeDetailIngredientList.text = ingredientName.joinToString { ingredient: Ingredient -> ingredient.name }
        recipeDetailDescription.text = recipe.description

    }

}
