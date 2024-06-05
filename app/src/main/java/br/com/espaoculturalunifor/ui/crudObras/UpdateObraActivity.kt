package br.com.espaoculturalunifor.ui.crudObras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.espaoculturalunifor.ui.activity.OpcoesActivity
import br.com.espaoculturalunifor.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateObraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener{
            val referenceObra = binding.referenceNome.text.toString()
            val updateAutor = binding.updateAutor.text.toString()
            val updateData = binding.updateData.text.toString()
            val updateDescricao = binding.updateDescricao.text.toString()
            val updateImage = binding.updateImage.text.toString()

            updateData(referenceObra, updateAutor,updateData,updateDescricao,updateImage)

            val intent = Intent(this@UpdateObraActivity, OpcoesActivity::class.java)
            startActivity(intent)
            finish()


        }
    }

    private fun updateData(nome: String, autor: String, data: String, descricao: String, image: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Obras")
        val obra = mapOf<String,String>("nome" to nome, "autor" to autor, "data" to data, "descricao" to descricao, "imagem" to image)
        databaseReference.child(nome).updateChildren(obra).addOnSuccessListener {
            binding.referenceNome.text.clear()
            binding.updateAutor.text.clear()
            binding.updateData.text.clear()
            binding.updateDescricao.text.clear()
            binding.updateImage.text.clear()

            Toast.makeText(this, "Alterado com sucesso", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
        }

    }
}