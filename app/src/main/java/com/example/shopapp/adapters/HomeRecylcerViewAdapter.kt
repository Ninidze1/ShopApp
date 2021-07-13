package com.example.shopapp.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.databinding.ItemLayoutBinding
import com.example.shopapp.models.PostItem


typealias click = (position: Int) -> Unit
class HomeRecylcerViewAdapter: RecyclerView.Adapter<HomeRecylcerViewAdapter.ViewHolder>() {
    val items = mutableListOf<PostItem>()
    lateinit var click: click
    private lateinit var adapter: ImagesRecyclerAdapter

    inner class ViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: PostItem
        fun bind() {

            model = items[adapterPosition]
            adapter = ImagesRecyclerAdapter(model.urls)
            binding.images.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.images.adapter = adapter



            binding.itemTitle.text = model.title
            binding.description.text = model.description
            "$ ${model.price.toString()}".also { binding.price.text = it }

            binding.addToCartButton.setOnClickListener {
                click.invoke(adapterPosition)
            }
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