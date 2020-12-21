package com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters.answersadapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.chatbot.Message
import com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters.answersadapter.viewholders.ViewHolderProvider.makeViewHolder
import com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters.answersadapter.viewholders.MainViewHolder

class ChatBotMainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var chatMessages = listOf<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return makeViewHolder(viewType, parent, ::whenClicked)
    }

    override fun getItemViewType(position: Int): Int {
        val item = chatMessages[position]
        return item.messageTypeFlag
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setData(chatMessages[position])
    }

    override fun getItemCount(): Int {
        return chatMessages.size
    }

    fun updateChatMessages(messages: List<Message>) {
        chatMessages = messages
        notifyDataSetChanged()
    }

    fun whenClicked(message: String) {
        Log.i("ADAPTER_TESTING", "whenClicked: ${message} ")
    }
}





