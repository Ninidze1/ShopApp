package com.example.shopapp.fragments.completeInfo

import android.Manifest
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.shopapp.base.BaseFragment
import com.example.shopapp.databinding.CompleteInfoFragmentBinding
import com.example.shopapp.extensions.getFileFromUri
import com.example.shopapp.extensions.loadUri
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompleteInfoFragment : BaseFragment<CompleteInfoFragmentBinding, CompleteInfoViewModel>(
    CompleteInfoFragmentBinding::inflate,
    CompleteInfoViewModel::class.java
) {

    private lateinit var uri: Uri

    override fun setUp(inflater: LayoutInflater, container: ViewGroup?) {
        init()
    }

    private fun init() {
        listeners()
    }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        if (permissions[Manifest.permission.CAMERA] == true
            && permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true
            && permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
        ) {
            openCamera()
        }
    }

    private fun listeners() {
        binding.profilePic.setOnClickListener {
            if (hasCameraPermission() && writeStoragePermission())
                openCamera()
            else
                requestPerm(requestPermission)
        }
    }

    private fun openCamera() {
        val filename = "photo.jpg"
        val imageUri =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }
        val imagesDetails = ContentValues().apply {
            put(MediaStore.Audio.Media.DISPLAY_NAME, filename)
        }
        requireContext().contentResolver.insert(imageUri, imagesDetails).let {
            if (it != null) {
                uri = it
                takePicture.launch(uri)
            }
        }
    }

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        binding.profilePic.loadUri(uri)
        requireActivity().getFileFromUri(uri)
    }


}