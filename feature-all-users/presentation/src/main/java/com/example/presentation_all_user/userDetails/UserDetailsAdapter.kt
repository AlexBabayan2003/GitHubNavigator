package com.example.presentation.userDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.userDetails.UserRepo
import com.example.presentation_all_users.databinding.ItemUserRepoBinding

class UserReposAdapter :
    ListAdapter<UserRepo, UserReposAdapter.UserRepoViewHolder>(UserRepoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        val binding =
            ItemUserRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserRepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }

    class UserRepoViewHolder(private val binding: ItemUserRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: UserRepo) {
            binding.repoNameTextView.text = repo.name
        }
    }

    class UserRepoDiffCallback : DiffUtil.ItemCallback<UserRepo>() {
        override fun areItemsTheSame(
            oldItem: UserRepo,
            newItem: UserRepo
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserRepo,
            newItem: UserRepo
        ): Boolean {
            return oldItem == newItem
        }
    }
}