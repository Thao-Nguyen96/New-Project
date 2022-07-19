package com.nxt.piechart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.nxt.piechart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var value1 = 10
    var value2 = 20
    var value3 = 50
    var value4 = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPieChart()

        binding.seekbarItem1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                value1 = p1
                binding.startPoint1.text = p0?.progress.toString()

                setUpPieChart()

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })


        binding.seekbarItem2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                value2 = p1
                binding.startPoint2.text = p0?.progress.toString()

                setUpPieChart()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })


        binding.seekbarItem3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                value3 = p1
                binding.startPoint3.text = p0?.progress.toString()

                setUpPieChart()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        binding.seekbarItem4.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                value4 = p1
                binding.startPoint4.text = p0?.progress.toString()

                setUpPieChart()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    private fun setUpPieChart() {
        val pieceDataHolders = mutableListOf<PieceDataHolder>()

        pieceDataHolders.add(PieceDataHolder(value1, Color.GREEN, "Item 1"))
        pieceDataHolders.add(PieceDataHolder(value2, Color.YELLOW, "Item 2"))
        pieceDataHolders.add(PieceDataHolder(value3, Color.RED, "Item 3"))
        pieceDataHolders.add(PieceDataHolder(value4, Color.BLUE, "Item 4"))

        binding.pieChart.setData(pieceDataHolders)
    }
}