package com.example.githubnavigator.presentation.allUsers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubnavigator.databinding.FragmentAllUsersBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllUsersFragment : Fragment() {

    private val viewModel: AllUsersViewModel by viewModels()
    private lateinit var adapter: AllUsersAdapter
    private var _binding: FragmentAllUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = AllUsersAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                    firstVisibleItemPosition >= 0
                ) {
                    viewModel.loadUsers()
                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel.users.observe(viewLifecycleOwner) { users ->
            adapter.submitList(users)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            // Handle loading state (e.g., show/hide progress bar)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}