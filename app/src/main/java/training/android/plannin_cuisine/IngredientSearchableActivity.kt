package training.android.plannin_cuisine

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ingredient_searchable_recyclerview.*
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.Ingredient
import training.android.plannin_cuisine.Utils.IngredientAdapter
import training.android.plannin_cuisine.Utils.SaveIngredientInListDialogFragment
import java.util.*
import kotlin.collections.ArrayList

class IngredientSearchableActivity : AppCompatActivity(), View.OnClickListener {

    val ingredientArray = database.getAllIngredient()
    val ingredientStringArray = ArrayList<String>()
    val displayList = ArrayList<Ingredient>()
    val adapter = IngredientAdapter(displayList, this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingredient_searchable_recyclerview)

        for (ingredient in ingredientArray) {
            ingredientStringArray.add(ingredient.name)
        }
        displayList.addAll(ingredientArray)

        val recyclerView = findViewById<RecyclerView>(R.id.ingredient_searchable_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }


    // intanciation du search menu

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // on inlfate la vue du menu (affiche)

        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu?.findItem(R.id.search_bar)

        if (menuItem != null) {

            // si on clique sur le menu(?), on retourne l'action vue en searchview

            val searchView = menuItem.actionView as SearchView

            // on déclare une action à faire sur le texte que l'utilisateur va tapper dans la barre auparavant inflatée

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    // return true indique que l'élément est consommé (?)

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    // si l'utilisateur tape du texte alors:

                    if (newText!!.isNotEmpty()) {

                        // on vide la liste

                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault()) // on transforme le texte de manière à pouvoir l'utiliser ensuite sans avoir d'erreur
                        ingredientArray.forEach {

                            // pour chaque item de l'array, si le name de l'item contient search, on ajoute cet item à la liste affichée

                            if (it.name.toLowerCase(Locale.getDefault()).contains(search)) {
                                displayList.add(it)
                            }
                        }

                        // on notifie la vue pour afficher les nouvelles données

                        ingredient_searchable_recyclerview.adapter!!.notifyDataSetChanged()
                    }
                    else {

                        // si aucun élément de la liste ne contient les lettres on met à jour la liste et notifie la vue qui affiche la liste

                        displayList.clear()
                        displayList.addAll(ingredientArray)
                        ingredient_searchable_recyclerview.adapter!!.notifyDataSetChanged()
                    }

                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    // action à faire lorsque l'on clique sur un objet

    override fun onClick(view: View) {
        if (view.tag != null) {

            // on récupère la position de l'item cliqué

            val index = view.tag as Int
            val ingredient = displayList[index]


            /** On  crée une instance de la class SIILDialog fragment en lui passant en paramètre l'item (ici ingrédient) cliqué **/

            val saveIngrdientDialog = SaveIngredientInListDialogFragment(ingredient, this)
            saveIngrdientDialog.show(supportFragmentManager, "SaveIngredientDialog")

            /** On renvoie le résultat (puisque true) attendu (true ou false) par celui qui envoie l'intent qui demande un résultat (celui qui écoute: activity for result)  **/

            setResult(Activity.RESULT_OK)

            }
        }
    }
