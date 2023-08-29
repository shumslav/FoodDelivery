package com.example.testapp.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.data.models.DishesItem
import com.example.testapp.databinding.DishesItemBinding
import com.example.testapp.fragments.ProductFragment
import com.example.testapp.fragments.SearchFragment
import com.squareup.picasso.Picasso

@SuppressLint("NotifyDataSetChanged")
class SearchDishesAdapter(
    private val fragment: SearchFragment,
    private var dishes: List<DishesItem>
) :
    RecyclerView.Adapter<SearchDishesAdapter.DishesHolder>() {

    class DishesHolder(val binding: DishesItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesHolder {
        return DishesHolder(
            DishesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DishesHolder, position: Int) {
        val dish = dishes[position]
        holder.binding.dishName.text = dish.name
        Picasso.get().load(dish.imgUrl).into(holder.binding.dishImage)
        holder.binding.card.setOnClickListener {
            Log.i("SearchHERE", "Click")
            fragment.childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_search, ProductFragment.newInstance(dish.id))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }


    fun refreshData(newDishes:List<DishesItem>){
        dishes = newDishes
        notifyDataSetChanged()
    }
}