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
import com.example.healthtrackerpraksa.persistence.model.BloodSugar

private const val STROKE_WIDTH = 12f
private const val STROKE_WIDTH_SECOND = 8f

class BloodSugarCanvas @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val minBloodSugar = 60
    private val maxBloodSugar = 220

    private var sugarValue: List<BloodSugar>? = null

    private var drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)
    private var drawColorSecond = ResourcesCompat.getColor(resources, R.color.gray, null)
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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
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
        val secondPath = Path()
        sugarValue?.let { values ->
            if (values.isNotEmpty()) {
                val divWidth = canvas.width.toFloat() / (values.size - 1)
                val value = values[0]
                val difference = value.value - minBloodSugar
                val percentage = difference.toFloat() / (maxBloodSugar - minBloodSugar)
                secondPath.moveTo(70f, 20f)
                secondPath.lineTo(70f, canvas.height.toFloat())
                secondPath.lineTo(canvas.width.toFloat(), canvas.height.toFloat())
                secondPath.moveTo(70f, (canvas.height / 2).toFloat())
                secondPath.lineTo(canvas.width.toFloat(), (canvas.height / 2).toFloat())
                secondPath.moveTo(70F, 20F)
                path.moveTo(70f, percentage * canvas.height)
                canvas.drawCircle(70f, percentage * canvas.height, 10f, paint)
                for (i in 1 until values.size) {
                    val newValue = values[i]
                    val difference2 = newValue.value - minBloodSugar
                    val percentege2 = difference2.toFloat() / (maxBloodSugar - minBloodSugar)
                    path.lineTo(i * divWidth, percentege2 * canvas.height)
                    canvas.drawCircle(i * divWidth, percentege2 * canvas.height, 12f, paint)
                    secondPath.moveTo(i * divWidth, canvas.height.toFloat())
                    secondPath.lineTo(i * divWidth, canvas.height.toFloat() - 20f)
                }
            }
        }
        canvas.drawPath(path, paint)
        canvas.drawPath(secondPath, paintSecond)
    }
    fun setParameter(data: List<BloodSugar>) {
        this.sugarValue = data
        invalidate()
    }
}