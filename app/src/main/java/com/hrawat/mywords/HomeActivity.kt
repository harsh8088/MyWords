package com.hrawat.mywords

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.hrawat.mywords.adapter.CategoryAdapter
import com.hrawat.mywords.model.Category
import java.util.*

class HomeActivity : AppCompatActivity() {
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
    }

    private fun initView() {
        categoryAdapter = CategoryAdapter(this@HomeActivity)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_category)
        val mLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = categoryAdapter
        categoryAdapter.setCategoryListener(object : CategoryAdapter.CategoryListener {
            override fun onCategoryClick(categoryAdapter: CategoryAdapter, categoryName: String) {
                when (categoryName) {
                    "Tic Tac Toe" -> startActivity(Intent(this@HomeActivity, TicTacToeActivity::class.java))
                    "Binary Challenge" -> startActivity(Intent(this@HomeActivity, BinaryChallengeActivity::class.java))
                    "Binary Cube" -> startActivity(Intent(this@HomeActivity, BinaryCubeActivity::class.java))
                    "Word Puzzle" -> startActivity(Intent(this@HomeActivity, PuzzleActivity::class.java))
                    else -> Toast.makeText(this@HomeActivity, "gone", Toast.LENGTH_SHORT).show()
                }
            }
        })
        getDataCategories()
    }


    private fun getDataCategories() {
        val nearByCategories = ArrayList<Category>()
        nearByCategories.add(Category("", "Tic Tac Toe", R.drawable.ic_tic_tac_toe))
        nearByCategories.add(Category("", "Binary Challenge", R.drawable.ic_binary_cube))
        nearByCategories.add(Category("", "Binary Cube", R.drawable.ic_binary_cube))
        nearByCategories.add(Category("", "Word Puzzle", R.drawable.ic_word_puzzle))
        nearByCategories.add(Category("", "Challenge", R.drawable.ic_word_puzzle))
        nearByCategories.add(Category("", "Challenge", R.drawable.ic_word_puzzle))

        categoryAdapter.addAllCategories(nearByCategories)

    }
}
