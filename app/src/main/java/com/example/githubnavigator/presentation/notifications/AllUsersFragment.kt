package com.example.githubnavigator.presentation.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.githubnavigator.databinding.FragmentNotificationsBinding
import kotlinx.coroutines.launch

class AllUsersFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding: FragmentNotificationsBinding
        get() = _binding ?: throw RuntimeException("FragmentNotificationsBinding == null")
    private lateinit var allUsersViewModel: AllUsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        allUsersViewModel =
            ViewModelProvider(this)[AllUsersViewModel::class.java]

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewLifecycleOwner.lifecycleScope.launch {
            val textView: TextView = binding.textAllUsersTv
            allUsersViewModel.text.collect {
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