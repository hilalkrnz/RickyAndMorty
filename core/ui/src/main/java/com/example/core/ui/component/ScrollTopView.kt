package com.example.core.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.core.ui.R
import kotlin.math.min

class ScrollTopView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {
    private var circleColor: Int = Color.BLUE
    private val vectorDrawable: Drawable? =
        ContextCompat.getDrawable(context, R.drawable.arrow_up_icon)


    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ScrollTopView,
            0, 0
        ).apply {
            try {
                circleColor = getColor(R.styleable.ScrollTopView_circleColor, Color.BLUE)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint().apply {
            color = circleColor
            style = Paint.Style.FILL
            isAntiAlias = false
        }

        val centerCoordinateX = width / 2F
        val centerCoordinateY = height / 2F
        val radius = min(width, height) / 2F

        val vectorSize = (radius * 2).toInt()
        val vectorLeft = centerCoordinateX - vectorSize / 2F
        val vectorTop = centerCoordinateY - vectorSize / 2F
        val vectorRight = centerCoordinateX + vectorSize / 2F
        val vectorBottom = centerCoordinateY + vectorSize / 2F

        canvas?.drawCircle(centerCoordinateX, centerCoordinateY, radius, paint)

        vectorDrawable?.setBounds(
            vectorLeft.toInt(),
            vectorTop.toInt(),
            vectorRight.toInt(),
            vectorBottom.toInt()
        )
        canvas?.let { vectorDrawable?.draw(it) }
    }
}

