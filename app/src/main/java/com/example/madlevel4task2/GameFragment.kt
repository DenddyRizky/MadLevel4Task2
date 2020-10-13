package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    var gameImages: IntArray = intArrayOf(R.drawable.rock, R.drawable.paper, R.drawable.scissors)
    var results: Array<String> = arrayOf("You win!", "Draw", "Computer wins!")

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
            playGame(0)
        }

        iv_paper_option.setOnClickListener{
            playGame(1)
        }

        iv_scissors_option.setOnClickListener{
            playGame(2)
        }
    }

    private fun playGame(playerChoice: Int){
        var computerChoice = computerPick()
        var result = resultDecider(playerChoice, computerChoice)

        ivComputer.setImageResource(gameImages[computerChoice])
        ivPlayer.setImageResource(gameImages[playerChoice])
        tvResult.text = results.get(result)
    }

    private fun resultDecider(player: Int, computer: Int) : Int{
        var rock = 0
        var paper = 1
        var sciccors = 2

        //PLAYER CHOOSES ROCK
        if(player == rock && computer == sciccors) return 0
        else if(player == rock && computer == rock) return 1
        else if(player == rock && computer == paper) return 2

        //PLAYER CHOOSES PAPER
        else if(player == paper && computer == rock) return 0
        else if(player == paper && computer == paper) return 1
        else if(player == paper && computer == sciccors) return 2

        //PLAYER CHOOSES SCICCORS
        else if(player == sciccors && computer == paper) return 0
        else if(player == sciccors && computer == sciccors) return 1
        else if(player == sciccors && computer == rock) return 2
    }

    private fun computerPick() : Int{
        return Random.nextInt(0,3)
    }
}