package com.example.healthtrackerpraksa.ui.activities.adapters.chatbotadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.chatbot.Answer
import com.example.healthtrackerpraksa.util.CONVERSATION_OVER

class ChatBotAnswersAdapter :
    RecyclerView.Adapter<ChatBotAnswersAdapter.ChatBotAnswerViewHolder>() {

    private var answers: HashMap<Int, Answer> = HashMap()
    private var onAnswerClickListener: IOnAnswerClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatBotAnswerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_chat_bot_possible_answers, parent, false)
        return ChatBotAnswerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatBotAnswerViewHolder, position: Int) {
        val answer = answers[position]!!
        holder.answer.text = answer.value
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    //------------------------------------------------//

    fun updateAnswers(answers: HashMap<Int, Answer>) {
        this.answers = answers
        notifyDataSetChanged()
    }

    fun setOnAnswerClickListener(onAnswerClickListener: IOnAnswerClickListener){
        this.onAnswerClickListener = onAnswerClickListener
    }

    inner class ChatBotAnswerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val answer: TextView = view.findViewById(R.id.tv_possible_answer)

        init {
            answer.setOnClickListener {
                val answer = answers[this.adapterPosition]
                if (answer!!.nextQuestionId.isNotEmpty()){
                    onAnswerClickListener?.onAnswerClicked(answer.nextQuestionId, answer.value)
                } else onAnswerClickListener?.onAnswerClicked(CONVERSATION_OVER, answer.value)

            }
        }
    }
}

interface IOnAnswerClickListener{
    fun onAnswerClicked(nextQuestionId: String, message: String)
}




