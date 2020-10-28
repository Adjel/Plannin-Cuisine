package training.android.plannin_cuisine.Utils


import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.IngredientOfList
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ingredient_list_item.view.*
import training.android.plannin_cuisine.R

class IngredientOfListAdapter(
    val ingredientOfListDisplay: ArrayList<IngredientOfList>, val toastContext: Context
) : RecyclerView.Adapter<IngredientOfListAdapter.ViewHolder>() {


    /** On déclare une liste qui à pour clé à chacune de nos valeurs des booléens
    var checkedIngredientOfList = SparseBooleanArray()
    **/




    /** On délcare une liste qui contiendra les items que l'utilisateur à coché et qu'il voudra supprimer **/

    var deleteCheckedIngredientOfListArray = ArrayList<IngredientOfList>()


    /** On déclare la classe qui créera et gardera nos vues**/

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardView = itemView.findViewById<CardView>(R.id.ingredientoflist_cardview_name)
        val name = itemView.findViewById<TextView>(R.id.ingredientoflist_name)
        var checkbox = itemView.ingredientoflist_checkbox
        var quantity = itemView.findViewById<EditText>(R.id.ingredientoflist_quantity)
        val unitmeasure = itemView.findViewById<TextView>(R.id.ingredientoflist_unitmeasure)


        /**
        init {
            //called after the constructor


            checkbox.setOnClickListener {

                if (!checkedIngredientOfList.get(adapterPosition, false)) {
                    //checkox checked
                    checkbox.isChecked = true
                    //stores checkbox states and position
                    checkedIngredientOfList.put(adapterPosition, true)
                } else {
                    //checkbox uncheked
                    checkbox.isChecked = false
                    //stores checkbox states and position
                    checkedIngredientOfList.put(adapterPosition, false)
                }
            }

        }
        **/

    }


    /** on instancie grâce à cette fonction le holder dans lequelle notre vue sera inflatée**/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.ingredient_list_item, parent, false)

        return ViewHolder(viewItem)
    }


    /** On garde la vue dans notre holder**/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ingredientOfList =
            ingredientOfListDisplay[position]        //on récupère la position sur laquelle notre holder va se binder


        holder.name?.text = database.readIngredientById(ingredientOfList).name
        holder.unitmeasure?.text =
            database.readUnitMeasureById(database.readIngredientById(ingredientOfList)).symbol


        // si la quantité de l'ingrédient n'a jamais été entrée on affiche 0, sinon on affiche la quantité de l'ingrédient testé

        if (ingredientOfList.quantity != null) {
            holder.quantity.setText(ingredientOfList.quantity.toString())
        } else {
            holder.quantity.setText(R.string.quantity_is_null)
        }


        /** on passe en true le booléen qui définit si l'utilisateur peut accéeder aux EditText de notre recyclerView**/

        holder.quantity.isFocusableInTouchMode = true


        /** on définit du code à exécuter après les différentes interactions de l'utilisateur**/

        holder.quantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(text: Editable?) {

                if (text?.length!! > 0) {
                    ingredientOfList.quantity = text.toString().toInt()
                    database.saveQuantityOfIngredientOfList(ingredientOfList)
                }
            }


        })

        /** on définit un code à exécuter lorsque l'utilisateur n'interagit plus avec l'editText passé en paramètre */



        holder.quantity.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                Toast.makeText(toastContext, "Ingredient saved", Toast.LENGTH_SHORT).show()
            }
        }


        /** on vérifie que notre checkbox à été cochée ou non **/


        /**
        if (!checkedIngredientOfList.get(position, false)) {
        holder.checkbox.isChecked = false
        }
        else {
        holder.checkbox.isChecked = true
        }
        **/


        /** On délcare les actions à faire lors des interactions de l'utilisateur avec une checkbox  **/

        holder.checkbox.isChecked = false

        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                deleteCheckedIngredientOfListArray.add(ingredientOfList)

            } else {
                deleteCheckedIngredientOfListArray.remove(ingredientOfList)
            }
        }
    }


    /** on déclare une fonction qui permettra à l'appelant de récupérer les items à supprimer **/

    fun getIngredientOfListArrayToDelete(): ArrayList<IngredientOfList> {
        return deleteCheckedIngredientOfListArray
    }


    /** On déclare une fonction qui permet de mettre les données à jour**/

    fun updateItemListChecked(deleteIngredientOfListArray: ArrayList<IngredientOfList>) {
        deleteIngredientOfListArray.clear()
        this.deleteCheckedIngredientOfListArray = deleteIngredientOfListArray
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return ingredientOfListDisplay.size
    }


    /** lorsque qu'une donnée est ajoutée ou soustraite à notre liste, on remet cette dernière à jour et on notifie l'appelant (qui affichera les changements ect **/

    fun updateIngredientList(ingredientOfListArray: ArrayList<IngredientOfList> = database.getAllIngredientOfList()) {

        ingredientOfListDisplay.clear()
        ingredientOfListDisplay.addAll(ingredientOfListArray)
        notifyDataSetChanged()
    }

}
