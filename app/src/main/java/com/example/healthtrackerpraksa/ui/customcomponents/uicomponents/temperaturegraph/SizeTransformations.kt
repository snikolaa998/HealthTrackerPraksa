package com.example.healthtrackerpraksa.ui.customcomponents.uicomponents.temperaturegraph

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

object SizeTransformations {

    fun toDP(context: Context, value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value, context.resources.displayMetrics
        )
    }

    fun toSP(context: Context, value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            value,
            context.resources.displayMetrics
        )
    }

    fun pxToDp(context: Context, px: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }
}