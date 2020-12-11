package com.example.healthtrackerpraksa.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.chatbot.Conversation
import com.example.healthtrackerpraksa.chatbot.Message
import com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters.ChatBotAnswersAdapter
import com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters.ChatBotMainAdapter
import com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters.IOnAnswerClickListener
import com.example.healthtrackerpraksa.chatbot.Question
import com.example.healthtrackerpraksa.ui.viewmodels.ChatBotViewModel
import com.example.healthtrackerpraksa.util.ANSWER_FLAG
import com.example.healthtrackerpraksa.util.CONVERSATION_OVER
import com.example.healthtrackerpraksa.util.QUESTION_FLAG
import kotlinx.android.synthetic.main.activity_chat_bot.*

class ChatBotActivity : AppCompatActivity(), IOnAnswerClickListener {

    private val chatBotViewModel: ChatBotViewModel by viewModels()
    private lateinit var questions: HashMap<String, Question>
    private val chatBotAdapter = ChatBotMainAdapter()
    private val chatBotAnswersAdapter = ChatBotAnswersAdapter()
    private val conversation = Conversation()
    private lateinit var question: Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)

        subscribeObserver()
        chatBotViewModel.initCBLiveData()
        setupAndInitRVs()
        chatBotAnswersAdapter.setOnAnswerClickListener(this)
    }

    private fun setupAndInitRVs() {
        rv_chat_bot.apply {
            layoutManager =
                LinearLayoutManager(this@ChatBotActivity, LinearLayoutManager.VERTICAL, false)
            adapter = chatBotAdapter
        }
        rv_chat_bot_answers.apply {
            layoutManager =
                LinearLayoutManager(this@ChatBotActivity, LinearLayoutManager.VERTICAL, false)
            adapter = chatBotAnswersAdapter
        }
    }

    private fun initChat(chatBotAdapter: ChatBotMainAdapter) {
        question = questions["Q0"]!!
        conversation.addMessage(Message(question.value, QUESTION_FLAG))
        chatBotAdapter.updateChatMessages(conversation.getConversation())
        chatBotAnswersAdapter.updateAnswers(question.answersMap)
    }

    private fun subscribeObserver() {
        chatBotViewModel.chatBotLiveData.observe(this, { questions ->
            this.questions = questions
            initChat(chatBotAdapter)
        })
    }

    override fun onAnswerClicked(nextQuestionId: String, message: String) {
        if (nextQuestionId != CONVERSATION_OVER) {
            question = questions[nextQuestionId]!!
            conversation.apply {
                addMessage(Message(message, ANSWER_FLAG))
                addMessage(Message(question.value, QUESTION_FLAG))
            }
            chatBotAdapter.updateChatMessages(conversation.getConversation())
            chatBotAnswersAdapter.updateAnswers(question.answersMap)
        } else {
            conversation.apply {
                addMessage(Message(message, ANSWER_FLAG))
                addMessage(Message(CONVERSATION_OVER, QUESTION_FLAG))
            }
            chatBotAdapter.updateChatMessages(conversation.getConversation())
            chatBotAnswersAdapter.updateAnswers(HashMap())
        }
    }

}