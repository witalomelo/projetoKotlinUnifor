package br.com.client.ui.activity.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.client.R
import br.com.client.models.Pergunta
import br.com.client.ranking.ResultActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class QuizQuestionsActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    private var userName: String? = null
    private var questionsList: ArrayList<Pergunta> = arrayListOf()
    private var currentQuestionIndex = 0
    private var selectedAlternativeIndex = -1
    private var isAnswerChecked = false
    private var totalScore = 0

    private var tvQuestion: TextView? = null
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var btnSubmit: Button? = null
    private var tvAlternatives: ArrayList<TextView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        tvQuestion = findViewById(R.id.tvQuestion)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvAlternatives = arrayListOf(
            findViewById(R.id.opcaoVerdadeira),
            findViewById(R.id.opcaoFalsa)
        )

        userName = intent.getStringExtra("userName")
        Log.d("PlayerName", "Nome do jogador recebido: $userName")

        fetchQuestions()

        btnSubmit?.setOnClickListener {
            if (!isAnswerChecked) {
                val anyAnswerIsChecked = selectedAlternativeIndex != -1

                // verifica se alguma alternativa foi selecionada
                if (!anyAnswerIsChecked) {
                    Toast.makeText(this, "Selecione uma alternativa", Toast.LENGTH_SHORT).show()
                } else {
                    //indice da questão atual na lista de questao
                    val currentQuestion = questionsList[currentQuestionIndex]

                    //verifica se a alternativa selecionada é correta
                    if (selectedAlternativeIndex == currentQuestion.resposta.toInt() - 1) {
                        answerView(
                            tvAlternatives!![selectedAlternativeIndex],
                            R.drawable.correct_option_border_bg
                        )
                        totalScore++
                    } else {
                        answerView(
                            tvAlternatives!![selectedAlternativeIndex],
                            R.drawable.wrong_option_border_bg
                        )
                        answerView(
                            tvAlternatives!![currentQuestion.resposta.toInt() - 1],
                            R.drawable.correct_option_border_bg
                        )
                    }

                    isAnswerChecked = true

                    //logica do btn
                    btnSubmit?.text =
                        if (currentQuestionIndex == questionsList.size - 1) "FINALIZAR" else "PRÓXIMA QUESTÃO"
                    selectedAlternativeIndex = -1
                }
            } else {
                //logica das perguntas
                if (currentQuestionIndex < questionsList.size - 1) {
                    currentQuestionIndex++
                    updateQuestion()
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("userName", userName);
                    intent.putExtra("totalScore", totalScore)
                    startActivity(intent)
                    finish()
//                    fetchQuestions()
                }

                isAnswerChecked = false
                btnSubmit?.text = "ENVIAR"
            }
        }

        tvAlternatives?.let {
            for (optionIndex in it.indices) {
                it[optionIndex].let {
                    it.setOnClickListener { //configurando clique em cada alternativa
                        if (!isAnswerChecked) { //verifica resposta selecionada
                            selectedAlternativeView(it as TextView, optionIndex)
                        }
                    }
                }
            }
        }
    }

    //buscar os dados do firebase
    private fun fetchQuestions() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Perguntas")
        //adicionando listener sempre que houver mudança no diretorio de perguntas
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                questionsList.clear() //garantir que não haverá dados duplicados
                for (dataSnapshot in snapshot.children) {
                    //setando cada valor do banco
                    val id = dataSnapshot.child("id").getValue(String::class.java)
                    val pergunta = dataSnapshot.child("pergunta").getValue(String::class.java)
                    val resposta = dataSnapshot.child("resposta").getValue(String::class.java)

                    if (id != null && pergunta != null && resposta != null) {
                        val perguntaObj = Pergunta(id, pergunta, resposta)
                        questionsList.add(perguntaObj)
                    }
                }
                //verificando se a lista não esta vazia antes de executar updateQuestion()
                if (questionsList.isNotEmpty()) {
                    currentQuestionIndex = 0
                    updateQuestion()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@QuizQuestionsActivity,
                    "Falha ao carregar questões",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    //logica para definir a aparencia padrão do item exceto a alternativa selecionada
    private fun defaultAlternativesView() {
        for (alternativeIndex in tvAlternatives!!.indices) {
            val alternativeTv = tvAlternatives!![alternativeIndex]

            if (alternativeIndex == selectedAlternativeIndex) {
                // Se esta alternativa estiver selecionada, não altere sua aparência
                continue
            }

            //fonte padrao
            alternativeTv.typeface = Typeface.DEFAULT

            //cor do texto
            alternativeTv.setTextColor(Color.parseColor("#7A8089"))

            //fundo
            alternativeTv.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    //atualizando perguntas
    private fun updateQuestion() {

        defaultAlternativesView()

        // logica para redirecionar para a tela resultados
        if (finishQuestionario()) return

        val currentQuestion = questionsList[currentQuestionIndex]

        // Renderizar o Texto da Pergunta
        tvQuestion?.text = currentQuestion.pergunta

        // Atualizar ProgressBar
        progressBar?.max = questionsList.size
        progressBar?.progress = currentQuestionIndex + 1

        // Atualizar  ProgressBar
        tvProgress?.text = "${currentQuestionIndex + 1}/${questionsList.size}"

        // Atualizar Texto do Botão Enviar
        btnSubmit?.text =
            if (currentQuestionIndex == questionsList.size - 1) "FINALIZAR" else "ENVIAR"


    }

    private fun finishQuestionario(): Boolean {
        if (currentQuestionIndex >= questionsList.size) {
            val userName = intent.putExtra("userName", userName).toString()
            val score = intent.putExtra("score", totalScore).toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Jogadores")

            val jogadores = mapOf<String, Any>( "score" to score)

            databaseReference.child(userName).updateChildren(jogadores).addOnCompleteListener{
                task ->
                if (task.isSuccessful){
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("userName", userName)
                    intent.putExtra("totalScore", totalScore)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao atualizar o score: ${task.exception?.message}", Toast.LENGTH_SHORT).show()

                }
            }
            return true
        }
        return false
    }

    private fun selectedAlternativeView(option: TextView, index: Int) {
        defaultAlternativesView()
        selectedAlternativeIndex = index

        option.setTextColor(Color.parseColor("#363A43"))
        option.setTypeface(option.typeface, Typeface.BOLD)
        option.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(view: TextView, drawableId: Int) {
        view.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            drawableId
        )
        view.setTextColor(Color.parseColor("#FFFFFF"))
    }
}
