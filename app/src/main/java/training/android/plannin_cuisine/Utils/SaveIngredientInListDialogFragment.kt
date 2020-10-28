package training.android.plannin_cuisine.Utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.Ingredient
import training.android.plannin_cuisine.Entities.IngredientOfList
import training.android.plannin_cuisine.R
import java.lang.IllegalStateException

class SaveIngredientInListDialogFragment(val ingredient: Ingredient, val onClickContext: Context) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        /** on va récupérer la vu pour s'y attacher (fragment) puis inflater sur la vue de l'activity **/

        return activity?.let {
            val builder = AlertDialog.Builder(onClickContext, R.style.MyDialogTheme)
            val inflater = requireActivity().layoutInflater;


            builder.setView(inflater.inflate(R.layout.save_ingredient_in_list_dialog, null))
                .setPositiveButton(getString(R.string.positive_dialog_button)) { dialog, which ->
                    Log.i("SaveIngredient", "Appel de ma fonction de sauvegarde")

                    // la fonction (qui sauvegarde un ingrédient) renvoie un Booléen, donc on peut effectuer une action en fonction du résultat
                    if (database.saveIngredientOfList(ingredient)) {
                        Toast.makeText(onClickContext, "Ingredient added in your ingredient list", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(onClickContext, "This ingredient is already in your ingredient list", Toast.LENGTH_SHORT).show()
                    }

                }

                .setNegativeButton("No and cancel", DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(onClickContext, "Ingredient not added", Toast.LENGTH_SHORT).show()
                    dialog.cancel()
                })

            return builder.create()
        } ?: throw IllegalStateException("Acitvity cannot be null")
    }


}