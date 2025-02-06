package com.example.githubnavigator.presentation.userRepos

import androidx.recyclerview.widget.DiffUtil
import com.example.githubnavigator.domain.userRepos.UserReposDomainEntity

class UserRepoDiffCallback : DiffUtil.ItemCallback<UserReposDomainEntity>() {
    override fun areItemsTheSame(oldItem: UserReposDomainEntity, newItem: UserReposDomainEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserReposDomainEntity, newItem: UserReposDomainEntity): Boolean {
        return oldItem == newItem
    }
}