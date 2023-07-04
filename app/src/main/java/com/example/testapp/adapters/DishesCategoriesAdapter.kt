package com.example.testapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.DishesCategoryItemBinding
import com.example.testapp.viewModels.MainViewModel

@SuppressLint("NotifyDataSetChanged")
class DishesCategoriesAdapter(
    private val context: Context,
    private val viewModel: MainViewModel,
    private val lifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<DishesCategoriesAdapter.DishesCategoriesHolder>() {

    private var categories: List<String> = listOf()
    private var selectedCategory: String? = null

    init {
        viewModel.dishesCategories.observe(lifecycleOwner) {
            categories = it
            notifyDataSetChanged()
        }

        viewModel.selectedCategory.observe(lifecycleOwner) { changedCategory ->
            selectedCategory = changedCategory
            notifyDataSetChanged()
        }
    }

    class DishesCategoriesHolder(val binding: DishesCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesCategoriesHolder {
        return DishesCategoriesAdapter.DishesCategoriesHolder(
            DishesCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DishesCategoriesHolder, position: Int) {
        val category = categories[position]
        if (category == selectedCategory) {
            holder.binding.name.setTextColor(context.getColor(R.color.white))
            holder.binding.card.setCardBackgroundColor(context.getColor(R.color.selected_category))
        }
        else{
            holder.binding.name.setTextColor(context.getColor(R.color.black))
            holder.binding.card.setCardBackgroundColor(context.getColor(R.color.not_selected_category))
        }
        holder.binding.categoryName = category
        holder.binding.card.setOnClickListener {
            if (category != selectedCategory) {
                viewModel.selectedCategory.value = category
            }
        }
    }

    override fun getItemCount(): Int {
        return viewModel.dishesCategories.value?.size ?: 0
    }
}