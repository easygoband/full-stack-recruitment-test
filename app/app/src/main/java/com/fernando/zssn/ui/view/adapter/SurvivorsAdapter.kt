package com.fernando.zssn.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fernando.zssn.databinding.RowSurvivorItemBinding
import com.fernando.zssn.data.model.Survivor

class SurvivorsAdapter(
    var survivors: List<Survivor>,
    private val survivorCLickListener: (Survivor) -> Unit
) : RecyclerView.Adapter<SurvivorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowSurvivorItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    fun filterList(filterList: List<Survivor>) {
        survivors = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val survivor = survivors[position]
        holder.bind(survivor,position.toString())
        holder.itemView.setOnClickListener{ survivorCLickListener(survivor) }
    }

    override fun getItemCount() = survivors.size

    class ViewHolder(private val binding: RowSurvivorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(survivor: Survivor, position: String) {
            binding.subTxt.text = survivor.surname
            binding.txt.text = survivor.name

            val url = when(position) {
                "0" -> "https://ui-avatars.com/api/?name=1&background=random"
                "1" -> "https://ui-avatars.com/api/?name=2&background=random"
                "2" -> "https://ui-avatars.com/api/?name=3&background=random"
                else -> "https://ui-avatars.com/api/?name="+survivor.name+"+"+survivor.surname+"&background=random"
            }
            Glide
                .with(binding.root.context)
                .load(url)
                .circleCrop()
                .into(binding.img)

        }
    }
}