package com.example.shopapp.adapters


import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.databinding.ImageLayoutBinding
import com.example.shopapp.extensions.loadImg

class ImagesRecyclerAdapter(val images: List<String?>?): RecyclerView.Adapter<ImagesRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ImageLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val model = images?.get(adapterPosition)
            if (model != null) {
                binding.imageView.loadImg(model)
                d("tagtag", "$model")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ImageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = images!!.size

}