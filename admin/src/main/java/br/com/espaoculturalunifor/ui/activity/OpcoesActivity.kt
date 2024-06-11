package br.com.espaoculturalunifor.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.espaoculturalunifor.databinding.ActivityOpcoesBinding
import br.com.espaoculturalunifor.ui.crudObras.MainActivity
import br.com.espaoculturalunifor.ui.crudQuiz.MainActivityQuiz

class OpcoesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpcoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpcoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityOpcoesAdmObras.setOnClickListener{
            val intent = Intent(this@OpcoesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.activityOpcoesAdmQuiz.setOnClickListener{
            val intent = Intent(this@OpcoesActivity, MainActivityQuiz::class.java)
            startActivity(intent)
            finish()
        }

    }
}