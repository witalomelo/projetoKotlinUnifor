package br.com.espaoculturalunifor.ui.crudObras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.espaoculturalunifor.ui.activity.OpcoesActivity
import br.com.espaoculturalunifor.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteObraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener{
            val nome = binding.deleteObra.text.toString()
            if(nome.isNotEmpty()){
                deleteData(nome)
                val intent = Intent(this, OpcoesActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Insira o nome da obra", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun deleteData(nome: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Obras")
        databaseReference.child(nome).removeValue().addOnSuccessListener {
            Toast.makeText(this, "Deletado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Digite um nome válido", Toast.LENGTH_SHORT).show()
        }

    }
}