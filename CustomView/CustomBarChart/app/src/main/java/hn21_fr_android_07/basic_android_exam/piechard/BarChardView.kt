package hn21_fr_android_07.basic_android_exam.piechard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import java.util.*
import kotlin.collections.ArrayList

class BarChardView : View {

    //vẽ cột
    lateinit var barPainter: Paint

    //vẽ trục
    lateinit var axisPainter: Paint

    //các đường kẻ ngang
    lateinit var guidePainter: Paint

    //vẽ thông sô trục x,y
    lateinit var xLabelPainter: Paint
    lateinit var yLabelPainter: Paint

    //màu cột
    var barColor: Int? = 0

    //màu trục
    var axisColor: Int? = 0

    //màu đường kẻ ngang
    var guideColor: Int? = 0

    //độ dày truc
    var axisThickness: Float = 0f

    //độ dày đường kẻ ngang
    var guideThickness: Float = 0f

    //khoảng cách các hướng với biểu đồ
    var padding: Float = 0f
    var xLabelWith: Float = 0f
    var yLabelWith: Float = 0f

    //các cột
    private var series = arrayListOf<Float>()

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

        xLabelPainter = Paint(ANTI_ALIAS_FLAG)
        xLabelPainter.color = Color.BLACK
        xLabelPainter.textSize = 30f
        xLabelPainter.textAlign = Paint.Align.RIGHT

        yLabelPainter = Paint(ANTI_ALIAS_FLAG)
        yLabelPainter.color = Color.BLACK
        yLabelPainter.textSize = 30f
        yLabelPainter.textAlign = Paint.Align.RIGHT


        val random = Random()
        for (i in 0 until 10) {
            series.add(random.nextFloat())
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val height = height
        val width = width
        val gridTopLeft = padding + yLabelWith + 10f

        //khoảng cách chiều dọc trừ padding
        val gridBottom = height - padding - 100f
        //khoảng cách chiều ngang trừ padding
        val gridRight = width - padding

        drawGuides(canvas, gridBottom, gridRight)
        drawAxis(canvas, gridTopLeft, gridBottom, gridRight)
        drawBars(canvas, height, gridTopLeft, gridBottom, gridRight)
    }

    private fun drawBars(
        canvas: Canvas?,
        canvasHeight: Int,
        gridTopLeft: Float,
        gridBottom: Float,
        gridRight: Float,
    ) {

        //khoảng cách giữa các cột
        val space = 10f
        val totalSpace = space * series.size

        val width = (gridRight - gridTopLeft - totalSpace) / series.size
        //điểm bắt đầu cột
        var left = gridTopLeft + space
        //điểm kết thúc chiều rông cột
        var right = left + width
        val height = canvasHeight - 2 * padding - 90f

        for (s in series) {
            val top = padding + height * (1f - s)
            canvas?.drawRect(left, top, right, gridBottom - 5f, barPainter)

            canvas?.save()
            canvas?.rotate(-90f)
            canvas?.drawText("label $s", -height - padding,
                left + (width + 2 * space) / 2, xLabelPainter)
            canvas?.restore()


            left = right + space
            right = left + width
        }
    }

    private fun drawAxis(canvas: Canvas?, gridTopLeft: Float, gridBottom: Float, gridRight: Float) {

        //truc y
        canvas?.drawLine(gridTopLeft, gridBottom, gridTopLeft, padding, axisPainter)
        //truc x
        canvas?.drawLine(gridTopLeft, gridBottom, gridRight, gridBottom, axisPainter)
    }

    private fun drawGuides(canvas: Canvas?, gridBottom: Float, gridRight: Float) {

        //khoảng cách giữa các dòng
        val spacing = (gridBottom - padding) / 10f
        var y: Float

        for (i in 0 until 10) {

            //giá trị
            val label = (100 - i * 10).toString()

            val width = yLabelPainter.measureText(label)
            if (yLabelWith < width) yLabelWith = width

            val bound = Rect()
            yLabelPainter.getTextBounds(label, 0, label.length, bound)

            y = padding + i * spacing
            //startX , start y, stop x, stop y
            //x ngang y dọc
            canvas?.drawLine(padding + yLabelWith, y, gridRight, y, guidePainter)

            //viêt giá trị cột
            canvas?.drawText(label, padding + yLabelWith, y + bound.height() / 2, yLabelPainter)
        }
    }
}