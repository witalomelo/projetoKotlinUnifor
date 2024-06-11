package br.com.espaoculturalunifor.ui.crudObras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.espaoculturalunifor.data.ObraData
import br.com.espaoculturalunifor.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadObraActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            val nome = binding.uploadNome.text.toString()
            val autor = binding.uploadAutor.text.toString()
            val data = binding.uploadData.text.toString()
            val descricao = binding.uploadDescricao.text.toString()
            val imagem = binding.uploadImagem.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Obras")
            val obras = ObraData(nome, autor, data,descricao, imagem)
            databaseReference.child(nome).setValue(obras).addOnSuccessListener {
                binding.uploadNome.text.clear()
                binding.uploadAutor.text.clear()
                binding.uploadData.text.clear()
                binding.uploadDescricao.text.clear()
                binding.uploadImagem.text.clear()

                Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@UploadObraActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}