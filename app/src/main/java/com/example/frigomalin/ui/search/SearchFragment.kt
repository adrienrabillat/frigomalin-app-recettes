package com.example.frigomalin.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.frigomalin.R
import com.example.frigomalin.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: MealAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupSearchInput()
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        adapter = MealAdapter { meal ->
            val bundle = bundleOf("mealId" to meal.id)
            findNavController().navigate(R.id.action_search_to_detail, bundle)
        }
        
        binding.recyclerViewMeals.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewMeals.adapter = adapter
    }
    
    private fun setupSearchInput() {
        binding.btnSearch.setOnClickListener {
            performSearch()
        }
        
        binding.editTextSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }
    
    private fun performSearch() {
        val query = binding.editTextSearch.text.toString().trim()
        viewModel.searchMeals(query)
    }
    
    private fun observeViewModel() {
        viewModel.meals.observe(viewLifecycleOwner) { meals ->
            adapter.submitList(meals)
            
            binding.txtEmptyState.visibility = if (meals.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
