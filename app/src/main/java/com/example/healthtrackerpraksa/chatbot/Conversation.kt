package com.example.healthtrackerpraksa.chatbot

class Conversation() {
    private val conversation = mutableListOf<Message>()

    fun addMessage(message: Message){
        conversation.add(message)
    }

    fun getConversation(): List<Message>{
        return conversation
    }
}


