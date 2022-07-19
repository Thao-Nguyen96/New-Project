package hn21_fr_android_07.basic_android_exam.piechard

import android.graphics.Color
import android.icu.util.ULocale
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.os.IResultReceiver
import java.util.*

class MainActivity : AppCompatActivity() {

    private var series = arrayListOf<BarSeries>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val random = Random()
        for (i in 0 until 10){
            series.add(BarSeries("label $i", random.nextFloat()))
        }
        setContentView(R.layout.activity_main)
    }
}