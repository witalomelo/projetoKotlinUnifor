package br.com.client.ui.activity.exposicao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.client.R
import br.com.client.models.Exposicao
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ExposicaoActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var exposicoesRecyclerView: RecyclerView
    private lateinit var exposicoesList: ArrayList<Exposicao>
    private lateinit var exposicaoAdapter: ExposicaoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exposicoes)

        exposicoesRecyclerView = findViewById(R.id.recyclerViewExposicoes)
        exposicoesRecyclerView.layoutManager = LinearLayoutManager(this)
        exposicoesRecyclerView.setHasFixedSize(true)

        exposicoesList = arrayListOf()
        exposicaoAdapter = ExposicaoAdapter(exposicoesList)
        exposicoesRecyclerView.adapter = exposicaoAdapter

        fetchExposicoes()
    }

    private fun fetchExposicoes() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Exposicoes")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                exposicoesList.clear()
                for (dataSnapshot in snapshot.children) {
                    val exposicao = dataSnapshot.getValue(Exposicao::class.java)
                    if (exposicao != null) {
                        exposicoesList.add(exposicao)
                    }
                }
                exposicaoAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}