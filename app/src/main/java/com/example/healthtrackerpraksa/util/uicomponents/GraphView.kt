package com.example.healthtrackerpraksa.util.uicomponents

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.util.uicomponents.SizeTransformations.toDP
import java.time.LocalDateTime
import java.time.ZoneOffset

class GraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {


    var title = "Sample Title Label"
    var titleTextSize = 35f
    var sideTextSize = 10f
    var bottomTextSize = 10f

    //graph range
    var numOfVerticalTicks = 7
    var numOfHorizontalLines = 8
    var graphLineWidth = 5f

    var dataToDraw = listOf<Temperature>(
        Temperature(1606393401, 41),
        Temperature(1606307001, 37),
        Temperature(1606220601, 39),
        Temperature(1606134201, 36),
        Temperature(1606047801, 38),
        Temperature(1605961401, 39),
        Temperature(1605875001, 36)
    )
    private var minTempValue = findMinTempValue(dataToDraw)

    init {
        initAttributes(context, attrs, defStyleRes)

    }

    // entire view size
    var totalViewHeight = toDP(context, 340f)
    var totalViewWidth = toDP(context, 410f)


    //space allocated
    var bottomTextRoom = toDP(context, 20f)
    var tickRoom = toDP(context, 8f)
    var baselineRoom = toDP(context, 16f)
    var sideTextRoom = toDP(context, 32f)

    var paddingVertical = toDP(context, 8f)
    var paddingHorizontal = toDP(context, 8f)

    //graphStartCoordinates
    var graphStartX = paddingHorizontal + sideTextRoom
    var graphStartY = paddingVertical + bottomTextRoom + tickRoom + baselineRoom
    var graphEndY = toDP(context, 300f)
    var graphEndX: Float = toDP(context, 400f) - paddingHorizontal

    //distances between lines and ticks
    var distOfLines = (graphEndY - graphStartY) / numOfHorizontalLines
    var distOfTicks = (graphEndX - graphStartX) / numOfVerticalTicks


    private val textPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 1f
        isAntiAlias = true
        color = Color.BLACK
    }
    private val horLinesPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 1f
        isAntiAlias = true
        color = Color.BLACK
    }
    private val baselinePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        isAntiAlias = true
        color = Color.GRAY
    }
    private val graphPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 8f
        isAntiAlias = true
        color = resources.getColor(R.color.light_blue_400)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = resolveSizeAndState(totalViewWidth.toInt(), widthMeasureSpec, 0)
        val height = resolveSizeAndState(totalViewHeight.toInt(), heightMeasureSpec, 0)
        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        scaleX = width / totalViewWidth
        scaleY = height / totalViewHeight
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.save()
        canvas.scale(1f, -1f, 0f, height.toFloat() / 2)
        val transformedPoints = transformData(dataToDraw)
        drawGraph(canvas, transformedPoints)
        drawHorLines(canvas)
        drawVerTicks(canvas)
        drawBaseLine(canvas)
        canvas.restore()
        drawSideText(canvas, sideTextSize)
        drawBottomText(canvas, bottomTextSize, dataToDraw)
        drawTitle(canvas, title, titleTextSize)

    }

    private fun drawTitle(canvas: Canvas, title: String, textSize: Float) {
        val titleX = graphStartX
        val titleY = totalViewHeight - graphEndY
        textPaint.textSize = textSize
        textPaint.strokeWidth = 3f
        canvas.drawText(title, titleX * scaleX, titleY * scaleY, textPaint)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun drawBottomText(canvas: Canvas, textSize: Float, points: List<Temperature>) {
        var botTextX = graphStartX * scaleX
        var botTextY = totalViewHeight - paddingVertical - bottomTextRoom + 20f
        var timeToDraw: LocalDateTime
        textPaint.textSize = textSize
        for (i in 0 until this.numOfVerticalTicks) {
            timeToDraw = transformUnixToTimeDate(points[i])
            canvas.save()
            canvas.rotate(30f, botTextX, botTextY * scaleY)
            canvas.drawText(
                "${timeToDraw.month}, ${timeToDraw.dayOfMonth}",
                botTextX,
                botTextY * scaleY,
                textPaint
            )
            botTextX += distOfTicks * scaleX
            canvas.restore()
        }

    }

    private fun drawSideText(canvas: Canvas, textSize: Float) {
        var textX = graphStartX - sideTextRoom
        var textY = totalViewHeight - graphStartY
        var startValueToDraw = minTempValue - 1
        textPaint.textSize = textSize
        for (i in 0 until numOfHorizontalLines) {

            canvas.drawText(startValueToDraw.toString(), textX * scaleX, textY * scaleY, textPaint)
            textY -= distOfLines
            startValueToDraw += 1
        }
    }

    private fun drawBaseLine(canvas: Canvas) {
        val baselineX = graphStartX
        val baselineY = graphStartY - baselineRoom
        val path = Path()
        path.moveTo(baselineX * scaleX, baselineY * scaleY)
        path.lineTo(graphEndX * scaleX, baselineY * scaleY)
        canvas.drawPath(path, baselinePaint)
        path.close()

    }

    private fun drawVerTicks(canvas: Canvas) {
        val path = Path()
        path.moveTo(5f /2 +graphStartX * scaleX, (graphStartY - baselineRoom) * scaleY)
        var xCoord = 5f / 2 + graphStartX * scaleX
        for (i in 0 until this.numOfVerticalTicks) {
            path.lineTo(xCoord, (graphStartY - baselineRoom - tickRoom) * scaleY)
            xCoord += distOfTicks * scaleX
            path.moveTo(xCoord, (graphStartY - baselineRoom) * scaleY)
        }

        canvas.drawPath(path, baselinePaint)
        path.close()
    }

    private fun drawHorLines(canvas: Canvas) {
        val path = Path()
        path.moveTo(graphStartX * scaleX, graphStartY * scaleY)
        var yCoord = graphStartY * scaleY

        for (i in 0 until numOfHorizontalLines) {
            path.lineTo(graphEndX * scaleX, yCoord)

            yCoord += distOfLines * scaleY
            path.moveTo(graphStartX * scaleX, yCoord)
        }
        canvas.drawPath(path, horLinesPaint)
        path.close()
    }

    private fun drawGraph(
        canvas: Canvas,
        points: List<Point>
    ) {
        val path = Path()
        path.moveTo(points[0].x, points[0].y)
        canvas.drawCircle(points[0].x, points[0].y, 7f, graphPaint)
        points.forEachIndexed { i, point ->
            if (i != 0){
                path.lineTo(point.x, point.y)
                canvas.drawCircle(point.x, point.y, 7f, graphPaint)
            }
        }
        canvas.drawPath(path, graphPaint)
        path.close()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun transformData(data: List<Temperature>): List<Point> {
        val minValue = findMinTempValue(data) - 1
        val firstYCoordinate = graphStartY

        val firstXCoordinate = graphStartX
        val firstElementDay = transformUnixToTimeDate(data[0])

        val listOfPoints = mutableListOf<Point>()

        data.forEachIndexed { i, temperature ->
            // FOR Y COORDINATE
            val yPosition = temperature.temperature - minValue
            val coordinateY = firstYCoordinate + yPosition * distOfLines

//            //FOR X COORDINATE

            val coordinateX = firstXCoordinate + i * distOfTicks

            listOfPoints.add(i, Point(coordinateX * scaleX, coordinateY * scaleY))
        }
        return listOfPoints
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun transformUnixToTimeDate(data: Temperature): LocalDateTime {
        return LocalDateTime.ofEpochSecond(
            data.time.toLong(),
            0,
            ZoneOffset.ofTotalSeconds(7200)
        )
    }

    private fun findMinTempValue(data: List<Temperature>): Int {
        var minValue = data[0].temperature
        data.forEach { temp ->
            if (temp.temperature < minValue) minValue = temp.temperature
        }
        return minValue
    }

    private fun initAttributes(
        context: Context,
        attrs: AttributeSet?,
        defStyleRes: Int
    ) {
        val chosenAttributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.GraphView, defStyleRes, 0
        )
        title = chosenAttributes.getString(R.styleable.GraphView_title_text).toString()
        titleTextSize = chosenAttributes.getFloat(R.styleable.GraphView_title_text_size, 35f)
        sideTextSize = chosenAttributes.getFloat(R.styleable.GraphView_side_text_size, 20f)
        bottomTextSize = chosenAttributes.getFloat(R.styleable.GraphView_bottom_text_size, 14f)
        graphLineWidth = chosenAttributes.getFloat(R.styleable.GraphView_graph_line_width, 4f)
        numOfHorizontalLines =
            chosenAttributes.getInteger(R.styleable.GraphView_number_horizontal_lines, 5)
        numOfVerticalTicks =
            chosenAttributes.getInteger(R.styleable.GraphView_number_vertical_ticks, 5)

        chosenAttributes.recycle()
    }
}


class Point(val x: Float, val y: Float)
