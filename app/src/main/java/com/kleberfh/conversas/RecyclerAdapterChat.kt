package com.kleberfh.conversas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapterChat(
    private val list: List<ItemChat>,
    private val listener: MainActivity,
) : RecyclerView.Adapter<RecyclerAdapterChat.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_chat, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        holder.username.text = currentItem.username
        holder.age.text = currentItem.age.toInt().toString()
        holder.city.text = currentItem.city
        holder.gender.text = currentItem.gender
        holder.sexualOrientation.text = currentItem.sexualOrientation
        Glide.with(holder.image).load(currentItem.photo).into(holder.image)
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
        val age: TextView = itemView.findViewById(R.id.chat_age)
        val city: TextView = itemView.findViewById(R.id.chat_city)
        val image: ImageView = itemView.findViewById(R.id.chat_image)
        val gender: TextView = itemView.findViewById(R.id.chat_gender)
        val username: TextView = itemView.findViewById(R.id.chat_username)
        val sexualOrientation: TextView = itemView.findViewById(R.id.chat_sexual_orientation)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
