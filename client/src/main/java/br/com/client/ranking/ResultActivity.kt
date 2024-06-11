package br.com.client.ranking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.client.databinding.ActivityResultBinding
import br.com.client.ui.activity.quiz.MainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("userName").toString()
        val totalScore = intent.getIntExtra("totalScore", 0)

        binding.congratulationsTv.text = "Parabéns, $nome!"
        binding.scoreTv.text = "Seu score foi de $totalScore"

        updateScore(nome, totalScore)

        binding.btnRanking.setOnClickListener{
            val intent = Intent(this@ResultActivity, RankingActivity::class.java)
            startActivity(intent)
        }

        binding.btnRestart.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun updateScore(nome: String, totalScore: Int) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Jogadores")
        val jogador = mapOf<String, Any>("userName" to nome, "score" to totalScore)
        databaseReference.child(nome).updateChildren(jogador).addOnCompleteListener{task ->
            if(task.isSuccessful){
                Log.d("Firebase", "Score atualizado com sucesso.")
            }else {
                Log.e("Firebase", "Erro ao atualizar o score.", task.exception)
            }
        }
    }

}