package com.example.appviewproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 이미지 읽어와서 splashImage에 넣기
        Glide.with(this).load(R.drawable.save_money).into(splashImage)

        val handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {
                // 인트로 액티비티를 생성 ( 현재 액티비티, 인트로 액티비티의 클래스)
                val introActivity = Intent(this@Splash, Intro::class.java)
                startActivity(introActivity)    // 인트로 실행
                finish()                        // 뒤로가기 못하게 엑티비티 종료
            }

        }, 3000)

    }

    // 스플래쉬 도중 뒤로가기 block
    override fun onBackPressed() {
//        super.onBackPressed()
    }
}
