package br.com.client.ui.activity.exposicao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.client.R
import br.com.client.models.Exposicao

class ExposicaoAdapter(private val exposicoes: List<Exposicao>) : RecyclerView.Adapter<ExposicaoAdapter.ExposicaoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExposicaoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_exposicao, parent, false)
        return ExposicaoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExposicaoViewHolder, position: Int) {
        val exposicao = exposicoes[position]
        holder.tvNomeExposicao.text = exposicao.nome
        holder.tvDataExposicao.text = exposicao.data
        holder.tvDescricaoExposicao.text = exposicao.descricao
    }

    override fun getItemCount(): Int {
        return exposicoes.size
    }

    class ExposicaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNomeExposicao: TextView = itemView.findViewById(R.id.tvNomeExposicao)
        val tvDataExposicao: TextView = itemView.findViewById(R.id.tvDataExposicao)
        val tvDescricaoExposicao: TextView = itemView.findViewById(R.id.tvDescricaoExposicao)
    }
}
