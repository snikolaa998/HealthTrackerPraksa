package com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters.answersadapter.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.chatbot.Message

abstract class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun setData( data: Message)
}