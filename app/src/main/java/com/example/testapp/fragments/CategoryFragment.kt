package com.example.testapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.adapters.DishesAdapter
import com.example.testapp.adapters.DishesCategoriesAdapter
import com.example.testapp.databinding.FragmentCategoryBinding
import com.example.testapp.viewModels.MainViewModel


private const val CATEGORY = "Category"

class CategoryFragment : Fragment() {
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(CATEGORY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCategoryBinding.inflate(inflater, container, false)

        category?.let { binding.categoryName = category }
        binding.btnBack.setOnClickListener {
            requireParentFragment().childFragmentManager.popBackStack()
        }
        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        val adapterCategory = DishesCategoriesAdapter(requireContext(), viewModel, viewLifecycleOwner)
        binding.categoriesRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.categoriesRecycler.adapter = adapterCategory

        val adapterDishes = DishesAdapter(this, viewModel, viewLifecycleOwner)
        binding.dishesRecycler.layoutManager = GridLayoutManager(requireContext(),3)
        binding.dishesRecycler.adapter = adapterDishes

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(category: String) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY, category)
                }
            }
    }
}