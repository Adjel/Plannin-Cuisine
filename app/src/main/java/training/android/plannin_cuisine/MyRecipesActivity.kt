package training.android.plannin_cuisine

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipes_wishlist_recyclerview.*
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.RecipeWishList
import training.android.plannin_cuisine.Utils.DeleteCheckedRecipesFragmentDialog
import training.android.plannin_cuisine.Utils.RecipesWishListAdapter

class MyRecipesActivity: AppCompatActivity(), View.OnLongClickListener, DeleteCheckedRecipesFragmentDialog.OnFragmentResultListener {

    private val UPDATE_RECIPELIST_REQUEST_CODE = 1

    val recipesWishList: ArrayList<RecipeWishList> = database.getAllRecipeWishList()
    val adapter = RecipesWishListAdapter(recipesWishList, this)
    val checkedRecipeWishListToDelete = adapter.getRecipeListCheckedToDelete()
    val deleteCheckedRecipes = DeleteCheckedRecipesFragmentDialog(checkedRecipeWishListToDelete, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_wishlist_recyclerview)

        findViewById<Button>(R.id.recipe_search_activity_button).setOnClickListener {

            val intent = Intent(this, RecipeSearchableActivity::class.java)
            startActivityForResult(intent, UPDATE_RECIPELIST_REQUEST_CODE)

        }


        findViewById<ImageButton>(R.id.delete_recipes).setOnClickListener {
            deleteCheckedRecipes.show(supportFragmentManager, "RECIPESACTIVIVTY")
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recipes_wishlist_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == UPDATE_RECIPELIST_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                adapter.updateRecipeWishList()
            }
        }
    }

    override fun onLongClick(view: View?): Boolean {
        if (view != null) {
            val index = view.tag as Int

            val intent = Intent(this, RecipeDetail::class.java)
            intent.putExtra("recipeDetail", database.getRecipeById(recipesWishList[index]))
            startActivity(intent)
        }
        return true
    }


    override fun notifyDataChanged(updateAll : Boolean, updateSelected : Boolean) {

        if (updateAll && !updateSelected) {
            adapter.updateRecipeWishList()
            Toast.makeText(this, "Recipe list cleaned", Toast.LENGTH_SHORT).show()
        }

        if (updateSelected && !updateAll) {
            adapter.updateRecipeWishList()
            Toast.makeText(this, "Selected recipe(s) deleted", Toast.LENGTH_SHORT).show()
            adapter.checkedRecipeWishArrayList.clear()
        }
    }

}