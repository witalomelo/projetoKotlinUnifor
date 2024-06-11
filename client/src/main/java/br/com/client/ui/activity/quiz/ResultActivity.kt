package br.com.client.ui.activity.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.client.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("userName").toString()
        Log.d("Nome_Result", "Nome do jogador recebido: $nome")

        val totalScore = intent.getIntExtra("totalScore", 0)

        binding.congratulationsTv.text = "Parabéns, $nome!"
        binding.scoreTv.text = "Seu score foi de $totalScore"

        binding.btnRestart.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}