package br.com.client.ui.activity.quiz

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.client.databinding.ActivityRankingBinding
import br.com.client.ranking.Player
import br.com.client.ranking.RankingListAdapter
import com.google.firebase.database.*

class RankingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRankingBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var rankingListAdapter: RankingListAdapter
    private var rankingList: ArrayList<Player> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        rankingListAdapter = RankingListAdapter(rankingList)
        binding.recyclerView.adapter = rankingListAdapter

        fetchRanking()
    }

    private fun fetchRanking() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Diretorio de Jogadores")
        databaseReference.orderByChild("score")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    rankingList.clear()
                    for (dataSnapshot in snapshot.children.reversed()) {
                        val userName = dataSnapshot.child("userName").getValue(String::class.java)
                        val score = dataSnapshot.child("score").getValue(Int::class.java)

                        if (userName != null && score != null) {
                            val player = Player(userName, score)
                            rankingList.add(player)
                        }
                    }
                    rankingListAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@RankingActivity,
                        "Erro ao carregar ranking",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("Firebase", "Erro: ", error.toException())
                }
            })
    }
}
