package com.nxt.piechart

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.graphics.RectF
import android.util.TypedValue


class PieChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : View(context, attrs, defStyle) {

    private var mTextPaint: TextPaint? = null
    private var mTextHeight: Float = 0f

    private var pieChartCircleRadius = 100f //bán kính

    private var textBottom: Float = 0f

    private var mTextSize = 14f //kích thước chữ

    //diện tích hình chữ nhật bị chiếm bởi biểu đồ tròn
    private var pieChartCircleRectF = RectF()

    //danh sách thông tin biểu đồ tròn
    private var pieceDataHolders = mutableListOf<PieceDataHolder>()

    //đánh dấu độ dài dòng
    private var markerLineLength: Float = 30f

    init {
        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PieChartView,
            defStyle, 0
        )
        pieChartCircleRadius = a.getDimension(
            R.styleable.PieChartView_circleRadius,
            pieChartCircleRadius
        )
        mTextSize = a.getDimension(R.styleable.PieChartView_textSize,
            mTextSize) / resources.displayMetrics.density
        a.recycle()
        mTextPaint = TextPaint()
        mTextPaint!!.flags = Paint.ANTI_ALIAS_FLAG
        mTextPaint!!.textAlign = Paint.Align.LEFT
        invalidateTextPaintAndMeasurements()
    }

    private fun invalidateTextPaintAndMeasurements() {
        mTextPaint!!.textSize = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
            mTextSize,
            context.resources.displayMetrics))

        val fontMetrics = mTextPaint!!.fontMetrics
        mTextHeight = fontMetrics.descent - fontMetrics.ascent
        textBottom = fontMetrics.bottom
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        initPieChartCircleRectF();
        drawAllSectors(canvas);
    }

    private fun drawAllSectors(canvas: Canvas?) {
        var sum = 0f
        for ((value) in pieceDataHolders) {
            sum += value
        }

        var sum2 = 0f
        for ((value, color, marker) in pieceDataHolders) {
            val startAngel = sum2 / sum * 360
            sum2 += value
            val sweepAngel = value / sum * 360
            drawSector(canvas!!, color, startAngel, sweepAngel)
            drawMarkerLineAndText(canvas, color, startAngel + sweepAngel / 2, marker)
        }
    }

    private fun initPieChartCircleRectF() {
        pieChartCircleRectF.left = width / 2 - pieChartCircleRadius;
        pieChartCircleRectF.top = height / 2 - pieChartCircleRadius;
        pieChartCircleRectF.right = pieChartCircleRectF.left + pieChartCircleRadius * 2;
        pieChartCircleRectF.bottom = pieChartCircleRectF.top + pieChartCircleRadius * 2;
    }

    /**
     * Đặt dữ liệu hiển thị trong biểu đồ tròn
     *
     *  @param data liệt kê dũ liệu
     */

    fun setData(data: List<PieceDataHolder>) {
        pieceDataHolders.clear()
        pieceDataHolders.addAll(data)

        invalidate()
    }

    /**
     * Vẽ các dòng chú thích và đánh dấu văn bản
     *
     * @param canvas     tranh
     * @param color      màu
     * @param startAngle góc bắt đầu
     * @param sweepAngle Góc cuối
     */

    private fun drawSector(
        canvas: Canvas,
        color: Int,
        startAngle: Float,
        sweepAngle: Float,
    ) {
        val paint = Paint()
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.style = Paint.Style.FILL
        paint.color = color
        canvas.drawArc(pieChartCircleRectF, startAngle, sweepAngle, true, paint)
    }

    /**
     * vẽ đường đánh dấu và đánh dấu văn bản
     * @param canvas
     * @param color
     * @param rotateAngel góc quay giữa vách đánh dấu và phương ngang
     */

    private fun drawMarkerLineAndText(
        canvas: Canvas,
        color: Int,
        rotateAngel: Float,
        text: String?,
    ) {
        val paint = Paint()
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.style = Paint.Style.STROKE
        paint.color = color
        val path = Path()
        path.close()
        path.moveTo((width / 2).toFloat(), (height / 2).toFloat())
        val x = (width / 2 + (markerLineLength + pieChartCircleRadius) * Math.cos(Math.toRadians(
            rotateAngel.toDouble()))).toFloat()
        val y = (height / 2 + (markerLineLength + pieChartCircleRadius) * Math.sin(Math.toRadians(
            rotateAngel.toDouble()))).toFloat()
        path.lineTo(x, y)
        val landLineX: Float = if (270f > rotateAngel && rotateAngel > 90f) {
            x - 20
        } else {
            x + 20
        }
        path.lineTo(landLineX, y)
        canvas.drawPath(path, paint)
        mTextPaint!!.color = color
        if (270f > rotateAngel && rotateAngel > 90f) {
            val textWidth = mTextPaint!!.measureText(text)
            canvas.drawText(text!!,
                landLineX - textWidth,
                y + mTextHeight / 2 - textBottom,
                mTextPaint!!)
        } else {
            canvas.drawText(text!!, landLineX, y + mTextHeight / 2 - textBottom, mTextPaint!!)
        }
    }

}