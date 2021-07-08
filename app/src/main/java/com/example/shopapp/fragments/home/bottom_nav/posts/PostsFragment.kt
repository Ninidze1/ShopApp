package com.example.shopapp.fragments.home.bottom_nav.posts

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
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

    private fun init() {
        viewModel.getPosts()
        recyclerInit()
        listeners()
        observers()
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
        viewModel.post.observe(viewLifecycleOwner, {
            when(it.status) {
                ResultHandle.Status.SUCCESS -> {
                    it.data?.let { data -> adapter.addItems(data) }
                }
                ResultHandle.Status.ERROR -> {
                    d("resultHandle", it.message.toString())
                }
            }
        })
    }

}