package com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.chatbot.Message
import com.example.healthtrackerpraksa.util.QUESTION_FLAG

class ChatBotMainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var chatMessages = listOf<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            QUESTION_FLAG -> {
                ChatBotQuestionViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.card_chat_bot_question, parent, false))
            }
            else -> {
                ChatBotAnswerViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.card_chat_bot_answer, parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = chatMessages[position]
        return item.messageTypeFlag
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatMessage = chatMessages[position]
        when (holder) {
            is ChatBotQuestionViewHolder -> holder.chatMessage.text = chatMessage.message
            is ChatBotAnswerViewHolder -> holder.chatMessage.text = chatMessage.message
        }
    }

    override fun getItemCount(): Int {
        return chatMessages.size
    }

    fun updateChatMessages(messages: List<Message>) {
        chatMessages = messages
        notifyDataSetChanged()
    }
}

class ChatBotAnswerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val chatMessage: TextView = view.findViewById(R.id.tv_card_chat_bot_answer)
}

class ChatBotQuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val chatMessage: TextView = view.findViewById(R.id.tv_card_chat_bot_question)
}
