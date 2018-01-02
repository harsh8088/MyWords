package com.hrawat.mywords.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hrawat.mywords.R
import com.hrawat.mywords.model.Category
import java.util.*

/**
 * Created by hrawat on 1/2/2018.
 */
class TicTacToeAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tic_tac_toe, null)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ItemViewHolder) {
            val details = categoryList[position]
            holder.categoryName.text = details.name
            holder.background.setOnClickListener(View.OnClickListener
            { categoryListener?.onCategoryClick(this@TicTacToeAdapter, details.name) })
        }
    }


    private var categoryListener: ClickListener? = null
    private var categoryList = ArrayList<Category>()

    interface ClickListener {

        fun onCategoryClick(adapter: TicTacToeAdapter, categoryName: String)
    }

    fun setCategoryListener(listener: ClickListener) {
        this.categoryListener = listener
    }


    fun addAllCategories(nearByCategories: ArrayList<Category>) {
        this.categoryList.clear()
        this.categoryList.addAll(nearByCategories)
        this.notifyDataSetChanged()
    }


    private inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val background: ImageView = view.findViewById(R.id.iv_category_background)
        val icon: ImageView = view.findViewById(R.id.iv_category_icon)
        val categoryName: TextView = view.findViewById(R.id.tv_category_name)

    }


}