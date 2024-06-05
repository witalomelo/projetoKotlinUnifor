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
            val updateItemA = binding.updateItemA.text.toString()
            val updateItemB = binding.updateItemB.text.toString()
            val updateItemC = binding.updateItemC.text.toString()
            val updateItemD = binding.updateItemD.text.toString()
            val updateItemCorreto = binding.updateItemCorreto.text.toString()

            updateData(
                referenceId,
                updatePergunta,
                updateItemA,
                updateItemB,
                updateItemC,
                updateItemD,
                updateItemCorreto
            )

            val intent = Intent(this@UpdateQuizActivity, MainActivityQuiz::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateData(
        id: String,
        pergunta: String,
        itemA: String,
        itemB: String,
        itemC: String,
        itemD: String,
        itemCorreto: String
    ) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Perguntas")
        val pergunta = mapOf<String, String>(
            "id" to id,
            "pergunta" to pergunta,
            "itemA" to itemA,
            "itemB" to itemB,
            "itemC" to itemC,
            "itemD" to itemD,
            "itemCorreto" to itemCorreto
        )
        databaseReference.child(id).updateChildren(pergunta).addOnSuccessListener {
            binding.referenceId.text.clear()
            binding.updatePergunta.text.clear()
            binding.updateItemA.text.clear()
            binding.updateItemB.text.clear()
            binding.updateItemC.text.clear()
            binding.updateItemD.text.clear()
            binding.updateItemCorreto.text.clear()

            Toast.makeText(this, "Alterado com sucesso", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
        }
    }
}