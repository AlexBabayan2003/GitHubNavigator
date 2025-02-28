package com.example.presentation_user_repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.extension.collectLatestLifecycleFlow as collect
import com.example.presentation.user.repos.databinding.FragmentUserReposBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserReposFragment : Fragment() {

    private var _binding: FragmentUserReposBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserReposViewModel by viewModels()
    private lateinit var adapter: UserReposAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserReposAdapter()
        binding.reposRecyclerView.adapter = adapter
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        collect(viewModel.reposState) { repos ->
            adapter.submitList(repos)
        }

        collect(viewModel.isLoading) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        collect(viewModel.errorState) { error ->
            if (!error.isNullOrEmpty()) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}