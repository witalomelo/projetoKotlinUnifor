package br.com.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.Toast
import br.com.client.databinding.ActivityMainBinding
import br.com.client.ui.activity.obras.ObraActivity
import br.com.client.ui.activity.quiz.MainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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


    }
}