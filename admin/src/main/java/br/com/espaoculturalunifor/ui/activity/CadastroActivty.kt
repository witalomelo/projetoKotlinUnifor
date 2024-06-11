package br.com.espaoculturalunifor.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.espaoculturalunifor.data.AdmData
import br.com.espaoculturalunifor.databinding.ActivityCadastroAdmBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CadastroActivty : AppCompatActivity() {


    private lateinit var binding: ActivityCadastroAdmBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroAdmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCadastrarAdm.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val matricula = binding.editMatricula.text.toString()
            val senha = binding.editSenha.text.toString()

            databaseReference =
                FirebaseDatabase.getInstance().getReference("Diretorio de Administradores")
            val administradores = AdmData(nome, matricula, senha)

            databaseReference.child(matricula).setValue(administradores).addOnSuccessListener {
                binding.editNome.text.clear()
                binding.editMatricula.text.clear()
                binding.editSenha.text.clear()

                Toast.makeText(this, "Cadastrado", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@CadastroActivty, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Falhou", Toast.LENGTH_SHORT).show()
            }



        }

    }
}