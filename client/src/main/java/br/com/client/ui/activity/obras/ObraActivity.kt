package br.com.client.ui.activity.obras

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.client.R
import br.com.client.databinding.ActivityObraBinding
import br.com.client.models.Obra
import com.google.firebase.database.*

class ObraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityObraBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var adapter: ListaObrasAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) //obrigatorio do recyclerView

        adapter = ListaObrasAdapter(this, listOf())
        recyclerView.adapter = adapter

        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Obras")

        fetchObras()
    }

    private fun fetchObras() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val obrasList = mutableListOf<Obra>()
                for (dataSnapshot in snapshot.children) {
                    val obra = dataSnapshot.getValue(Obra::class.java)
                    if (obra != null) {
                        obrasList.add(obra)
                    }
                }
                adapter.updateObras(obrasList)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
