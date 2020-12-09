package com.example.healthtrackerpraksa.chatbot

class Question
    (
    val id: String,
    val value: String,
    val answers: List<Answer>
) {
    val answersHashMap = HashMap<String, Answer>()

    init {
        answers.forEach {
            answersHashMap.put(it.id, it)
        }
    }
}
