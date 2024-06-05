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
            val itemA = binding.uploadItemA.text.toString()
            val itemB = binding.uploadItemB.text.toString()
            val itemC = binding.uploadItemC.text.toString()
            val itemD = binding.uploadItemD.text.toString()
            val itemCorreto = binding.uploadItemCorreto.text.toString()

            databaseReference =
                FirebaseDatabase.getInstance().getReference("Diretorio de Perguntas")
            val perguntas = PerguntaData(id, pergunta, itemA, itemB, itemC, itemD, itemCorreto)
            databaseReference.child(id).setValue(perguntas).addOnSuccessListener {
                binding.uploadId.text.clear()
                binding.uploadPergunta.text.clear()
                binding.uploadItemA.text.clear()
                binding.uploadItemB.text.clear()
                binding.uploadItemC.text.clear()
                binding.uploadItemD.text.clear()
                binding.uploadItemCorreto.text.clear()

                Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@UploadQuizActivity, MainActivityQuiz::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}