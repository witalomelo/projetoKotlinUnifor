package br.com.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import br.com.client.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener{
            val searchObra : String = binding.searchObra.text.toString()
            if(searchObra.isNotEmpty()){
                readData(searchObra)
            }
        }
    }

    private fun readData(obra: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Obras")
        databaseReference.child(obra).get().addOnSuccessListener {
            if(it.exists()){
                val autor = it.child("autor").value
                val data = it.child("data").value
                val imagem = it.child("imagem").value

                Toast.makeText(this, "Resultados Encontrados", Toast.LENGTH_SHORT).show()

                binding.searchObra.text.clear()
                binding.readAutor.text = autor.toString()
                binding.readData.text = data.toString()
                binding.readImagem.text = imagem.toString()

            } else {
                Toast.makeText(this, "Obra não encontrada", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
        }
    }
}