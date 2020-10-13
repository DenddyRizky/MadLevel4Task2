package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {


    var gameImages: IntArray = intArrayOf(R.drawable.rock, R.drawable.paper, R.drawable.scissors)
    var results: Array<String> = arrayOf("You win!", "Draw", "Computer wins!")
    var rock = 0
    var paper = 1
    var sciccors = 2
    var win = 0
    var draw = 1
    var lose = 2

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_rock_option.setOnClickListener{
            playGame(rock)
        }

        iv_paper_option.setOnClickListener{
            playGame(paper)
        }

        iv_scissors_option.setOnClickListener{
            playGame(sciccors)
        }
    }

    private fun playGame(playerChoice: Int){
        var computerChoice = computerPick()
        var result = resultDecider(playerChoice, computerChoice)

        ivComputer.setImageResource(gameImages[computerChoice])
        ivPlayer.setImageResource(gameImages[playerChoice])
        tvResult.text = results.get(result)

    }

    private fun resultDecider(player: Int, computer: Int): Int {

        return if(player == computer){
            draw
        } else if(player == rock && computer == sciccors){
            win
        } else if(player == paper && computer == rock){
            win
        } else if(player == sciccors && computer == paper){
            win
        } else lose
    }

    private fun computerPick() : Int{
        return Random.nextInt(0,3)
    }
}