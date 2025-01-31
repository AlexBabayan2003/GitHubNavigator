package com.example.githubnavigator.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.githubnavigator.databinding.FragmentLoginScreenBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginScreenFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentLoginScreenBinding == null")

    private val loginScreenViewModel: LoginScreenViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentLoginScreenBinding.inflate(inflater, container, false)
            .also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        loginScreenViewModel.loginUiState.observe(viewLifecycleOwner, Observer { uiState ->
            when (uiState) {
                is LoginUiState.Loading -> {
                    // Показать индикатор загрузки
                }

                is LoginUiState.Success -> {
                    // Переход в основной экран
                    navController.navigate(LoginScreenFragmentDirections.actionLoginScreenFragmentToNavigationProfile())
                }

                is LoginUiState.Error -> {
                    // Показать ошибку через SnackBar
                    Snackbar.make(view, uiState.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        binding.btnLogin.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            val token = binding.passwordEt.text.toString()
            loginScreenViewModel.login(username, token)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}