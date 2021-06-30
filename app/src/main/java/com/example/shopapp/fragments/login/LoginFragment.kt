package com.example.shopapp.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.viewModels
import com.example.shopapp.R
import com.example.shopapp.databinding.LoginFragmentBinding
import com.example.shopapp.extensions.diffColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        toRegister()
        binding.appCompatButton.setOnClickListener {
        }
    }

    private fun toRegister() {
        binding.register.diffColor(arrayOf(getString(R.string.new_user), getString(R.string.sign_up), getString(R.string.here)),
            arrayOf(R.color.gray, R.color.main_color, R.color.gray))
    }

}