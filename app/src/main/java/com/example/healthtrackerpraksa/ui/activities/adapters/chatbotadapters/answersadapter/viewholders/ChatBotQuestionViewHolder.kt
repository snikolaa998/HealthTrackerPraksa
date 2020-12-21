package com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters.answersadapter.viewholders

import android.view.View
import android.widget.TextView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.chatbot.Message

class ChatBotQuestionViewHolder(view: View) : MainViewHolder(view) {
    private val chatMessage: TextView = view.findViewById(R.id.tv_card_chat_bot_question)

    override fun setData(data: Message) {
        chatMessage.text = data.message
    }
}