package com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters.answersadapter.viewholders

import android.view.View
import android.widget.TextView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.chatbot.Message

class ChatBotAnswerViewHolder(view: View, onClick: (String) -> Unit) :
    MainViewHolder(view) {
    private val chatMessage: TextView = view.findViewById(R.id.tv_card_chat_bot_answer)

    init {
        chatMessage.setOnClickListener {
            onClick(chatMessage.text.toString())
        }
    }

    override fun setData(data: Message) {
        chatMessage.text = data.message

    }
}