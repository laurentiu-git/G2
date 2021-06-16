package com.rld.g2esports.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rld.g2esports.R
import com.rld.g2esports.adapters.NewsAdapter
import com.rld.g2esports.databinding.FragmentNewsBinding
import com.rld.g2esports.util.Resource
import com.rld.g2esports.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    @Inject
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsViewModel: NewsViewModel
    private var fragmentBinding: FragmentNewsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewsBinding.bind(view)

        newsViewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)

        newsViewModel.newsItem.observe(
                viewLifecycleOwner
        ) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        binding.paginationProgressBar.visibility = View.INVISIBLE
                        newsAdapter.differ.submitList(it)
                    }
                }
                is Resource.Loading -> {
                    binding.paginationProgressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.paginationProgressBar.visibility = View.INVISIBLE
                }
            }

        }

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("link", it)
            }
            findNavController().navigate(
                    R.id.action_newsFragment_to_articleFragment,
                    bundle
            )
        }

        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireView().findViewById<RecyclerView>(R.id.rvNews).adapter = null
        fragmentBinding = null
    }
}