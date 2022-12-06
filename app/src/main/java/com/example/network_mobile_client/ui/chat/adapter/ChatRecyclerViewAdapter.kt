package com.example.network_mobile_client.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network_mobile_client.data.ChatMessageData
import com.example.network_mobile_client.databinding.ChatMessageLayoutBinding
import com.example.network_mobile_client.databinding.FragmentChatBinding
import java.util.Calendar


class ChatRecyclerViewAdapter(
    private val binding: FragmentChatBinding,
    private val data: MutableList<ChatMessageData>
) :
    RecyclerView.Adapter<ChatRecyclerViewAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ChatMessageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun addChatData(message: String, calendar: Calendar) {
        val dateString = "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
        data.add(ChatMessageData(message, dateString))
    }

    inner class DataViewHolder(private val binding: ChatMessageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ChatMessageData) {
            binding.txtMessage.text = data.message
            binding.txtDate.text = data.date
        }
    }
}
