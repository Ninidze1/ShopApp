package com.example.shopapp.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.shopapp.databinding.ItemLayoutBinding
import com.example.shopapp.extensions.goneIf
import com.example.shopapp.models.PostItem


typealias click = (position: Int) -> Unit
class HomeRecylcerViewAdapter: RecyclerView.Adapter<HomeRecylcerViewAdapter.ViewHolder>() {
    val items = mutableListOf<PostItem>()
    lateinit var click: click

    inner class ViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: PostItem
        private lateinit var imageAdapter: ImageViewPager
        fun bind() {

            model = items[adapterPosition]
            initViewPager()



            binding.itemTitle.text = model.title
            binding.description.text = model.description
            "$ ${model.price.toString()}".also { binding.price.text = it }

            binding.addToCartButton.setOnClickListener {
                click.invoke(adapterPosition)
            }
        }
        private fun initViewPager() {
            imageAdapter = ImageViewPager(model.urls)
            binding.images.adapter = imageAdapter
            if (model.urls!!.size >= 2)
                setIndex(1)

            binding.images.goneIf(model.urls.isNullOrEmpty())
            binding.nextImg.goneIf(model.urls!!.size < 2)
            binding.previousImg.goneIf(model.urls!!.size < 2)
            binding.imagesPosition.goneIf(model.urls!!.size < 2)

            binding.images.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setIndex(position + 1)
                }
            })
        }

        private fun setIndex(position: Int) {
            "$position/${model.urls!!.size}".also { binding.imagesPosition.text = it }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = items.size

    fun addItems(items: MutableList<PostItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun refresh() {
        this.items.clear()
        notifyDataSetChanged()
    }

}