package training.android.plannin_cuisine.Utils


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import training.android.plannin_cuisine.Database.App
import training.android.plannin_cuisine.Entities.Recipe
import training.android.plannin_cuisine.R
import java.lang.IllegalStateException

class SaveRecipeInListDialogFragment(val recipe: Recipe, val onClickContext: Context): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {

            val builder = AlertDialog.Builder(onClickContext, R.style.MyDialogTheme)
            val inflater = requireActivity().layoutInflater;

            builder.setView(inflater.inflate(R.layout.save_recipe_in_list_dialog, null))
                .setPositiveButton(getString(R.string.positive_dialog_button)) { dialog, which ->

                    if (App.database.saveRecipeInRecipeWishList(recipe)) {
                        Toast.makeText(onClickContext, "Recipe added in your ingredient list", Toast.LENGTH_SHORT).show()
                    }
                }

                .setNegativeButton("No and cancel", DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(onClickContext, "Recipe not added", Toast.LENGTH_SHORT).show()
                    dialog.cancel()
                })

            return builder.create()
        } ?: throw IllegalStateException("Acitvity cannot be null")
    }
}