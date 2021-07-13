package com.example.shopapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.databinding.DrawerItemBinding
import com.example.shopapp.models.MenuItem

typealias menuClickListener = (count: Int) -> Unit
class DrawerMenuAdapter(private val items: Array<MenuItem>): RecyclerView.Adapter<DrawerMenuAdapter.ViewHolder>() {

    lateinit var menuClickListener: menuClickListener

    inner class ViewHolder(private val binding: DrawerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: MenuItem
        fun bind() {
            model = items[adapterPosition]
            binding.menuTitle.text = model.title
            binding.root.setOnClickListener {
                menuClickListener.invoke(model.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DrawerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = items.size
}