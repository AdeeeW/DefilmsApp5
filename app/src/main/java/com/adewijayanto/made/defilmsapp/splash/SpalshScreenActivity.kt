package com.adewijayanto.made.defilmsapp.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.adewijayanto.made.defilmsapp.MainActivity
import com.adewijayanto.made.defilmsapp.databinding.ActivitySpalshScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class SpalshScreenActivity : AppCompatActivity() {
    private val binding: ActivitySpalshScreenBinding by lazy {
        ActivitySpalshScreenBinding.inflate(layoutInflater)
    }
    companion object{
        const val SPLASH_TIME_OUT: Long = 1000L
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        WindowManager.LayoutParams.FLAG_FULLSCREEN
        setContentView(binding.root)

        binding.splashLogo.visibility = View.GONE

        binding.splashLottie.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                binding.splashLogo.visibility = View.VISIBLE

                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this@SpalshScreenActivity, MainActivity::class.java))
                    finish()
                }, SPLASH_TIME_OUT)
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationRepeat(animation: Animator?) {}

        })
    }
}