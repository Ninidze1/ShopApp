package com.example.shopapp.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapp.R
import com.example.shopapp.adapters.DrawerMenuAdapter
import com.example.shopapp.base.BaseFragment
import com.example.shopapp.databinding.HomeFragmentBinding
import com.example.shopapp.models.MenuItem
import com.example.shopapp.user_state.LoginPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(
    HomeFragmentBinding::inflate,
    HomeViewModel::class.java
) {
    private lateinit var adapter: DrawerMenuAdapter
    @Inject
    lateinit var userPreference: LoginPreference

    override fun setUp(inflater: LayoutInflater, container: ViewGroup?) {
        initBottomNav()
        initDrawerMenu()
    }

    private fun initBottomNav() {
        val navController =
            (childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        binding.bottomNavbar.setupWithNavController(navController)

    }

    private fun initDrawerMenu() {

        binding.drawerMenuButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.END)
        }

        adapter = DrawerMenuAdapter(
            arrayOf(
                MenuItem(
                    R.id.action_registerFragment_to_loginFragment,
                    getString(R.string.my_profile)
                ),
                MenuItem(
                    R.id.action_registerFragment_to_loginFragment,
                    getString(R.string.my_post)
                ),
                MenuItem(
                    R.id.action_registerFragment_to_loginFragment,
                    getString(R.string.about)
                ),
                MenuItem(
                    R.id.action_registerFragment_to_loginFragment,
                    getString(R.string.my_cart)
                )
            )
        )

        binding.drawerRecylccer.layoutManager = LinearLayoutManager(requireContext())
        binding.drawerRecylccer.adapter = adapter
        adapter.menuClickListener = {
            val navController = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
            navController.navController.navigate(R.id.action_global_loginFragment)
            userPreference.deleteSession()
        }



    }


}