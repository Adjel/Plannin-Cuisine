package training.android.plannin_cuisine

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Utils.DeteleIngredientOfListDialogFragment
import training.android.plannin_cuisine.Utils.IngredientOfListAdapter

class MyIngredientListActivity : AppCompatActivity(), DeteleIngredientOfListDialogFragment.OnFragmentInteractionListener {

    // on intancie une variable (clé) qui va permettre de récupérer un résultat d'une autre activity (request) en passant cette clé en paramètre (REQUEST CODE)

    private val UPDATE_INGREDIENTOFLIST_REQUEST_CODE = 0

    val ingredientOfListArray = database.getAllIngredientOfList()
    val adapter = IngredientOfListAdapter(ingredientOfListArray, this)



    // Array qui permettra de récupérer la liste d'ingrédient cochés

    val deleteCheckedIngredientArray = adapter.getIngredientOfListArrayToDelete()


    // on crée l'instance de notre dialog de suppression des items cochés qui sera lancée lorsque le bouton sera cliqué

    val deleteIngrdientOfListDialog = DeteleIngredientOfListDialogFragment(deleteCheckedIngredientArray, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingredient_list_recyclerview)


        /** on cache l'action bar de cette activity, et de cette manière là, le theme reste le même (donc on supporte bien les checkboxs)*/

        supportActionBar?.hide()


        findViewById<Button>(R.id.ingredient_search_activity_search_button).setOnClickListener {
            val intent = Intent(this, IngredientSearchableActivity::class.java)
            startActivityForResult(intent, UPDATE_INGREDIENTOFLIST_REQUEST_CODE)
        }

        // on instancie est on lit le clique du button delete_ingredient
        findViewById<ImageButton>(R.id.delete_ingredient).setOnClickListener {
            deleteIngrdientOfListDialog.show(supportFragmentManager, "SaveIngredientDialog")
        }


        val recyclerView = findViewById<RecyclerView>(R.id.ingredientoflist_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }


    /**on implémente et on surchage la fonction qui va permettre d'instancier une activity et d'en attendre et récupérer un résultat**/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // si le resquest code et le resultcode corresponde

        if (requestCode == UPDATE_INGREDIENTOFLIST_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                //  on met à jour l'adapeur

                adapter.updateIngredientList()
            }
        }
    }

    override fun onFragmentInteraction(allDeleted: Boolean, selectedDeleted: Boolean) {

        if (allDeleted && !selectedDeleted) {
            adapter.updateIngredientList()
            Toast.makeText(this, "Ingredients list cleaned", Toast.LENGTH_SHORT).show()
        }

        if (selectedDeleted && !allDeleted) {

            adapter.updateIngredientList()
            adapter.deleteCheckedIngredientOfListArray.clear()
            Log.i("INGREDIENTDELETE", "Result Received")
            Toast.makeText(this, "Selected ingredients deleted", Toast.LENGTH_SHORT).show()

        }
    }


}

