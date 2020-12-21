package com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters.answersadapter.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.util.ANSWER_FLAG

object ViewHolderProvider {

    fun makeViewHolder(
        messageType: Int,
        parent: ViewGroup,
        onClick: (String) -> Unit
    ): MainViewHolder {
        return when (messageType) {
            ANSWER_FLAG -> ChatBotAnswerViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_chat_bot_answer, parent, false),
                onClick
            )
            else -> ChatBotQuestionViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_chat_bot_question, parent, false)
            )
        }
    }
}