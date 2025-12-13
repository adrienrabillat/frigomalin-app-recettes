package com.example.frigomalin.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.frigomalin.R
import com.example.frigomalin.data.Meal
import com.example.frigomalin.databinding.ItemMealBinding

class MealAdapter(
    private val onMealClick: (Meal) -> Unit
) : ListAdapter<Meal, MealAdapter.MealViewHolder>(MealDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MealViewHolder(binding, onMealClick)
    }
    
    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class MealViewHolder(
        private val binding: ItemMealBinding,
        private val onMealClick: (Meal) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(meal: Meal) {
            binding.txtMealName.text = meal.name
            
            Glide.with(binding.root.context)
                .load(meal.thumbnail)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .into(binding.imgMeal)
            
            binding.root.setOnClickListener {
                onMealClick(meal)
            }
        }
    }
    
    private class MealDiffCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }
}
