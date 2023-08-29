package com.example.testapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.remote.models.DishesItem
import com.example.testapp.databinding.BagProductItemBinding
import com.example.testapp.viewModels.MainViewModel
import com.squareup.picasso.Picasso

class BagAdapter(
    private var chosenProductsId: Map<Int, Int>,
    private var products: List<DishesItem>,
    private val viewModel: MainViewModel
) :
    RecyclerView.Adapter<BagAdapter.BagHolder>() {

    private var listOfId = mutableListOf<Int>().apply {
        addAll(chosenProductsId.keys)
        sort()
    }

    class BagHolder(val binding: BagProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagHolder {
        return BagHolder(
            BagProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BagHolder, position: Int) {
        val productId = listOfId[position]
        val product = products.find { it.id == productId }
        product?.let { it ->
            holder.binding.count.text = chosenProductsId[productId].toString()
            Picasso.get().load(it.imgUrl).into(holder.binding.image)
            holder.binding.name.text = it.name
            holder.binding.price.text = "${it.price} ₽"
            holder.binding.weight.text = " · ${it.weight}г"

            holder.binding.minusBtn.setOnClickListener {
                viewModel.bag.value?.let { bag ->
                    if (bag.contains(productId)) {
                        bag[productId] = bag[productId]!! - 1
                        if (bag[productId] == 0)
                            bag.remove(productId)
                    }
                }
                viewModel.bag.value = viewModel.bag.value
            }

            holder.binding.plusBtn.setOnClickListener {
                viewModel.bag.value?.let { bag ->
                    if (bag.contains(productId))
                        bag[productId] = bag[productId]!! + 1
                }
                viewModel.bag.value = viewModel.bag.value
            }
        }
    }

    override fun getItemCount(): Int =
        chosenProductsId.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(chosenProductsId: Map<Int, Int>, products: List<DishesItem>) {
        this.chosenProductsId = chosenProductsId
        listOfId = mutableListOf<Int>().apply {
            addAll(chosenProductsId.keys)
            sort()
        }
        this.products = products
        notifyDataSetChanged()
    }
}