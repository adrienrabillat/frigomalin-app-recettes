package com.example.frigomalin.ui.detail

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.frigomalin.R
import com.example.frigomalin.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: DetailViewModel by viewModels()
    
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            launchCamera()
        } else {
            Toast.makeText(
                requireContext(),
                R.string.camera_permission_denied,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as? Bitmap
            imageBitmap?.let {
                binding.imgPhoto.setImageBitmap(it)
                binding.imgPhoto.visibility = View.VISIBLE
            }
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val mealId = arguments?.getString("mealId")
        if (mealId != null) {
            viewModel.loadMealDetails(mealId)
        }
        
        setupButtons()
        observeViewModel()
    }
    
    private fun setupButtons() {
        binding.btnShare.setOnClickListener {
            shareRecipe()
        }
        
        binding.btnAddPhoto.setOnClickListener {
            checkCameraPermissionAndLaunch()
        }
    }
    
    private fun observeViewModel() {
        viewModel.mealDetail.observe(viewLifecycleOwner) { meal ->
            meal?.let {
                binding.txtMealName.text = it.name
                binding.txtInstructions.text = it.instructions ?: getString(R.string.detail_error)
                
                Glide.with(this)
                    .load(it.thumbnail)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(binding.imgMealHeader)
                
                val ingredients = it.getIngredientsList()
                if (ingredients.isNotEmpty()) {
                    val ingredientsText = ingredients.joinToString("\n") { (ingredient, measure) ->
                        "• $measure $ingredient"
                    }
                    binding.txtIngredients.text = ingredientsText
                    binding.txtIngredients.visibility = View.VISIBLE
                    binding.txtIngredientsLabel.visibility = View.VISIBLE
                } else {
                    binding.txtIngredients.visibility = View.GONE
                    binding.txtIngredientsLabel.visibility = View.GONE
                }
            }
        }
        
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun shareRecipe() {
        val meal = viewModel.mealDetail.value ?: return
        
        val shareText = getString(
            R.string.share_text,
            meal.name,
            meal.instructions ?: ""
        )
        
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject))
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        
        startActivity(Intent.createChooser(shareIntent, getString(R.string.btn_share)))
    }
    
    private fun checkCameraPermissionAndLaunch() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                launchCamera()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
    
    private fun launchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            cameraLauncher.launch(intent)
        } else {
            Toast.makeText(
                requireContext(),
                R.string.camera_error,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
