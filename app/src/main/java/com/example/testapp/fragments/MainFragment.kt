package com.example.testapp.fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.App
import com.example.testapp.adapters.CategoriesAdapter
import com.example.testapp.data.remote.TestApi
import com.example.testapp.databinding.FragmentMainBinding
import com.example.testapp.viewModels.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var testApi: TestApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.inject(this)
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        viewModel.categories.observe(viewLifecycleOwner){
            val adapter = CategoriesAdapter(it, this, viewModel)
            binding.recyclerCategory.adapter = adapter
            binding.recyclerCategory.layoutManager =LinearLayoutManager(requireContext())
        }

        return binding.root
    }
}