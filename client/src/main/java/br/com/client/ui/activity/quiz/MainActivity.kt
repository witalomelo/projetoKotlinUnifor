package br.com.client.ui.activity.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.client.databinding.ActivityMain2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            val userName = binding.etName.text.toString()
            databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Jogadores")
            val jogadores = Jogador(userName, score = null)
            databaseReference.child(userName).setValue(jogadores)

            val intent = Intent(this, QuizQuestionsActivity::class.java)
            intent.putExtra("userName", userName) // Passando o userName como extra
//            Log.d("PlayerName", "Nome do jogador recebido: $userName")
            startActivity(intent)
            finish()

        }
    }
}