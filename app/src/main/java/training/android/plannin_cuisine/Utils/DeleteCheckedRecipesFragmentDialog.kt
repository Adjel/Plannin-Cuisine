package training.android.plannin_cuisine.Utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.RecipeWishList
import training.android.plannin_cuisine.R

class DeleteCheckedRecipesFragmentDialog(val checkedRecipeWishListToDelete: ArrayList<RecipeWishList>, val toastContext: Context) :
    DialogFragment() {

    private var activityListener: OnFragmentResultListener? = null

    interface OnFragmentResultListener {
        fun notifyDataChanged(updateAll: Boolean = false, updateSelected:  Boolean = false) {

        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.MyDialogTheme)
            val inflater = requireActivity().layoutInflater;

            builder.setView(inflater.inflate(R.layout.delete_recipeinwishlist_dialog_fragment, null))

                .setPositiveButton(
                    "Do you really want to delete all recipes in your recipe list?", DialogInterface.OnClickListener { _, _ ->

                        if (database.deleteRecipeInWiwhlist(database.getAllRecipeWishList()) && activityListener != null) {

                            val updateAll = true
                            val updateSelected = false

                            activityListener!!.notifyDataChanged(updateAll, updateSelected)
                        }
                    })

                .setNeutralButton("Cancel", DialogInterface.OnClickListener { _, _ ->
                    dialog?.cancel()

                })

                .setNegativeButton("Do you want to deleted only selected recipes ?", DialogInterface.OnClickListener { _, _ ->
                    if (database.deleteRecipeInWiwhlist(checkedRecipeWishListToDelete) && activityListener != null) {

                        val updateSelected = true
                        val updateAll = false

                        activityListener!!.notifyDataChanged(updateAll, updateSelected)
                    }
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnFragmentResultListener) {
            activityListener = context
        }else {
            throw RuntimeException("$context must implement OnFragmentResultListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        activityListener = null
    }
}