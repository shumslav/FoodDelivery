package com.example.testapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.adapters.BagAdapter
import com.example.testapp.data.remote.models.DishesItem
import com.example.testapp.databinding.FragmentBagBinding
import com.example.testapp.viewModels.MainViewModel

class BagFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBagBinding.inflate(inflater,container,false)
        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        val adapter = BagAdapter(mapOf(), listOf(), viewModel)
        viewModel.bag.observe(viewLifecycleOwner){
            Log.i("Bag", it.toString())
            viewModel.dishes.value?.run {
                adapter.updateData(it,this)
                binding.result.text = "Оплатить ${updateResult(it, this)} ₽"
            }
            if (it.isNotEmpty())
                binding.resultCard.visibility = View.VISIBLE
            else
                binding.resultCard.visibility = View.GONE
        }
        binding.bagRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.bagRecycler.adapter = adapter
        return binding.root
    }

    private fun updateResult(chosenProducts:Map<Int,Int>, products: List<DishesItem>):Int{
        var sum = 0
        chosenProducts.keys.forEach { id ->
            products.find { it.id==id }?.run {
                sum += this.price * chosenProducts[id]!!
            }
        }
        return sum
    }
}