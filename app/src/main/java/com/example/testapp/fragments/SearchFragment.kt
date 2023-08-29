package com.example.testapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testapp.adapters.SearchDishesAdapter
import com.example.testapp.data.models.DishesItem
import com.example.testapp.databinding.FragmentSearchBinding
import com.example.testapp.viewModels.MainViewModel


class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.search.setText(viewModel.searchString.value ?: "")

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (viewModel.searchString.value != s) {
                    viewModel.searchString.value = s?.toString() ?: ""
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        val adapter = SearchDishesAdapter(this, listOf())
        binding.dishesRecycler.run {
            layoutManager = GridLayoutManager(requireContext(), 3)
            this.adapter = adapter
        }

        viewModel.searchString.observe(viewLifecycleOwner){searchString ->
            val dishes = viewModel.dishes.value?.filter { checkDish(it,searchString) } ?: listOf()
            adapter.refreshData(dishes)
        }

        return binding.root
    }

    private fun checkDish(dish: DishesItem, str:String):Boolean{
        return dish.name.lowercase().contains(str.lowercase())
    }
}