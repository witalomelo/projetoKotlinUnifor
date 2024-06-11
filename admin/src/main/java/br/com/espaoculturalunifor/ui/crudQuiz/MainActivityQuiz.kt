package br.com.espaoculturalunifor.ui.crudQuiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.espaoculturalunifor.databinding.ActivityMainQuizBinding
import br.com.espaoculturalunifor.ui.activity.LoginActivity

class MainActivityQuiz : AppCompatActivity() {

    private lateinit var binding: ActivityMainQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener{
            val intent = Intent(this@MainActivityQuiz, UploadQuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainUpdate.setOnClickListener{
            val intent = Intent(this@MainActivityQuiz, UpdateQuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainDelete.setOnClickListener{
            val intent = Intent(this@MainActivityQuiz, DeleteQuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainSair.setOnClickListener{
            val intent = Intent(this@MainActivityQuiz, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}