package com.oratakashi.dummies.utility

import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun<T> ViewGroup.updateState(state: MutableStateFlow<T>, value: T) {
    findViewTreeLifecycleOwner()?.lifecycleScope?.launch(Dispatchers.Main.immediate) {
        state.emit(value)
    }
}