package com.example.presentation_user_repos

import androidx.recyclerview.widget.DiffUtil
import com.example.domain_user_repos.UserReposDomainEntity

class UserRepoDiffCallback : DiffUtil.ItemCallback<UserReposDomainEntity>() {
    override fun areItemsTheSame(oldItem: UserReposDomainEntity, newItem: UserReposDomainEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserReposDomainEntity, newItem: UserReposDomainEntity): Boolean {
        return oldItem == newItem
    }
}