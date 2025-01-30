package com.example.githubnavigator.presentation.loginscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.githubnavigator.databinding.FragmentLoginScreenBinding

class LoginScreenFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val binding: FragmentLoginScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginScreenBinding == null")

    private lateinit var loginScreenViewModel: LoginScreenViewModel

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
        loginScreenViewModel = ViewModelProvider(this)[LoginScreenViewModel::class.java]
        navController = findNavController()
        binding.btnLogin.setOnClickListener {
            navController.navigate(LoginScreenFragmentDirections.actionLoginScreenFragmentToNavigationProfile())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}