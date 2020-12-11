package com.example.healthtrackerpraksa.repositories

import com.example.healthtrackerpraksa.MyApplication
import com.example.healthtrackerpraksa.chatbot.Question
import com.google.gson.Gson

class ChatBotRepository {

    private val assetManager = MyApplication.instance.assets
    private val reader = assetManager.open("chatbotjson.json").bufferedReader().readText()
    private val gson = Gson()
    private val questions: List<Question> =
        gson.fromJson(reader, Array<Question>::class.java).toList()
    private val questionsMap: HashMap<String, Question> = HashMap()

    init {
        createQuestionsHashMap(questions, questionsMap)
    }

    private fun createQuestionsHashMap(
        questions: List<Question>,
        questionsMap: HashMap<String, Question>
    ) {
        questions.forEach { question ->
            question.initMap()
            questionsMap[question.id] = question
        }
    }

    fun getChatBotData(): HashMap<String, Question> {
        return questionsMap
    }
}