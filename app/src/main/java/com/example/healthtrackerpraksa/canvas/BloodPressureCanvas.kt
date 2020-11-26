package com.example.healthtrackerpraksa.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.persistence.model.BloodPressure

private const val STROKE_WIDTH = 10f

class BloodPressureCanvas @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private var pressureValue: List<BloodPressure>? = null

    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private val paint = Paint().apply {
        color = drawColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE_WIDTH
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
        val path = Path()
        if(pressureValue != null) {
            path.moveTo(50f, pressureValue!![0].upperValue + 200f)
            path.lineTo(150f, pressureValue!![1].upperValue + 200f)
            var currentX = 150f
            var currentY = pressureValue!![1].upperValue + 200f
            for (data in pressureValue!!) {
                currentX += 100f
                path.moveTo(currentX, currentY)
                path.lineTo(currentX + 100f, data.upperValue + 200f)
            }
        }
        path.moveTo(50f, 450f)
        path.lineTo(50f, 450f)
        path.lineTo(200f, 350f)
        path.lineTo(350f, 350f)
        path.lineTo(500f, 320f)
        path.lineTo(650f, 500f)
        canvas.drawPath(path, paint)
    }
    fun setParameter(data: List<BloodPressure>) {
        this.pressureValue = data
        invalidate()
    }
}