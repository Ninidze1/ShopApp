package com.example.shopapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.databinding.ItemLayoutBinding
import com.example.shopapp.models.Item

typealias click = (position: Int) -> Unit
class HomeRecylcerViewAdapter: RecyclerView.Adapter<HomeRecylcerViewAdapter.ViewHolder>() {
    val items = mutableListOf<Item>()
    lateinit var click: click

    inner class ViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val model = items[adapterPosition]
            binding.itemTitle.text = model.title
            binding.description.text = model.description
            binding.imageSwitcher.setFactory {
                TODO()
            }

            binding.price.text = "$ ${model.price.toString()}"

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

    fun addItems(items: MutableList<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}