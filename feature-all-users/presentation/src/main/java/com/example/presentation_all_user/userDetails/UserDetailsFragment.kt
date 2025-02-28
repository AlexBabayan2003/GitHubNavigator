package com.example.presentation_all_user.userDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.domain.userDetails.UserDetails
import com.example.presentation.userDetails.UserReposAdapter
import com.example.presentation_all_users.databinding.FragmentUserDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserDetailsViewModel by viewModels()
    private lateinit var adapter: UserReposAdapter
    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserReposAdapter()
        binding.reposRecyclerView.adapter = adapter
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val username = args.username
        viewModel.loadUserDetails(username)
        observeVm()
    }

    private fun observeVm() {
        collectLatestLifecycleFlow(viewModel.userDetailsState) { userDetails ->
            updateUserDetailsUI(userDetails)
        }

        collectLatestLifecycleFlow(viewModel.userReposState) { repos ->
            adapter.submitList(repos)
        }

        collectLatestLifecycleFlow(viewModel.isLoading) { isLoading ->
            binding.progressBar.visibility= if (isLoading) View.VISIBLE else View.GONE
        }

        collectLatestLifecycleFlow(viewModel.errorState) { error ->
            if (!error.isNullOrEmpty()) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun <T> Fragment.collectLatestLifecycleFlow(
        flow: Flow<T>,
        collect: suspend (T) -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest {
                    collect(it)}
            }
        }
    }
    private fun updateUserDetailsUI(userDetails: UserDetails?) {
        if (userDetails != null) {
            binding.usernameTextView.text = userDetails.username
            Glide.with(requireContext())
                .load(userDetails.avatarUrl)
                .into(binding.userImageView)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}