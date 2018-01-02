package com.hrawat.mywords

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.hrawat.mywords.model.Puzzle
import com.hrawat.mywords.utils.RandomUtil


class PuzzleActivity : AppCompatActivity() {


    private lateinit var puzzleAdapter: WordPuzzleAdapter

    lateinit var textTimer: TextView
    private lateinit var layoutStarted: LinearLayout
    private lateinit var random: RandomUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_puzzle)
        initViews()
    }

    private fun initViews() {
        val btnStart = findViewById<Button>(R.id.btn_start)
        textTimer = findViewById(R.id.tv_timer)
        textTimer.text = ""
        btnStart.visibility = View.VISIBLE
        layoutStarted = findViewById(R.id.ll_started)
        layoutStarted.visibility = View.GONE
        val textWord = findViewById<TextView>(R.id.text_find)
        puzzleAdapter = WordPuzzleAdapter(this@PuzzleActivity)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_word_puzzle)

        val mLayoutManager = GridLayoutManager(this, 7)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = puzzleAdapter
        val ischeckedList = ArrayList<Puzzle>()
        puzzleAdapter.setCategoryListener(object : WordPuzzleAdapter.CategoryListener {
            override fun onCategoryClick(position: Int, isChecked: Boolean, puzzle: Puzzle) {
                val posList = puzzleAdapter.getWordPositionList()
                if (!ischeckedList.isEmpty() && ischeckedList.contains(puzzle)) {
                    if (isChecked) {
                        puzzle.isChecked = isChecked
                        ischeckedList.add(puzzle)
                    } else {
                        ischeckedList.remove(puzzle)
                    }
                } else if (isChecked) {
                    puzzle.isChecked = isChecked
                    ischeckedList.add(puzzle)
                }

                var matched = false
                if (ischeckedList.size == posList.size) {
                    for (checkedPuzzle in ischeckedList) {
                        matched = posList.contains(checkedPuzzle.position)
                    }
                    if (matched)
                        showResults()
                }


            }
        })

        random = RandomUtil()
//        puzzleAdapter.addRandomWords(random.getRandomDigit())

        textWord.text = random.getRandomWord()

        puzzleAdapter.addRandomWords(random.getRandomArray(textWord.text.toString()))
        puzzleAdapter.setWordPositionList(random.getWordPositionList())
//        hideWordinArray(textWord.text.toString())


        btnStart.setOnClickListener(View.OnClickListener {
            btnStart.visibility = View.GONE
            layoutStarted.visibility = View.VISIBLE


        })
    }

    private fun hideWordinArray(word: String) {
        var length = word.length

        var randomPos = random.getRandomPosition()
        var row = randomPos % 7
        //check horizontal
        var size = 7 - row
        if (size >= length) {
            Toast.makeText(this@PuzzleActivity, "horizontal success at " + randomPos, Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(this@PuzzleActivity, "horizontal fail at " + randomPos, Toast.LENGTH_SHORT).show()
        //check vertical
        var colomn = 0
        if (randomPos < 6)
            colomn = 1
        else
            colomn = randomPos / 7
        if (7 - colomn >= length) {
            puzzleAdapter.addWordsVertically(randomPos, word)
            Toast.makeText(this@PuzzleActivity, "vertical success at " + randomPos, Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(this@PuzzleActivity, "vertical fail at " + randomPos, Toast.LENGTH_SHORT).show()


    }

    private fun showResults() {
        val dialog = Dialog(this)
        dialog.setTitle("Result")
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.result_dialog)
        val btnOk = dialog.findViewById<Button>(R.id.btn_ok)
        dialog.show()



        btnOk.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
            initViews()
        })
    }


}
