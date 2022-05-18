package com.example.mercaaqui.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.mercaaqui.MainActivity
import com.example.mercaaqui.R

import kotlinx.android.synthetic.main.fragment_splash.*

private const val tiempo:Long=1600
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)
        var animation = AnimationUtils.loadAnimation(this,R.anim.anim)
        mercaaqui.startAnimation(animation)




        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, tiempo)
        animation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }
}