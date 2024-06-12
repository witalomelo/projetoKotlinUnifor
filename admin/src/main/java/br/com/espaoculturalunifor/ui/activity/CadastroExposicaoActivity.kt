package br.com.espaoculturalunifor.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.espaoculturalunifor.R
import br.com.espaoculturalunifor.data.ExposicaoData
import br.com.espaoculturalunifor.databinding.ActivityCadastroExposicaoBinding
import br.com.espaoculturalunifor.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CadastroExposicaoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroExposicaoBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroExposicaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //cadastrar
        binding.buttonCadastrarExposicao.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val data = binding.editData.text.toString()
            val descricao = binding.editDescricao.text.toString()

            databaseReference =
                FirebaseDatabase.getInstance().getReference("Diretorio de Exposicao")
            val exposicoes = ExposicaoData(nome, data, descricao)
            databaseReference.child(nome).setValue(exposicoes).addOnSuccessListener {
                binding.editNome.text.clear()
                binding.editData.text.clear()
                binding.editDescricao.text.clear()

                Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@CadastroExposicaoActivity, OpcoesActivity::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        //editar
        binding.buttonCadastrarEditar.setOnClickListener {
            val referenciaNome = binding.editNome.text.toString()
            val data = binding.editData.text.toString()
            val descricao = binding.editDescricao.text.toString()

            updateExposicao(referenciaNome, data, descricao)
        }

        //deletar
        binding.buttonCadastrarDelete.setOnClickListener {
            val nome = binding.editNome.text.toString()
            if (nome.isNotEmpty()) {
                deletaExposicao(nome)

                val intent = Intent(this@CadastroExposicaoActivity, OpcoesActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Insira um nome valido", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun deletaExposicao(nome: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Exposicao")
        databaseReference.child(nome).removeValue().addOnSuccessListener {
            binding.editNome.text.clear()
            Toast.makeText(this, "Deletado", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

        }
    }


    private fun updateExposicao(referenciaNome: String, data: String, descricao: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Exposicao")
        val exposicao = mapOf<String, String>(
            "nome" to referenciaNome,
            "data" to data,
            "descricao" to descricao
        )
        databaseReference.child(referenciaNome).updateChildren(exposicao).addOnSuccessListener {
            binding.editNome.text.clear()
            binding.editData.text.clear()
            binding.editDescricao.text.clear()

            Toast.makeText(this, "Editado com sucesso", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

        }

    }


}