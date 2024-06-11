package br.com.espaoculturalunifor.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.client.ui.activity.obras.ObraActivity
import br.com.client.ui.activity.quiz.MainActivity
import br.com.espaoculturalunifor.databinding.ActivityMainClientBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityOpcoesAdmObras.setOnClickListener(){
            val intent = Intent(this@MainActivity, ObraActivity::class.java)
            startActivity(intent)
        }

        binding.activityOpcoesAdmQuiz.setOnClickListener{
            val intent = Intent (this@MainActivity, MainActivity::class.java)
            startActivity(intent)
        }


    }
}