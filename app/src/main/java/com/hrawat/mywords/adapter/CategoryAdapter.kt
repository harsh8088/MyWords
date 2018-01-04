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
 * Created by hrawat on 12/28/2017.
 */
class CategoryAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categoryListener: CategoryListener? = null
    private var categoryList = ArrayList<Category>()

    interface CategoryListener {

        fun onCategoryClick(categoryAdapter: CategoryAdapter, categoryName: String)
    }

    fun setCategoryListener(listener: CategoryListener) {
        this.categoryListener = listener
    }


    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is CategoryViewHolder) {
            val details = categoryList[position]
//        viewHolder.icon.(details.getIcon());
            holder.background.setImageResource(details.thumbnail)
//            holder.categoryName.text = details.name
            holder.background.setOnClickListener(View.OnClickListener
            { categoryListener?.onCategoryClick(this@CategoryAdapter, details.name) })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, null)
        return CategoryViewHolder(view)
    }

    fun addAllCategories(nearByCategories: ArrayList<Category>) {
        this.categoryList.clear()
        this.categoryList.addAll(nearByCategories)
        this.notifyDataSetChanged()
    }


    private inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val background: ImageView = view.findViewById(R.id.iv_category_background)
        val icon: ImageView = view.findViewById(R.id.iv_category_icon)
        val categoryName: TextView = view.findViewById(R.id.tv_category_name)

    }

}