package training.android.plannin_cuisine

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_searchable_recyclerview.*
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.Recipe
import training.android.plannin_cuisine.Utils.RecipeAdapter
import training.android.plannin_cuisine.Utils.SaveRecipeInListDialogFragment
import java.util.*
import kotlin.collections.ArrayList


class RecipeSearchableActivity: AppCompatActivity(), View.OnLongClickListener, View.OnClickListener {

    val recipeList: ArrayList<Recipe> = database.getAllRecipe()
    val recipeListDisplay = ArrayList<Recipe>()
    val adapter = RecipeAdapter(recipeListDisplay, this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_searchable_recyclerview)

        recipeListDisplay.addAll(recipeList)


        val recyclerview = findViewById<RecyclerView>(R.id.recipe_searchable_recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu?.findItem(R.id.search_bar)

        if (menuItem != null) {

            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()) {

                    recipeListDisplay.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        recipeList.forEach {

                            if (it.name.toLowerCase(Locale.getDefault()).contains(search)) {
                                recipeListDisplay.add(it)
                            }
                        }

                        recipe_searchable_recyclerview.adapter!!.notifyDataSetChanged()

                    } else {

                    recipeListDisplay.clear()
                    recipeListDisplay.addAll(recipeList)
                    recipe_searchable_recyclerview.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })
    }
    return super.onCreateOptionsMenu(menu)
}


    override fun onLongClick(view: View?): Boolean {
        if (view != null) {
            val index = view.tag as Int

            val intent = Intent(this, RecipeDetail::class.java)
            intent.putExtra("recipeDetail", recipeListDisplay[index])
            startActivity(intent)
        }
        return true
    }


    override fun onClick(view: View?) {
        if (view != null) {
            val index = view.tag as Int

            val saveRecipe = SaveRecipeInListDialogFragment(recipeListDisplay[index], this)
            saveRecipe.show(supportFragmentManager, "SaveRecipeDialog")

            setResult(Activity.RESULT_OK)
        }
    }

}