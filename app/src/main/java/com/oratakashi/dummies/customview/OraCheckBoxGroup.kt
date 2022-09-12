package com.oratakashi.dummies.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import com.oratakashi.dummies.R

class OraCheckBoxGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    var isSingleSelection: Boolean = false
        set(value) {
            field = value
            updateStateSingleSelection()
        }

    var listener: ((String) -> Unit)? = null

    var text: String
        get() {
            return children.toList()
                .filterIsInstance(OraCheckBox::class.java)
                .find { it.isChecked }?.text.toString()
        }
        set(value) {
            children.toList()
                .filterIsInstance(OraCheckBox::class.java)
                .find { it.text.toString() == value }?.isChecked = true
        }

    private fun updateStateSingleSelection() {
        if(isSingleSelection) {
            children.toList()
                .filterIsInstance(OraCheckBox::class.java)
                .onEach {
                    it.setOnCheckedChangeListener { view, isChecked ->

                        children.toList()
                            .filterIsInstance(OraCheckBox::class.java)
                            .onEach { currentView -> currentView.isChecked = false }

                        view.isChecked = isChecked

                        if(isChecked) {
                            listener?.invoke(view.text.toString())
                        }
                    }
                }
        }
    }

    fun addAll(data: List<String>) {
        data.forEach {
            addView(
                OraCheckBox(context).apply {
                    text = it
                },
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
            )
        }

        updateStateSingleSelection()
    }

    fun setOnCheckedChangeListener(callback: (String) -> Unit) {
        listener = callback
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if(child is OraCheckBox) {
            if(child.text?.toString()?.contains("Sample") == false) {
                children.toList()
                    .filterIsInstance(OraCheckBox::class.java)
                    .filter { it.text.toString().contains("Sample") }
                    .onEach { removeView(it) }
                super.addView(child, index, params)
            } else {
                super.addView(child, index, params)
            }
        }
    }

    init {
        orientation = VERTICAL

        if (isInEditMode) {
            addAll(
                listOf(
                    "Sample 1",
                    "Sample 2",
                    "Sample 3",
                )
            )
            text = "Sample 1"
        }

        attrs?.let {
            val attr = context.obtainStyledAttributes(attrs, R.styleable.OraCheckBoxGroup, 0, 0)

            runCatching {
                isSingleSelection = attr.getBoolean(R.styleable.OraCheckBoxGroup_singleSelection, false)
            }.onSuccess { attr.recycle() }
        }
    }
}