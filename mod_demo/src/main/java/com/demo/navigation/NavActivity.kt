package com.demo.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sum.demo.databinding.ActivityNavBinding

/**
 * @author Easin
 * @date   2022/5/12 16:46
 * @desc   MineNavActivity
 */
class NavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}