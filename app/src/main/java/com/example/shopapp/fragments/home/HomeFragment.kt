package com.example.shopapp.fragments.home

import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.adapters.HomeRecylcerViewAdapter
import com.example.shopapp.base.BaseFragment
import com.example.shopapp.databinding.DialogItemBinding
import com.example.shopapp.databinding.HomeFragmentBinding
import com.example.shopapp.extensions.showDialog
import com.example.shopapp.network.network.ResultHandle
import com.example.shopapp.user_state.LoginPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(
    HomeFragmentBinding::inflate,
    HomeViewModel::class.java
) {

    @Inject
    lateinit var userInfo: LoginPreference
    private lateinit var adapter: HomeRecylcerViewAdapter

    override fun setUp(inflater: LayoutInflater, container: ViewGroup?) {
        init()
    }

    private fun init() {
        viewModel.getPosts()
        recyclerInit()
        observers()
    }

    private fun listeners() {
        // drawer menu ვერ მოვასწარი და ამ ღილაკზე დაჭერით Shared Pref -იდან სესია იშლება
        binding.drawerMenuButton.setOnClickListener {
            userInfo.deleteSession()
        }
    }

    private fun recyclerInit() {
        adapter = HomeRecylcerViewAdapter()
        binding.homeRecylcer.layoutManager = LinearLayoutManager(requireContext())
        binding.homeRecylcer.adapter = adapter
    }


    private fun observers() {
        viewModel.post.observe(viewLifecycleOwner, {
            when(it.status) {
                ResultHandle.Companion.Status.SUCCESS -> {
                    it.data?.let { data -> adapter.addItems(data) }
                }
                ResultHandle.Companion.Status.ERROR -> {
                    Log.d("resultHandle", it.error.toString())
                }
            }
        })
    }


}