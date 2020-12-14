package com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
    public abstract fun setData(position:Int, data: String)
}