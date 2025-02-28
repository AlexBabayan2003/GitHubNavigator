package com.example.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.presentation.login.databinding.FragmentLoginScreenBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginScreenFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentLoginScreenBinding == null")
    private val loginScreenViewModel: LoginScreenViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        updateViewMargin()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginScreenViewModel.loginUiState.collectLatest { uiState ->
                    when (uiState) {
                        is LoginUiState.Loading -> {
                            // Show loading indicator if needed.
                        }

                        is LoginUiState.Success -> {
                            navController.navigate(
                                LoginScreenFragmentDirections.actionLoginScreenFragmentNavToUserReposFragment()
                            )
                        }

                        is LoginUiState.Error -> {
                            Snackbar.make(view, uiState.message, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            val token = binding.passwordEt.text.toString()
            loginScreenViewModel.login(username, token)
        }
        val backPressed = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressed)
    }


    private fun updateViewMargin() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val navBarHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
            binding.btnLogin.updateLayoutParams<ConstraintLayout.LayoutParams> {
                bottomMargin = navBarHeight + dpToPx()
            }
            insets
        }
    }

    private fun dpToPx(dp: Int = 20): Int =
        (dp * resources.displayMetrics.density).toInt()


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
