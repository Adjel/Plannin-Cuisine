package training.android.plannin_cuisine.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import training.android.plannin_cuisine.Entities.Recipe
import training.android.plannin_cuisine.R

class RecipeAdapter(val recipeList: ArrayList<Recipe>, val itemClickListener: View.OnClickListener, val itemLongClickListener: View.OnLongClickListener)
    : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val cardview = itemView.findViewById<CardView>(R.id.recipie_cardview_searchable)
        val name = itemView.findViewById<TextView>(R.id.recipe_name_searchable)
        val description = itemView.findViewById<TextView>(R.id.recipe_description_searchable)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.recipe_searchable_item, parent, false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]

        holder.cardview?.tag = position
        holder.name?.text = recipe.name
        holder.description?.text = recipe.description

        holder.cardview.setOnLongClickListener(itemLongClickListener)
        holder.cardview.setOnClickListener(itemClickListener)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}