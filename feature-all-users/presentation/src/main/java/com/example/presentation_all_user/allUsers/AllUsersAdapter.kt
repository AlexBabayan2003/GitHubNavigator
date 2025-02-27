package com.example.presentation_all_user.allUsers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.UserResponseDomain
import com.example.presentation_all_users.databinding.ItemUserBinding

class AllUsersAdapter(private val onUserClick: (String) -> Unit) :
    ListAdapter<UserResponseDomain, AllUsersAdapter.UserViewHolder>(UserDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, onUserClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class UserViewHolder(
        private val binding: ItemUserBinding,
        private val onUserClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserResponseDomain) {
            binding.usernameTextView.text = user.username
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .into(binding.userImageView)

            binding.root.setOnClickListener {
                onUserClick(user.username)
            }
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<UserResponseDomain>() {
        override fun areItemsTheSame(oldItem: UserResponseDomain, newItem: UserResponseDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserResponseDomain, newItem: UserResponseDomain): Boolean {
            return oldItem == newItem
        }
    }
}