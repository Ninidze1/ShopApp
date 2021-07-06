package com.example.shopapp.fragments.login

import android.app.Dialog
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.shopapp.R
import com.example.shopapp.base.BaseFragment
import com.example.shopapp.databinding.DialogItemBinding
import com.example.shopapp.databinding.LoginFragmentBinding
import com.example.shopapp.extensions.diffColorCenter
import com.example.shopapp.extensions.isEmail
import com.example.shopapp.extensions.showDialog
import com.example.shopapp.extensions.showToast
import com.example.shopapp.network.network.ResultHandle
import com.example.shopapp.user_state.LoginPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint()
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>(
    LoginFragmentBinding::inflate,
    LoginViewModel::class.java
) {

    @Inject
    lateinit var userInfo: LoginPreference

    override fun setUp(inflater: LayoutInflater, container: ViewGroup?) {
        if (userInfo.checkSession())
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        else
            init()
    }

    private fun init() {
        binding.inpMail.isEndIconVisible = false
        observers()
        toRegister()
        listeners()
    }

    private fun listeners() {
        binding.appCompatButton.setOnClickListener {
            signIn()
        }

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.emailEditText.doOnTextChanged { text, start, before, count ->
            emailChecker(text.toString())
            d("loglog", "$text, ${emailChecker(text.toString())}")
        }
    }

    private fun emailChecker(email: String){
        binding.inpMail.isEndIconVisible = email.isEmail()
    }

    private fun toRegister() {
        binding.register.diffColorCenter(arrayOf(getString(R.string.new_user), getString(R.string.sign_up), getString(R.string.here)),
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
                dialog.showDialog(dialogBinding)
                dialogBinding.title.text = "Error"
                dialogBinding.desc.text = "Enter valid Email"
                dialogBinding.noButton.setOnClickListener {
                    dialog.cancel()
                }
            }
        } else {
            dialog.showDialog(dialogBinding)
            dialogBinding.title.text = "Error"
            dialogBinding.desc.text = "Fill empty fields"
            dialogBinding.noButton.setOnClickListener {
                dialog.cancel()
            }
        }
    }

    private fun observers() {
        viewModel.loginInfo.observe(viewLifecycleOwner, {


            when(it.status) {
                ResultHandle.Companion.Status.SUCCESS -> {
                    requireContext().showToast("Success")
                    it.data?.token?.let { it1 -> userInfo.saveToken(it1) }
                    userInfo.saveUserId(it.data?.userId.toString())
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                ResultHandle.Companion.Status.ERROR -> {
                    requireContext().showToast("Enter Correct Credentials")
                }
            }

        })
    }

}