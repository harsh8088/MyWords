package com.hrawat.mywords

import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.hrawat.mywords.utils.RandomUtil


class PuzzleActivity : AppCompatActivity() {

    var startTime = 0L

    var handler = Handler()
    private lateinit var puzzleAdapter: WordPuzzleAdapter

    lateinit var textTimer: TextView
    private lateinit var layoutStarted: LinearLayout
    private lateinit var ran: RandomUtil
    private lateinit var timeCount:MyCount


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
        val findWord = findViewById<TextView>(R.id.text_find)
        puzzleAdapter = WordPuzzleAdapter(this@PuzzleActivity)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_word_puzzle)

        val mLayoutManager = GridLayoutManager(this, 7)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = puzzleAdapter
        puzzleAdapter.setCategoryListener(object : WordPuzzleAdapter.CategoryListener {
            override fun onCategoryClick( position: Int, isChecked: Boolean) {

                val list = puzzleAdapter.getList()
                val posList = puzzleAdapter.getWordPositionList()
                var ismatched = false
                for (puzzle in list) {
                    if(posList.contains(puzzle.position)) {
                        ismatched = puzzle.isChecked
                    }
                    else if(puzzle.isChecked)
                        ismatched=false
                }
                if (ismatched) {
                 timeCount.onFinish()
                }


            }
        })

        ran = RandomUtil()
        puzzleAdapter.addRandomWords(ran.getRandomDigit())

        findWord.text = ran.getRandomWord()
        hideWordinArray(findWord.text.toString())


        btnStart.setOnClickListener(View.OnClickListener {
            btnStart.visibility = View.GONE
            layoutStarted.visibility = View.VISIBLE

             timeCount=MyCount()

//
//            object : CountDownTimer(30000, 1000) {
//
//                override fun onTick(millisUntilFinished: Long) {
//                    textTimer.text = ("Remaining: " + millisUntilFinished / 1000)
//                    //here you can have your logic to set text to edittext
//                }
//
//                override fun onFinish() {
//                    showResults()
//                }
//            }.start()

//            startTime = SystemClock.uptimeMillis()
//            handler.postDelayed(runnable, 0)

        })
    }

    private fun hideWordinArray(word: String) {
        var length = word.length

        var randomPos = ran.getRandomPosition()
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


    private var runnable: Runnable = object : Runnable {

        override fun run() {
            var timeBuff: Long = 0

            var milliSeconds: Long = 0
            var Seconds: Long
            val Minutes: Long
            val MilliSeconds: Long
            var updateTime: Long
            milliSeconds = SystemClock.uptimeMillis() - startTime

            updateTime = timeBuff + milliSeconds


            Seconds = (updateTime / 1000)


            Minutes = Seconds / 60

            Seconds %= 60

            MilliSeconds = (updateTime % 1000)

            textTimer.text = ("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds))

            handler.postDelayed(this, 0)
        }

    }

    inner class MyCount :CountDownTimer(30000, 1000){
        override fun onTick(millisUntilFinished: Long) {
            textTimer.text = ("Remaining: " + millisUntilFinished / 1000)
        }

        override fun onFinish() {
            showResults()
        }

    }

}
