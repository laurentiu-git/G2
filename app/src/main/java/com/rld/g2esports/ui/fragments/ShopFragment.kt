package com.rld.g2esports.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rld.g2esports.R
import com.rld.g2esports.adapters.ShopAdapter
import com.rld.g2esports.databinding.FragmentShopBinding
import com.rld.g2esports.viewmodels.ShopViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.rld.g2esports.util.Resource
import javax.inject.Inject

@AndroidEntryPoint
class ShopFragment: Fragment(R.layout.fragment_shop) {

    @Inject
    lateinit var shopAdapter: ShopAdapter
    lateinit var shopViewModel: ShopViewModel
    private var fragmentBinding: FragmentShopBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentShopBinding.bind(view)

        shopViewModel = ViewModelProvider(requireActivity()).get(ShopViewModel::class.java)

        shopAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("shopItem", it)
            }
            findNavController().navigate(
                    R.id.action_homeFragment_to_shopDetailsFragment,
                    bundle
            )
        }
        
        
        shopViewModel.shopItems.observe(
            viewLifecycleOwner
        ) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.paginationProgressBar.visibility = View.INVISIBLE
                    response.data?.let {
                        shopAdapter.differ.submitList(it)
                    }
                }
                is Resource.Loading ->{
                    binding.paginationProgressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.paginationProgressBar.visibility = View.INVISIBLE
                }
            }

        }

        binding.rvShop.apply {
            adapter = shopAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireView().findViewById<RecyclerView>(R.id.rvShop).adapter = null
        fragmentBinding = null
    }

}