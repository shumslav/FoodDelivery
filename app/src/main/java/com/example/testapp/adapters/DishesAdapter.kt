package com.example.testapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.data.remote.models.DishesItem
import com.example.testapp.databinding.DishesItemBinding
import com.example.testapp.fragments.CategoryFragment
import com.example.testapp.fragments.ProductFragment
import com.example.testapp.viewModels.MainViewModel
import com.squareup.picasso.Picasso

@SuppressLint("NotifyDataSetChanged")
class DishesAdapter(
    private val fragment: CategoryFragment,
    private val viewModel: MainViewModel,
    private val lifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<DishesAdapter.DishesHolder>() {

    private var dishes: List<DishesItem> = listOf()

    init {
        viewModel.selectedCategory.observe(lifecycleOwner){
            dishes = viewModel.getDishesByCategory(it)
            notifyDataSetChanged()
        }
    }

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
        fragment.parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_categories, ProductFragment.newInstance(dish.id))
            .addToBackStack(null)
            .commit()
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }
}