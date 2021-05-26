package com.tdtd.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.tdtd.presentation.BR
import com.tdtd.presentation.databinding.RowMainUserRoomItemBinding
import com.tdtd.presentation.entity.Room

class MainAdapter(
    private val onClick: (room: Room) -> Unit,
    private val addBookmark: (room: Room) -> Unit,
    private val deleteBookmark: (room: Room) -> Unit
) : ListAdapter<Room, MainAdapter.MainViewHolder>(CategoryDiffCallback()) {

    private val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowMainUserRoomItemBinding.inflate(layoutInflater, parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class MainViewHolder constructor(val binding: RowMainUserRoomItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Room) {
            binding.setVariable(BR.room, data)
            binding.container.setOnClickListener {
                onClick(data)
            }
            binding.favoritesButton.isSelected = data.is_bookmark
            binding.favoritesButton.setOnClickListener {
                when (binding.favoritesButton.isSelected) {
                    false -> {
                        addBookmark(data)
                        binding.favoritesButton.isSelected = true
                        data.is_bookmark = true
                        val bundle = Bundle()
                        bundle.putString("value", "on")
                        firebaseAnalytics.logEvent("Favorite", bundle)
                    }
                    true -> {
                        deleteBookmark(data)
                        binding.favoritesButton.isSelected = false
                        data.is_bookmark = false
                        val bundle = Bundle()
                        bundle.putString("value", "off")
                        firebaseAnalytics.logEvent("Favorite", bundle)
                    }
                }
            }
            binding.hostImageView.isVisible = data.is_host
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class CategoryDiffCallback : DiffUtil.ItemCallback<Room>() {
    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.created_at == newItem.created_at
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }
}
