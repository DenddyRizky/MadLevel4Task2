package com.example.madlevel4task2

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Timestamp
import java.util.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

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
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.iv_action_history).isVisible = true
        requireActivity().toolbar.title = getString(R.string.app_name)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.iv_action_history -> {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun playGame(playerChoice: Int){
        var computerChoice = computerPick()
        var result = resultDecider(playerChoice, computerChoice)

        ivComputer.setImageResource(gameImages[computerChoice])
        ivPlayer.setImageResource(gameImages[playerChoice])
        tvResult.text = results.get(result)

        addGameHistory(playerChoice, computerChoice, result)
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

    private fun addGameHistory(playerMove: Int, computerMove: Int, gameResult: Int){
        mainScope.launch {
            val game = Game(playerMove, computerMove, gameResult)

            withContext(Dispatchers.IO){
                gameRepository.insertGame(game)
            }
        }
    }
}