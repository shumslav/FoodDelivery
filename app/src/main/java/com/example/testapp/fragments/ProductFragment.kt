package com.example.testapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.databinding.FragmentProductBinding
import com.example.testapp.viewModels.MainViewModel
import com.squareup.picasso.Picasso

private const val PRODUCT_ID = "productId"

class ProductFragment : Fragment() {
    private var productId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getInt(PRODUCT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProductBinding.inflate(inflater,container,false)
        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        val product = viewModel.dishes.value?.run {
            find { it.id==productId }
        }
        product?.let {
            binding.product = product
            Picasso.get().load(it.imgUrl).into(binding.image)
            binding.price.text = "${it.price} ₽"
            binding.weight.text = " · ${it.weight}г"
        }

        binding.closeBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(productId: Int) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(PRODUCT_ID, productId)
                }
            }
    }
}