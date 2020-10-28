package training.android.plannin_cuisine.Utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.ingredient_list_recyclerview.*
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.IngredientOfList
import training.android.plannin_cuisine.MyIngredientListActivity
import training.android.plannin_cuisine.R
import java.lang.IllegalStateException

class DeteleIngredientOfListDialogFragment(val deleteIngredientCheckedArray: ArrayList<IngredientOfList>, val toastContext: Context) : DialogFragment() {

    /** on instance notre lister à null, le listener est l'appelant (ici l'activity qui demande un résultat**/

    private var mListener: OnFragmentInteractionListener? = null


    /** on déclare une interface qui sera implémentée par l'appelant. L'interafce appelante appellera donc celle que l'on déclare ici (le callback) dont
     * la fonction sera donc exécutée après l'action que l'on veut d'abord faire ici. L'interface pourra aussi renvoyer une donnée (uri) **/

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(allDeleted: Boolean = false, selectedDeleted: Boolean = false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val deletAllIngredientOfList = database.getAllIngredientOfList()


        /** on va récupérer la vu pour s'y attacher (fragment) puis inflater sur la vue de l'activity **/

        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.MyDialogTheme)
            val inflater = requireActivity().layoutInflater;


            builder.setView(
                inflater.inflate(
                    R.layout.delete_ingredientoflist_dialog_fragment,
                    null
                )
            )

                .setPositiveButton("Delete all ingredients") { dialog, which ->

                    if (database.deleteIngredientOfList(deletAllIngredientOfList) && mListener != null) {

                        val allDeleted: Boolean = true
                        val selectedDeleted: Boolean = false

                        /** Si l'appelant n'est pas null (auquel cas c'est une erreur), on exécute al fonction implémentée par l'interface (listener) **/

                        mListener!!.onFragmentInteraction(allDeleted, selectedDeleted)
                    }
                }

                .setNeutralButton("No and cancel", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

                .setNegativeButton((R.string.delete_selected_ingredientoflist_dialogue_message), DialogInterface.OnClickListener { dialog, id ->

                    /**Si la liste passée en paramètre est vide, on ferme la dialogue et notifie l'utilisateur que la liste est vide **/

                    if (deleteIngredientCheckedArray.isEmpty()) {
                        this.dismiss()
                        Toast.makeText(toastContext, "You have to select items before", Toast.LENGTH_LONG).show()
                    }

                    if (database.deleteIngredientOfList(deleteIngredientCheckedArray) && mListener != null) {

                        val allDeleted: Boolean = false
                        val selectedDeleted: Boolean = true

                        mListener!!.onFragmentInteraction(allDeleted, selectedDeleted)

                        Log.i("DELETE", "delete selected")
                    }
                })


            return builder.create()
        } ?: throw IllegalStateException("Acitvity cannot be null")
    }


    /** si l'appelant (l'interface) est bien le context dans lequel le fragment est crée, alors l'instance de l'intefarce à bien le même context
     * si ce n'est pas le cas, on envoie une erreur (l'appelant n'est pas celui qui à instancié la dialog)**/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }


    // on évite la fuite mémoire (?)...

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

}