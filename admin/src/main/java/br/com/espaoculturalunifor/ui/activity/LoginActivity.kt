package br.com.espaoculturalunifor.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.espaoculturalunifor.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCadastrarAdm.setOnClickListener {
            val intent = Intent(this@LoginActivity, CadastroActivty::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonCadastrar.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email == "admin" && senha == "admin") {
                val intent = Intent(this@LoginActivity, OpcoesActivity::class.java)
                startActivity(intent)
                finish()
            }

        }


    }
}