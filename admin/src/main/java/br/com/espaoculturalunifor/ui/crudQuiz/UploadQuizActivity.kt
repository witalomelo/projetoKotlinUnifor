package br.com.espaoculturalunifor.ui.crudQuiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Toast
import br.com.espaoculturalunifor.R
import br.com.espaoculturalunifor.data.PerguntaData
import br.com.espaoculturalunifor.databinding.ActivityDeleteBinding
import br.com.espaoculturalunifor.databinding.ActivityUploadQuizBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadQuizBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val id = binding.uploadId.text.toString()
            val pergunta = binding.uploadPergunta.text.toString()
            val resposta = binding.uploadResposta.text.toString()

            databaseReference =
                FirebaseDatabase.getInstance().getReference("Diretorio de Perguntas")
            val perguntas = PerguntaData(id, pergunta, resposta)
            databaseReference.child(id).setValue(perguntas).addOnSuccessListener {
                binding.uploadId.text.clear()
                binding.uploadPergunta.text.clear()
                binding.uploadResposta.text.clear()

                Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@UploadQuizActivity, MainActivityQuiz::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}