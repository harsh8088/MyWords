package com.hrawat.mywords

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.hrawat.mywords.utils.RandomUtil


class PuzzleActivity : AppCompatActivity() {

    var startTime = 0L

    var handler = Handler()

    lateinit var textTimer: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_puzzle)
        initViews()
    }

    private fun initViews() {
        val btnStart = findViewById<Button>(R.id.btn_start)
        textTimer = findViewById(R.id.tv_timer)
        val categoryAdapter = WordPuzzleAdapter(this@PuzzleActivity)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_word_puzzle)

        val mLayoutManager = GridLayoutManager(this, 7)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = categoryAdapter
        categoryAdapter.setCategoryListener(object : WordPuzzleAdapter.CategoryListener {
            override fun onCategoryClick(puzzleAdapter: WordPuzzleAdapter, details: String) {

            }
        })

        val ran = RandomUtil()
        categoryAdapter.addRandomWords(ran.getRandomDigit())



        btnStart.setOnClickListener(View.OnClickListener {
            startTime = SystemClock.uptimeMillis()
            handler.postDelayed(runnable, 0)

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
}
