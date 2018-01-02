package com.hrawat.mywords

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.LinearLayout
import com.hrawat.mywords.model.Puzzle

/**
 * Created by hrawat on 12/25/2017.
 */
class WordPuzzleAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categoryListener: CategoryListener? = null
    private var puzzleList = ArrayList<Puzzle>()
    private lateinit var wordPosList: MutableList<Int>

    interface CategoryListener {

        fun onCategoryClick(position: Int, isChecked: Boolean, puzzle: Puzzle)
    }

    fun setCategoryListener(listener: CategoryListener) {
        this.categoryListener = listener
    }


    override fun getItemCount(): Int {
        return puzzleList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is WordPuzzleViewHolder) {
            val details = puzzleList[position]
            holder.word.text = details.text
            holder.background.setOnClickListener(View.OnClickListener
            {
                holder.word.isChecked = !holder.word.isChecked
//                details.isChecked = holder.word.isChecked
                categoryListener?.onCategoryClick(position, holder.word.isChecked, details)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_word_puzzle, null)
        return WordPuzzleViewHolder(view)
    }


    private inner class WordPuzzleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val background: LinearLayout = view.findViewById(R.id.ll_puzzle_word)
        val word: CheckedTextView = view.findViewById(R.id.tv_puzzle_word)

    }

    fun addRandomWords(randomList: ArrayList<Puzzle>) {

        puzzleList.clear()
        puzzleList.addAll(randomList)
        notifyDataSetChanged()
    }

    fun addWordsVertically(positon: Int, wordList: String) {

        var newPosition = positon
        wordPosList = arrayListOf()
        for (char in wordList.toCharArray().asList()) {
            puzzleList[newPosition] = Puzzle(char.toString(), newPosition, false)
            wordPosList.add(newPosition)
            newPosition += 7
        }
        notifyDataSetChanged()
    }

    fun getList(): ArrayList<Puzzle> {
        return puzzleList
    }

    fun setWordPositionList(wordPositon: MutableList<Int>) {
        wordPosList = arrayListOf()
        wordPosList.addAll(wordPositon)
    }

    fun getWordPositionList(): MutableList<Int> {
        return wordPosList
    }


}

