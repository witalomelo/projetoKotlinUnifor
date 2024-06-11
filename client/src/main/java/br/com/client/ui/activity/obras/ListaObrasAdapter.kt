package br.com.client.ui.activity.obras

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.client.R
import br.com.client.models.Obra
import coil.load

class ListaObrasAdapter(
    private val context: Context,
    private var obras: List<Obra>
) : RecyclerView.Adapter<ListaObrasAdapter.ViewHolder>() {

    //membro estatíco pertencente a classe
    companion object {
        const val CHAVE_OBRA = "obra" //chave utilizada para passar um objeto obra dentro de uma intent
    }

    //gerenciar visualizações individuais dentro do recycler view
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView = view.findViewById(R.id.nome)
        val autor: TextView = view.findViewById(R.id.autor)
        val descricao: TextView = view.findViewById(R.id.descricao)
        val imageView: ImageView = view.findViewById(R.id.imageView2)
    }

    //criar novos ViewHolder que serão usados para exibir itens dentro da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.obra_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obra = obras[position]

        Log.d("ListaObrasAdapter", "Carregando imagem da URL: ${obra.imagem}")

        holder.nome.text = obra.nome
        holder.autor.text = obra.autor
        holder.descricao.text = obra.descricao
        holder.imageView.load(obra.imagem)
        holder.itemView.setOnClickListener{
            val intent = Intent(context, DetalhesObraActivity::class.java)
            intent.putExtra(CHAVE_OBRA, obra)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = obras.size

    fun updateObras(novasObras: List<Obra>) {
        this.obras = novasObras
        notifyDataSetChanged()
    }
}
