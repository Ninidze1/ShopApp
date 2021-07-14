package com.example.shopapp.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.databinding.ImageLayoutBinding
import com.example.shopapp.extensions.loadImg
import com.example.shopapp.models.PostItem

class ImageViewPager(val images: List<PostItem.Url?>?): RecyclerView.Adapter<ImageViewPager.ViewHolder>() {

    inner class ViewHolder(private val binding: ImageLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val model = images?.get(adapterPosition)
            if (model != null) {
                model.url?.let { binding.imageView.loadImg(it) }
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