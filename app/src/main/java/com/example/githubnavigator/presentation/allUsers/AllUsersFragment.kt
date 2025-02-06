package com.example.githubnavigator.presentation.allUsers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubnavigator.databinding.FragmentAllUsersBinding
import com.example.githubnavigator.domain.allUsers.UserEntityDomain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllUsersFragment : Fragment() {

    private var _binding: FragmentAllUsersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllUsersViewModel by viewModels()
    private lateinit var adapter: AllUsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AllUsersAdapter { username ->
            Log.d("AllUsersFragment", "Navigating to details for: $username")
            val action = AllUsersFragmentDirections.actionNavigationAllUsersToUserDetailsFragment(username)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.observe(viewLifecycleOwner) { users ->
                    adapter.submitList(users.map { it.toUserResponse() })
                }
            }
        }

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
//                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//                }
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun UserEntityDomain.toUserResponse() =
        avatarUrl?.let {
            com.example.githubnavigator.data.remote.UserResponse(
                username = username,
                id = id,
                avatarUrl = it,
                fullName = "",
                bio = ""
            )
        }
}