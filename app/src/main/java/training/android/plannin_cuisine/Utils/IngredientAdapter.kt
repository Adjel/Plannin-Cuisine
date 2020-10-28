package training.android.plannin_cuisine.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import training.android.plannin_cuisine.Entities.Ingredient
import training.android.plannin_cuisine.R

class IngredientAdapter(val ingredientArray: ArrayList<Ingredient>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.ingredient_cardview_name)
        val name = itemView.findViewById<TextView>(R.id.ingredient_searchable_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.ingredient_searchable_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredientArray[position]

        holder.name.text = ingredient.name
        holder.cardView.tag = position
        holder.cardView.setOnClickListener(itemClickListener)
    }

    override fun getItemCount(): Int {
        return ingredientArray.size
    }
}