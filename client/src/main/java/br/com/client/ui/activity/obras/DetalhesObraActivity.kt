package br.com.client.ui.activity.obras

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.client.databinding.ActivityDetalhesObraBinding
import br.com.client.extensions.tentaCarregarImagem
import br.com.client.models.Obra

class DetalhesObraActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesObraBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarObra()
    }

    private fun tentaCarregarObra() {
        intent.getParcelableExtra<Obra>(CHAVE_OBRA)?.let {
            obraCarregada -> preencheCampos(obraCarregada)
        }?: finish()
    }

    private fun preencheCampos(obraCarregada: Obra) {
        with(binding){
            activityDetalhesObraImagem.tentaCarregarImagem(obraCarregada.imagem)
            activityDetalhesObraNome.text = obraCarregada.nome
            activityDetalhesObraDescricao.text = obraCarregada.descricao
        }
    }


}