package com.example.presentation_user_repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain_user_repos.UserRepos
import com.example.presentation.user.repos.databinding.ItemUserRepoBinding

class UserReposAdapter : ListAdapter<UserRepos, UserReposAdapter.UserRepoViewHolder>(UserRepoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        val binding = ItemUserRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserRepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }

    class UserRepoViewHolder(private val binding: ItemUserRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: UserRepos) {
            binding.repoNameTextView.text = repo.name
        }
    }
}

