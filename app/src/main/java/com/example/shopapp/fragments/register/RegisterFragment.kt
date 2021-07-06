package com.example.shopapp.fragments.register

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.shopapp.R
import com.example.shopapp.base.BaseFragment
import com.example.shopapp.databinding.DialogItemBinding
import com.example.shopapp.databinding.RegisterFragmentBinding
import com.example.shopapp.extensions.diffColorEnd
import com.example.shopapp.extensions.isEmail
import com.example.shopapp.extensions.showDialog
import com.example.shopapp.network.network.ResultHandle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterFragmentBinding, RegisterViewModel>(
    RegisterFragmentBinding::inflate,
    RegisterViewModel::class.java
) {
    override fun setUp(inflater: LayoutInflater, container: ViewGroup?) {
        init()

    }

    private fun init() {
        binding.inpMail.isEndIconVisible = false
        listeners()
        toRegister()
    }

    private fun observers() {
        viewModel.registerInfo.observe(viewLifecycleOwner, {
            when (it.status) {
                ResultHandle.Status.SUCCESS -> {
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
                ResultHandle.Status.ERROR -> {
                    dialog(getString(R.string.error), it.message.toString())
                }
            }
        })
    }

    private fun listeners() {

        binding.RegisterButton.setOnClickListener {
            register()
        }

        binding.loginfromReg.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.emailEditText.doOnTextChanged { text, _, _, _ ->
            emailChecker(text.toString())
        }
    }

    private fun emailChecker(email: String) {
        binding.inpMail.isEndIconVisible = email.isEmail()
    }

    private fun toRegister() {
        binding.loginfromReg.diffColorEnd(
            arrayOf(getString(R.string.already_a_member), getString(R.string.log_in)),
            arrayOf(R.color.gray, R.color.main_color)
        )
    }

    private fun register() {

        val email = binding.emailEditText.text!!.trim().toString()
        val fullName = binding.fullnameEt.text!!.trim().toString()
        val password = binding.passwordEditText.text!!.trim().toString()
        val repPassword = binding.passwordRepeatEditText.text!!.trim().toString()

        if (!email.isNullOrBlank() && !password.isNullOrBlank() && !repPassword.isNullOrBlank() && !fullName.isNullOrBlank()) {
            if (email.isEmail() && password == repPassword) {
                observers()
                viewModel.register(email, fullName, password)
            } else {
                dialog(getString(R.string.error), getString(R.string.enter_valid_email))
            }
        } else {
            dialog(getString(R.string.error), getString(R.string.fill_fields))
        }
    }

    private fun dialog(title: String, desc: String) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogItemBinding.inflate(layoutInflater)
        dialog.showDialog(dialogBinding)
        dialogBinding.title.text = title
        dialogBinding.desc.text = desc
        dialogBinding.noButton.setOnClickListener {
            dialog.cancel()
        }
    }


}