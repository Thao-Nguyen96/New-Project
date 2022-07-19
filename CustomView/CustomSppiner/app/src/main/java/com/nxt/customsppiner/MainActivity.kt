package com.nxt.customsppiner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var categoryAdapter: CategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categoryAdapter = CategoryAdapter(this, R.layout.item_selected, getListCategory())

        spinner.adapter = categoryAdapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                Toast.makeText(this@MainActivity,
                    categoryAdapter!!.getItem(position)?.name,
                    Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun getListCategory(): List<Category> {

        val list: ArrayList<Category> = ArrayList()

        list.add(Category("Xuan Thao"))
        list.add(Category("Thanh Huyen"))
        list.add(Category("Pham Phong"))
        list.add(Category("Hai Lan"))
        list.add(Category("Khanh Ly"))

        return list
    }


}