package com.example.githubnavigator.presentation.userRepos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubnavigator.databinding.ItemUserRepoBinding
import com.example.githubnavigator.domain.userRepos.UserReposDomainEntity

class UserReposAdapter : ListAdapter<UserReposDomainEntity, UserReposAdapter.UserRepoViewHolder>(UserRepoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        val binding = ItemUserRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserRepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }

    class UserRepoViewHolder(private val binding: ItemUserRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: UserReposDomainEntity) {
            binding.repoNameTextView.text = repo.name
        }
    }
}

