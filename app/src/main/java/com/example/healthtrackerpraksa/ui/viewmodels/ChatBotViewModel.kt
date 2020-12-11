package com.example.healthtrackerpraksa.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthtrackerpraksa.chatbot.Question
import com.example.healthtrackerpraksa.repositories.ChatBotRepository

class ChatBotViewModel: ViewModel() {

    private val chatBotRepository = ChatBotRepository()

    private val _chatBotLiveData = MutableLiveData<HashMap<String, Question>>()
        val chatBotLiveData: LiveData<HashMap<String,Question>>
            get() = _chatBotLiveData


    fun initCBLiveData(){
        _chatBotLiveData.value = chatBotRepository.getChatBotData()
    }
}