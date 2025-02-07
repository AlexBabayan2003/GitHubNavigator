package com.example.githubnavigator.presentation.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.githubnavigator.R
import com.example.githubnavigator.data.login.UserPreferences
import com.example.githubnavigator.databinding.FragmentProfileBinding
import com.example.githubnavigator.domain.profile.ProfileDomainEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: error("FragmentProfileBinding == null")

    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var navController: NavController

    @Inject
    lateinit var userPreferences: UserPreferences

    private val pickImageLauncher = registerForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            requireContext().contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            val current = profileViewModel.profileState.value
            if (current != null) {
                val updatedProfile = current.copy(avatarLocalUri = uri.toString())
                profileViewModel.updateProfile(updatedProfile)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        requireActivity().addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.profile_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.action_settings -> {
                             navController.navigate(R.id.action_profileFragment_to_settingsFragment)
                            true
                        }

                        else -> false
                    }
                }
            },
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )

        val username = userPreferences.getUsername()
        if (username != null) {
            profileViewModel.loadProfile(username)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    profileViewModel.profileState.collectLatest { profile ->
                        updateUI(profile)
                    }
                }
                launch {
                    profileViewModel.isLoading.collectLatest { isLoading ->
                        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                        binding.imageAvatar.visibility =
                            if (isLoading) View.INVISIBLE else View.VISIBLE
                    }
                }
            }
        }


        binding.imageAvatar.setOnClickListener {
            pickImageLauncher.launch(arrayOf("image/*"))
        }

        // Logout
        binding.btnLogout.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                profileViewModel.logout()
            }
            navController.navigate(R.id.action_navigation_profile_to_login_screen_fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun updateUI(profile: ProfileDomainEntity?) {
        if (profile == null) {
            binding.textUsername.text = "Unknown"
            binding.imageAvatar.setImageResource(R.drawable.ic_profile)
        } else {
            val displayName = profile.fullName ?: profile.username
            binding.textUsername.text = displayName

            if (!profile.avatarLocalUri.isNullOrEmpty()) {
                val uri = Uri.parse(profile.avatarLocalUri)
                val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION
                val persistedUriPermissions =
                    requireContext().contentResolver.persistedUriPermissions
                val hasPermission =
                    persistedUriPermissions.any { it.uri == uri && it.isReadPermission }

                if (hasPermission) {
                    binding.imageAvatar.setImageURI(uri)
                } else {
                    binding.imageAvatar.setImageResource(R.drawable.ic_profile)
                    profileViewModel.updateProfile(profile.copy(avatarLocalUri = null))
                }
            } else {
                binding.imageAvatar.setImageResource(R.drawable.ic_profile)
            }
        }
    }
}
