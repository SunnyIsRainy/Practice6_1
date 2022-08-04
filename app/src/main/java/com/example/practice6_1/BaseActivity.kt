package com.example.practice6_1

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    val mContext = this
    abstract fun setupEvent()
    abstract fun setValue()
}