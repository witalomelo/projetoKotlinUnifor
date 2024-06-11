package br.com.client.ui.activity.quiz

import br.com.client.models.Pergunta
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object Constants {
    const val USER_NAME = "user_name"
    val TOTAL_QUESTIONS: String = "total_questions"
    val SCORE: String = "score"



    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Perguntas")

    fun getQuestions(callback: (ArrayList<Pergunta>) -> Unit) {
        val questionsList = ArrayList<Pergunta>()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                questionsList.clear()
                for (dataSnapshot in snapshot.children) {
                    val pergunta = dataSnapshot.getValue(Pergunta::class.java)
                    if (pergunta != null) {
                        questionsList.add(pergunta)
                    }
                }
                callback(questionsList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors.
            }
        })

    }

}
