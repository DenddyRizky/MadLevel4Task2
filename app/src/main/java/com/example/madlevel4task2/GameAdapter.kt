package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.game.view.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    var gameImages: IntArray = intArrayOf(R.drawable.rock, R.drawable.paper, R.drawable.scissors)
    var results: Array<String> = arrayOf("You win!", "Draw", "Computer wins!")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        holder.databind((games[position]))
    }

    override fun getItemCount(): Int {
        return games.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun databind(game: Game){
            itemView.ivPlayerHistory.setImageResource(gameImages[game.playerMove])
            itemView.ivComputerHistory.setImageResource(gameImages[game.computerMove])
            itemView.tvResultHistory.text = results[game.gameResult]
        }
    }
}