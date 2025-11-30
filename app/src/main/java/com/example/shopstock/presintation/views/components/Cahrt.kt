package com.example.shopstock.presintation.views.components

// Compose imports
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.shopstock.domain.models.ChartPoint
import com.example.shopstock.domain.models.ChartStyle

// Data layer - abstractions

// Single responsibility: Calculate chart coordinates
class ChartCoordinateCalculator {
    fun calculatePoints(
        sales: List<Int>,
        canvasWidth: Float,
        canvasHeight: Float
    ): List<ChartPoint> {
        val maxY = (sales.maxOrNull() ?: 0) + 10
        val widthPerPoint = canvasWidth / (sales.size - 1)
        val heightScale = canvasHeight / maxY.toFloat()

        return sales.mapIndexed { index, value ->
            ChartPoint(
                x = index * widthPerPoint,
                y = canvasHeight - value * heightScale,
                label = value.toString()
            )
        }
    }
}

// Single responsibility: Draw chart components
class ChartRenderer(private val style: ChartStyle) {
    fun drawArea(drawScope: DrawScope, points: List<ChartPoint>) {
        val areaPath = Path().apply {
            moveTo(0f, drawScope.size.height)
            points.forEach { lineTo(it.x, it.y) }
            lineTo(drawScope.size.width, drawScope.size.height)
            close()
        }
        drawScope.drawPath(areaPath, color = style.areaColor)
    }
    fun drawLine(drawScope: DrawScope, points: List<ChartPoint>) {
        with(drawScope) {  // This provides Density context
            val linePath = Path().apply {
                points.forEachIndexed { index, point ->
                    if (index == 0) moveTo(point.x, point.y)
                    else lineTo(point.x, point.y)
                }
            }
            drawPath(
                linePath,
                color = style.lineColor,
                style = Stroke(width = style.lineWidth.toPx())
            )
        }
    }

    fun drawDots(drawScope: DrawScope, points: List<ChartPoint>) {
        with(drawScope) {
            points.forEach { point ->
                drawCircle(
                    color = style.dotColor,
                    radius = style.dotRadius.toPx(),
                    center = Offset(point.x, point.y)
                )
            }
        }
    }
}

// Open for extension: Main composable now delegates responsibilities
@Composable
fun SalesLineChart(
    sales: List<Int>,
    xAxisLabels: List<String> = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"),
    style: ChartStyle = ChartStyle(),
    calculator: ChartCoordinateCalculator = ChartCoordinateCalculator(),
    renderer: ChartRenderer = ChartRenderer(style)
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(bottom = 16.dp)
    ) {
        val points = calculator.calculatePoints(sales, size.width, size.height)

        renderer.drawArea(this, points)
        renderer.drawLine(this, points)
        renderer.drawDots(this, points)

        // Draw labels (could be extracted to LabelRenderer)
        points.forEachIndexed { index, point ->
            drawContext.canvas.nativeCanvas.drawText(
                point.label,
                point.x,
                point.y - 8.dp.toPx(),
                android.graphics.Paint().apply {
                    textAlign = android.graphics.Paint.Align.CENTER
                    textSize = 28f
                    color = android.graphics.Color.BLACK
                }
            )
        }

        xAxisLabels.forEachIndexed { index, label ->
            val x = index * (size.width / (xAxisLabels.size - 1))
            drawContext.canvas.nativeCanvas.drawText(
                label,
                x,
                size.height + 10.dp.toPx(),
                android.graphics.Paint().apply {
                    textAlign = android.graphics.Paint.Align.CENTER
                    textSize = 30f
                    color = android.graphics.Color.BLACK
                }
            )
        }
    }
}