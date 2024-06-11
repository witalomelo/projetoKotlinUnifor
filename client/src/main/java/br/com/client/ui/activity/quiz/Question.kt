package br.com.client.ui.activity.quiz

data class Question(
    val id: Int,
    val questionText: String,
    val alternatives: ArrayList<String>,
    val correctAnswerIndex: Int

)
