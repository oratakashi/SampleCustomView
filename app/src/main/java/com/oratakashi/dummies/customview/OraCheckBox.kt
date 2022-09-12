package com.oratakashi.dummies.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.content.ContextCompat
import com.oratakashi.dummies.R

class OraCheckBox @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
): AppCompatCheckBox(context, attr) {
    init {
        buttonDrawable = ContextCompat.getDrawable(context, R.drawable.selector_checkbox)
        layoutDirection = View.LAYOUT_DIRECTION_RTL
    }
}