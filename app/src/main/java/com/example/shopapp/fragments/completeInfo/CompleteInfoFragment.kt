package com.example.shopapp.fragments.completeInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shopapp.base.BaseFragment
import com.example.shopapp.databinding.CompleteInfoFragmentBinding

class CompleteInfoFragment : BaseFragment<CompleteInfoFragmentBinding, CompleteInfoViewModel>(
    CompleteInfoFragmentBinding::inflate,
    CompleteInfoViewModel::class.java
) {
    override fun setUp(inflater: LayoutInflater, container: ViewGroup?) {
        init()
    }

    private fun init() {
//        val requestPermission =
//            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
//                if (permissions[Manifest.permission.CAMERA] == true
//                    && permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true
//                    && permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
//                ) {
//                    // TODO: 7/14/2021
//                }
//        }
    }


}