package com.example.githubnavigator.presentation.all_users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubnavigator.R
import com.example.githubnavigator.databinding.ItemUserBinding
import com.example.githubnavigator.domain.allUsers.UserEntityDomain

class AllUsersAdapter : ListAdapter<UserEntityDomain, AllUsersAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntityDomain) {
            binding.usernameTextView.text = user.username
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.userImageView)
        }
    }
}

