package com.fernando.zssn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fernando.zssn.databinding.RowSurvivorItemBinding
import com.fernando.zssn.model.Survivor

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val survivor = survivors[position]
        holder.bind(survivor)
        holder.itemView.setOnClickListener{ survivorCLickListener(survivor) }
    }

    override fun getItemCount() = survivors.size

    class ViewHolder(private val binding: RowSurvivorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(survivor: Survivor) {
            binding.txt.text = survivor.name
            binding.subTxt.text = survivor.surname
            Glide
                .with(binding.root.context)
                .load("https://ui-avatars.com/api/?name=Luis+Fernando&background=random")
                .circleCrop()
                .into(binding.img)

        }
    }
}