package com.example.presentation_user_repos

import androidx.recyclerview.widget.DiffUtil
import com.example.domain_user_repos.UserRepos

class UserRepoDiffCallback : DiffUtil.ItemCallback<UserRepos>() {
    override fun areItemsTheSame(oldItem: UserRepos, newItem: UserRepos): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserRepos, newItem: UserRepos): Boolean {
        return oldItem == newItem
    }
}