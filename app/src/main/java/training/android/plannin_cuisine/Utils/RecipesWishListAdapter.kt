package training.android.plannin_cuisine.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.RecipeWishList
import training.android.plannin_cuisine.R

class RecipesWishListAdapter(val recipeWishArrayList: ArrayList<RecipeWishList>, val itemLongClickListener: View.OnLongClickListener): RecyclerView.Adapter<RecipesWishListAdapter.ViewHolder>() {

    val checkedRecipeWishArrayList = ArrayList<RecipeWishList>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardView = itemView.findViewById<CardView>(R.id.recipie_cardview_wishlist)
        val name = itemView.findViewById<TextView>(R.id.recipe_name_wishlist)
        val description = itemView.findViewById<TextView>(R.id.recipe_description_wishlist)
        val checkBox = itemView.findViewById<CheckBox>(R.id.recipe_wishlist_checkbox)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.recipe_wishlist_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeOfWishList = recipeWishArrayList[position]

        holder.cardView?.tag = position
        holder.name?.text = database.getRecipeById(recipeOfWishList).name
        holder.description?.text = database.getRecipeById(recipeOfWishList).description
        holder.checkBox.isChecked = false

        holder.cardView.setOnLongClickListener(itemLongClickListener)

        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                checkedRecipeWishArrayList.add(recipeOfWishList)
            } else {
                checkedRecipeWishArrayList.remove(recipeOfWishList)
            }
        }

    }

    fun updateRecipeWishList(recipeWishArrayListDisplay: ArrayList<RecipeWishList> = database.getAllRecipeWishList()) {

        recipeWishArrayList.clear()
        recipeWishArrayList.addAll(recipeWishArrayListDisplay)
        notifyDataSetChanged()
    }

    fun getRecipeListCheckedToDelete() : ArrayList<RecipeWishList> {
        return checkedRecipeWishArrayList
    }

    override fun getItemCount(): Int {
        return recipeWishArrayList.size
    }


}