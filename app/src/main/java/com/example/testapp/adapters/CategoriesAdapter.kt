package com.example.testapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.remote.models.CategoriesListItem
import com.example.testapp.databinding.MainCategoryItemBinding
import com.example.testapp.viewModels.MainViewModel
import com.squareup.picasso.Picasso

class CategoriesAdapter(private val categories: List<CategoriesListItem>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    class CategoriesHolder(val binding: MainCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        return CategoriesHolder(
            MainCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int =
        categories.size

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        val category = categories[position]
        holder.binding.category = category
        Picasso.get().load(category.imageUrl).into(holder.binding.image)

    }
}