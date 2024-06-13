package br.com.client

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.client.databinding.ActivityMainBinding
import br.com.client.ui.activity.exposicao.ExposicaoActivity
import br.com.client.ui.activity.obras.ObraActivity
import br.com.client.ui.activity.quiz.MainActivity

class MainActivity : AppCompatActivity() {

    protected lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityOpcoesAdmObras.setOnClickListener{
            val intent = Intent(this@MainActivity, ObraActivity::class.java)
            startActivity(intent)
        }

        binding.activityOpcoesAdmQuiz.setOnClickListener{
            val intent = Intent (this@MainActivity, MainActivity::class.java)
            startActivity(intent)
        }

//        binding.activityOpcoesAdmExposicao.setOnClickListener{
//            val intent = Intent (this@MainActivity, ExposicaoActivity::class.java)
//            startActivity(intent)
//        }


    }
}