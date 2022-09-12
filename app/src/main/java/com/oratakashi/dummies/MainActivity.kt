package com.oratakashi.dummies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oratakashi.dummies.databinding.ActivityMainBinding
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            cbgSample.addAll(listOf(
                "Indonesia",
                "English"
            ))
            cbgSample.isSingleSelection = false
            cbgSample.text = "Indonesia"
            cbgSample.setOnCheckedChangeListener {
                toast(it)
                android.util.Log.e("debug", "debug: $it")
            }
        }
    }
}