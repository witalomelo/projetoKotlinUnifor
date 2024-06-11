package br.com.espaoculturalunifor.ui.crudQuiz

import android.content.Intent
import android.os.BaseBundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.espaoculturalunifor.R
import br.com.espaoculturalunifor.databinding.ActivityMainQuizBinding
import br.com.espaoculturalunifor.databinding.ActivityUpdateBinding
import br.com.espaoculturalunifor.databinding.ActivityUpdateQuizBinding
import br.com.espaoculturalunifor.ui.activity.OpcoesActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateQuizBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val referenceId = binding.referenceId.text.toString()
            val updatePergunta = binding.updatePergunta.text.toString()
            val updateResposta = binding.updateResposta.text.toString()

            updateData(
                referenceId,
                updatePergunta,
                updateResposta
            )

            val intent = Intent(this@UpdateQuizActivity, MainActivityQuiz::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateData(
        id: String,
        pergunta: String,
        resposta: String,
    ) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Perguntas")
        val pergunta = mapOf<String, String>(
            "id" to id,
            "pergunta" to pergunta,
            "resposta" to resposta
        )
        databaseReference.child(id).updateChildren(pergunta).addOnSuccessListener {
            binding.referenceId.text.clear()
            binding.updatePergunta.text.clear()
            binding.updateResposta.text.clear()

            Toast.makeText(this, "Alterado com sucesso", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
        }
    }
}