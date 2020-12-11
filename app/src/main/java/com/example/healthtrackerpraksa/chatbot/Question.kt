package com.example.healthtrackerpraksa.chatbot

class Question
@JvmOverloads constructor(
    val id: String = "",
    val value: String = "",
    val answers:List<Answer>  = listOf()
) {
    val answersMap = HashMap<Int, Answer>()


    fun initMap(){
        answers.forEach {
            answersMap[it.id] = it
        }
    }
 }
