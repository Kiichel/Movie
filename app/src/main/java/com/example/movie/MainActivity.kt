package com.example.movie

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.icu.number.Scale
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler
    private lateinit var anim: ScaleAnimation
    lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img = findViewById(R.id.imgIconId)

        anim = ScaleAnimation(0F, 1F, 0F, 1F,
        img.measuredHeight.toFloat(), img.measuredWidth.toFloat())

        anim.duration = 1000
        anim.interpolator = DecelerateInterpolator()
        anim.fillAfter = true
        img.startAnimation(anim)

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val i = Intent(this, MoviesListActivity::class.java)
            startActivity(i)
            finish()
        }, 1000)
    }
}