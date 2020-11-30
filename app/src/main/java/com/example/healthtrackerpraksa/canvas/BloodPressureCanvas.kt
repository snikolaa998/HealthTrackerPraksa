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

private const val STROKE_WIDTH = 12f
private const val STROKE_WIDTH_SECOND = 8f

class BloodPressureCanvas @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private var pressureValue: List<BloodPressure>? = null
    private var drawColorSecond = ResourcesCompat.getColor(resources, R.color.gray, null)
    private var drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)
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
    private val paintSecond = Paint().apply {
        color = drawColorSecond
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE_WIDTH_SECOND
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
    }

    private val minBloodPressure = 60
    private val maxBloodPressure = 220

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
        val path = Path()
        val secondPath = Path()
        pressureValue?.let { values ->
            if(values.isNotEmpty()) {
                val divWidth = canvas.width.toFloat() / (values.size - 1)
                val value = values[0]
                val difference = value.upperValue - minBloodPressure
                val percentage = difference.toFloat() / (maxBloodPressure - minBloodPressure)
                val calc = 150f / (maxBloodPressure - minBloodPressure)
                secondPath.moveTo(70f, 20f)
                secondPath.lineTo(70f, canvas.height.toFloat())
                secondPath.lineTo(canvas.width.toFloat(), canvas.height.toFloat())
                secondPath.moveTo(70f, (canvas.height /2).toFloat())
                secondPath.lineTo(canvas.width.toFloat(), (canvas.height /2).toFloat())
                secondPath.moveTo(70f, 20f)
                path.moveTo(70f, percentage * canvas.height)
                canvas.drawCircle(70f, percentage * canvas.height, 10f, paint)
                for (i in 1 until values.size) {
                    val newValue = values[i]
                    val difference2 = newValue.upperValue - minBloodPressure
                    val percentage2 = difference2.toFloat() / (maxBloodPressure - minBloodPressure)
                    path.lineTo(i * divWidth, percentage2 * canvas.height)
                    canvas.drawCircle(i * divWidth, percentage2 * canvas.height, 12f, paint)
                    secondPath.moveTo(i*divWidth, canvas.height.toFloat())
                    secondPath.lineTo(i * divWidth, canvas.height.toFloat() - 20f)
                }

            }

        }
//        path.moveTo(50f, 450f)
//        path.lineTo(50f, 450f)
//        path.lineTo(200f, 350f)
//        path.lineTo(350f, 350f)
//        path.lineTo(500f, 320f)
//        path.lineTo(650f, 500f)
        canvas.drawPath(path, paint)
        canvas.drawPath(secondPath, paintSecond)
    }
    fun setParameter(data: List<BloodPressure>) {
        this.pressureValue = data
        invalidate()
    }
}