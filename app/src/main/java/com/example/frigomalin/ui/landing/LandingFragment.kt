package com.example.frigomalin.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.frigomalin.R
import com.example.frigomalin.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {
    
    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnSearchRecipes.setOnClickListener {
            findNavController().navigate(R.id.action_landing_to_search)
        }
        
        binding.btnAbout.setOnClickListener {
            findNavController().navigate(R.id.action_landing_to_readme)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
