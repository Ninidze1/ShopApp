package com.example.shopapp.fragments.home.bottom_nav.posts

import android.Manifest
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.adapters.HomeRecylcerViewAdapter
import com.example.shopapp.base.BaseFragment
import com.example.shopapp.databinding.PostsFragmentBinding
import com.example.shopapp.network.network.ResultHandle
import com.example.shopapp.user_state.LoginPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PostsFragment : BaseFragment<PostsFragmentBinding, PostsViewModel>(
    PostsFragmentBinding::inflate,
    PostsViewModel::class.java
) {

    @Inject
    lateinit var userInfo: LoginPreference
    private lateinit var adapter: HomeRecylcerViewAdapter

    override fun setUp(inflater: LayoutInflater, container: ViewGroup?) {
        init()
    }


    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.CAMERA] == true
                && permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true
                && permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
            ) {
                d("permissionTag", "success")
            } else {
                d("permissionTag", "failed")
            }
        }

    private fun init() {
        requestPerm(requestPermission)

        viewModel.getPosts()
        recyclerInit()
        listeners()
        observers()

        binding.swipe.setOnRefreshListener {
            adapter.refresh()
            viewModel.getPosts()
        }

    }

    private fun listeners() {

        adapter.click = { position ->
            d("loglog", "$position")
        }

    }

    private fun recyclerInit() {
        adapter = HomeRecylcerViewAdapter()
        binding.homeRecylcer.layoutManager = LinearLayoutManager(requireContext())
        binding.homeRecylcer.adapter = adapter
    }


    private fun observers() {
        viewModel.loading.observe(viewLifecycleOwner, {
            binding.swipe.isRefreshing = it
        })

        viewModel.post.observe(viewLifecycleOwner, {
            when(it.status) {
                ResultHandle.Status.SUCCESS -> {
                    d("tagtag", "${it.data}")
                    it.data?.let { data -> adapter.addItems(data.toMutableList()) }
                }
                ResultHandle.Status.ERROR -> {
                    d("resultHandle", it.message.toString())
                }
            }
        })
    }

}