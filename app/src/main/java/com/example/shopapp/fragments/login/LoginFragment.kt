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
import com.example.shopapp.extensions.*
import com.example.shopapp.network.network.ResultHandle
import com.example.shopapp.user_state.LoginPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>(
    LoginFragmentBinding::inflate,
    LoginViewModel::class.java
) {

    @Inject
    lateinit var userInfo: LoginPreference

    override fun setUp(inflater: LayoutInflater, container: ViewGroup?) {
        d("preferencelog", "${userInfo.checkSession()}")
        init()
    }

    private fun init() {
        binding.progressBar.hide()
        binding.inpMail.isEndIconVisible = false
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

        binding.emailEditText.doOnTextChanged { text, _, _, _ ->
            emailChecker(text.toString())
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

        val email = binding.emailEditText.text!!.trim().toString()
        val password = binding.passwordEditText.text!!.trim().toString()
        if (!email.isNullOrBlank() && !password.isNullOrBlank()) {
            if (email.isEmail()) {
                viewModel.login(email, password)
                observers()
            } else {
                dialog(getString(R.string.error), getString(R.string.enter_valid_email))
            }
        } else {
            dialog(getString(R.string.error), getString(R.string.fill_fields))
        }
    }

    private fun observers() {

        viewModel.loginInfo.observe(viewLifecycleOwner, {
            binding.progressBar.hideIf(it.loading)
            when(it.status) {
                ResultHandle.Status.SUCCESS -> {
                    userInfo.saveSession(binding.checkBox.isChecked)
                    userInfo.saveUserId(it.data?.userId.toString())
                    it.data?.token?.let { it1 -> userInfo.saveToken(it1) }

                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
                ResultHandle.Status.ERROR -> {
                    requireContext().showToast("incorrect credentials")
                }
            }
        })
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