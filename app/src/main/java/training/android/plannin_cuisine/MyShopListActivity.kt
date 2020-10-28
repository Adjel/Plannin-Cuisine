package training.android.plannin_cuisine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import training.android.plannin_cuisine.Database.App.Companion.database
import training.android.plannin_cuisine.Entities.Ingredient
import training.android.plannin_cuisine.Entities.IngredientOfList
import training.android.plannin_cuisine.Utils.ShopListAdapter

class MyShopListActivity: AppCompatActivity() {

    val shopListArray: List<IngredientOfList> = database.getShopListArray()
    val adapter = ShopListAdapter(shopListArray)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_list_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.shoplist_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}