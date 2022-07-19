package com.nxt.customsppiner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_selected.view.*

class CategoryAdapter(
    context: Context,
    resource: Int,
    objects: List<Category>
) : ArrayAdapter<Category>(context, resource, objects) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_selected, parent, false)

        val tvSelected = view.tv_selected
        val category = this.getItem(position)

        if (category != null){
            tvSelected.text = category.name
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)

        val tvCategory = view.tv_category
        val category = this.getItem(position)

        if (category != null){
            tvCategory.text = category.name
        }
       return view
    }
}