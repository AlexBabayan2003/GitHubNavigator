package com.example.githubnavigator.presentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.githubnavigator.databinding.FragmentUserRepositoriesBinding
import kotlinx.coroutines.launch

class UserRepositoriesFragment : Fragment() {

    private var _binding: FragmentUserRepositoriesBinding? = null
    private val binding: FragmentUserRepositoriesBinding
        get() = _binding ?: throw IllegalStateException("Binding is null")
    private lateinit var userRepositoriesViewModel: UserRepositoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userRepositoriesViewModel =
            ViewModelProvider(this)[UserRepositoriesViewModel::class.java]

        _binding = FragmentUserRepositoriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewLifecycleOwner.lifecycleScope.launch {
            val textView: TextView = binding.textUserRepositoriesTv
            userRepositoriesViewModel.text.collect {
                textView.text = it
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}