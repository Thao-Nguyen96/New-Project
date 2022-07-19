package fsoft.training.columnChart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import fsoft.training.columnChart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var value1 = 10
    var value2 = 22
    var value3 = 5
    var value4 = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBarchart()

        binding.seekbar1.max = 24
        binding.seekbar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                value1 = p1

                binding.start1.text =p0?.progress.toString()

                setUpBarchart()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        binding.seekbar2.max = 24
        binding.seekbar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                value2 = p1

                binding.start2.text =p0?.progress.toString()

                setUpBarchart()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        binding.seekbar3.max = 24
        binding.seekbar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                value3 = p1

                binding.start3.text =p0?.progress.toString()

                setUpBarchart()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        binding.seekbar4.max = 24
        binding.seekbar4.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                value4 = p1

                binding.start4.text =p0?.progress.toString()

                setUpBarchart()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }

    private fun setUpBarchart(){
        val visitors = arrayListOf<BarEntry>()

        visitors.add(BarEntry(2014f, value1.toFloat()))
        visitors.add(BarEntry(2015f, value2.toFloat()))
        visitors.add(BarEntry(2016f, value3.toFloat()))
        visitors.add(BarEntry(2017f, value4.toFloat()))

        val barDataSet = BarDataSet(visitors, "visitors")
        barDataSet.setColors(Color.BLUE, Color.YELLOW, Color.DKGRAY, Color.GREEN)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f

        val barData = BarData(barDataSet)

        binding.barchart.setFitBars(true)
        binding.barchart.data = barData
        binding.barchart.description.text = "Bar chart Example"
        binding.barchart.animateY(2000)
    }
}