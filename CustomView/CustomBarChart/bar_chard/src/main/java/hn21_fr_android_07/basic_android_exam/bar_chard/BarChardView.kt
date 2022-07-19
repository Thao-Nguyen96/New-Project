package hn21_fr_android_07.basic_android_exam.bar_chard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

class BarChardView : View {

    lateinit var barPainter: Paint
    lateinit var axisPainter: Paint
    lateinit var guidePainter: Paint

    var padding: Float? = null

    var series = arrayListOf<Float>()

//    constructor(context: Context) : super(context) {
//
//
//    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        padding = 20f

        barPainter = Paint()
        barPainter.style = Paint.Style.FILL
        barPainter.color = Color.LTGRAY

        axisPainter = Paint()
        axisPainter.style = Paint.Style.STROKE
        axisPainter.color = Color.BLACK
        axisPainter.strokeWidth = 5f

        guidePainter = Paint()
        guidePainter.style = Paint.Style.STROKE
        guidePainter.color = Color.GRAY
        guidePainter.strokeWidth = 3f

        val random = Random()

        for (i in 0 until 10) {
            series.add(random.nextFloat())
        }

    }

//    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
//        context,
//        attrs,
//        defStyle) {
//
//    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var height: Int = height
        var width: Int = width
        var gridTopLeft: Float = padding!!
        var gridBottom = height - padding!!
        var gridRight = width - padding!!

        drawGuides(canvas, gridBottom, gridRight)
        drawAxis(canvas,gridTopLeft, gridBottom, gridRight)
        drawBars(canvas,gridTopLeft, gridBottom, gridRight)

    }

    private fun drawBars(canvas: Canvas?, gridTopLeft: Float, gridBottom: Float, gridRight: Float) {
        TODO("Not yet implemented")
    }

    private fun drawAxis(canvas: Canvas?, gridTopLeft: Float, gridBottom: Float, gridRight: Float) {
        TODO("Not yet implemented")
    }

    private fun drawGuides(canvas: Canvas?, gridBottom: Float, gridRight: Float) {

    }

}