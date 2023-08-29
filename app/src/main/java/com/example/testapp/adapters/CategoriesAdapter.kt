package com.example.testapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.data.models.CategoriesListItem
import com.example.testapp.databinding.MainCategoryItemBinding
import com.example.testapp.fragments.CategoryFragment
import com.example.testapp.viewModels.MainViewModel
import com.squareup.picasso.Picasso

class CategoriesAdapter(
    private val categories: List<CategoriesListItem>,
    private val fragment: Fragment,
    private val viewModel: MainViewModel
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    class CategoriesHolder(val binding: MainCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

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
        onClickCategory(holder.binding.card, category.name)
    }

    private fun onClickCategory(card: View, category: String) {
        card.setOnClickListener {
            viewModel.dishesCategories.value?.let {
                viewModel.selectedCategory.value = it.first()
            }
            fragment.childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CategoryFragment.newInstance(category))
                .addToBackStack("CategoryFragment")
                .commit()
        }
    }
}