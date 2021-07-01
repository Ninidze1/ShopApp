package com.example.shopapp.fragments.login

import android.app.Dialog
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shopapp.R
import com.example.shopapp.databinding.DialogItemBinding
import com.example.shopapp.databinding.LoginFragmentBinding
import com.example.shopapp.extensions.diffColor
import com.example.shopapp.extensions.isEmail
import com.example.shopapp.extensions.showDialog
import com.example.shopapp.network.network.ResultHandle
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint()
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
        observers()
        toRegister()
        listeners()
    }

    private fun listeners() {
        binding.appCompatButton.setOnClickListener {
            signIn()
        }

        binding.register.setOnClickListener {

        }

        binding.reset.setOnClickListener {

        }

        binding.inpMail.isEndIconVisible = false
        binding.emailEditText.doOnTextChanged { text, start, before, count ->
            emailChecker(text as String)
        }
    }

    private fun emailChecker(email: String) {
        if (email.isEmail()) {
            binding.inpMail.isEndIconVisible = email.isEmail()
        }
    }

    private fun toRegister() {
        binding.register.diffColor(arrayOf(getString(R.string.new_user), getString(R.string.sign_up), getString(R.string.here)),
            arrayOf(R.color.gray, R.color.main_color, R.color.gray))
    }

    private fun signIn() {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogItemBinding.inflate(layoutInflater)

        val email = binding.emailEditText.text!!.trim().toString()
        val password = binding.passwordEditText.text!!.trim().toString()
        if (!email.isNullOrBlank() && !password.isNullOrBlank()) {
            if (email.isEmail()) {
                viewModel.login(email, password)
                viewModel.activateSession(binding.checkBox.isChecked)
            } else {
                dialog.showDialog(dialogBinding, "Error", "enter valid email")
            }
        } else {
            dialog.showDialog(dialogBinding, "Error", "fill the fields")
        }
    }

    private fun observers() {
        viewModel.loginInfo.observe(viewLifecycleOwner, {

            when(it.status) {
                ResultHandle.Companion.Status.SUCCESS -> {
                    d("resultHandle", "success")
                }
                ResultHandle.Companion.Status.ERROR -> {
                    val dialog = Dialog(requireContext())
                    val binding = DialogItemBinding.inflate(layoutInflater)
                    dialog.showDialog(binding, "Error", it.error.toString())
                }
            }

        })
    }

}