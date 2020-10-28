package training.android.plannin_cuisine.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.Ingredient
import training.android.plannin_cuisine.Entities.IngredientOfList
import training.android.plannin_cuisine.R

class ShopListAdapter(val shopListArray: List<IngredientOfList>): RecyclerView.Adapter<ShopListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardView = itemView.findViewById<CardView>(R.id.shoplist_item)
        val name = itemView.findViewById<TextView>(R.id.shoplist_item_name)
        val quantity = itemView.findViewById<TextView>(R.id.shoplist_item_quantity)
        val unitMeasure = itemView.findViewById<TextView>(R.id.shoplist_item_unitmeasure)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.shoplist_cardview_list_item, parent, false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopListItem = shopListArray[position]

        holder?.cardView.tag = position
        holder?.name.text = database.readIngredientById(shopListItem).name
        holder.quantity.text = shopListItem.quantity.toString()
        holder.unitMeasure.text = database.readUnitMeasureById(database.readIngredientById(shopListItem)).symbol

    }

    override fun getItemCount(): Int {
        return shopListArray.size
    }
}