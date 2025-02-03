package com.example.githubnavigator.presentation.all_users

import androidx.recyclerview.widget.DiffUtil
import com.example.githubnavigator.domain.allUsers.UserEntityDomain

class UserDiffCallback : DiffUtil.ItemCallback<UserEntityDomain>() {
    override fun areItemsTheSame(oldItem: UserEntityDomain, newItem: UserEntityDomain): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserEntityDomain, newItem: UserEntityDomain): Boolean {
        return oldItem== newItem
    }
}