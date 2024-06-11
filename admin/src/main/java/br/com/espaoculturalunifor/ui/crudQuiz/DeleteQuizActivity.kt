package br.com.espaoculturalunifor.ui.crudQuiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.espaoculturalunifor.R
import br.com.espaoculturalunifor.databinding.ActivityDeleteBinding
import br.com.espaoculturalunifor.databinding.ActivityDeleteQuizBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteQuizBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val id = binding.deleteId.text.toString()
            if (id.isNotEmpty()) {
                deleteData(id)
                val intent = Intent(this, MainActivityQuiz::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun deleteData(id: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Perguntas")
        databaseReference.child(id).removeValue().addOnSuccessListener {
            Toast.makeText(this, "Deletado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }
}