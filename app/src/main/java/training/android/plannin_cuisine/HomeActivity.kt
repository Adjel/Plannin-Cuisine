package training.android.plannin_cuisine


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Database.AppPreferences
import training.android.plannin_cuisine.Entities.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /** on vérifie que l'application à déjà été lancé, puis on exécute des tâches si true est renvoyé  */

        if (!AppPreferences.firstRun) {

            creatAppIngredientList()
            creatAppUnitMeasure()
            createAppRecipies()
            database.createRecipeIngredient(RecipeIngredientList)

            AppPreferences.firstRun = true
            Log.d("SpinKotlin", "The value of our pref is: ${AppPreferences.firstRun}")
        }



        findViewById<Button>(R.id.my_ingredients_button).setOnClickListener {

            val intent = Intent(this, MyIngredientListActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.my_recipies_button).setOnClickListener {

            val intent = Intent(this, MyRecipesActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.my_shop_list_button).setOnClickListener {

            val intent = Intent(this, MyShopListActivity::class.java)
            startActivity(intent)
        }
    }
}