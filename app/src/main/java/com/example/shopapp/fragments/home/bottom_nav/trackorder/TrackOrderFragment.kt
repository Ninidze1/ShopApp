package com.example.shopapp.fragments.home.bottom_nav.trackorder

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopapp.R

class TrackOrderFragment : Fragment() {

    companion object {
        fun newInstance() = TrackOrderFragment()
    }

    private lateinit var viewModel: TrackOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.track_order_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TrackOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}