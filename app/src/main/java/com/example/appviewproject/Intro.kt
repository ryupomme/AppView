package com.example.appviewproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_intro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Intro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        getIntroImage()
    }

    /*fun getIntroImage() {
        val imageUrl = "https://pbs.twimg.com/media/Do0evCEU8AM2rPV.jpg"

        Glide.with(this).load(imageUrl).into(introImage)

        val handler = Handler()
        handler.postDelayed({
            val mainActivity = Intent(this@Intro, MainActivity::class.java)
            startActivity(mainActivity)     // 메인 실행
            finish()                        // 뒤로가기 못하게 엑티비티 종료
        }, 3000)

    }*/

    // 스플래쉬 도중 뒤로가기 block
    override fun onBackPressed() {
//        super.onBackPressed()
    }

    fun getIntroImage() {
        // api 호출을 위한 retrofit 객체를 생성합니다
        val retrofit = Retrofit.Builder()
//             .baseUrl("http://ghryu.mmonstar.co.kr/")
            // .baseUrl("http://apptest.dv9163.kro.kr")
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // api 객체화
        val service = retrofit.create<HttpServiceIntrerface>(HttpServiceIntrerface::class.java!!)

        // 생성한 api 객체의 함수를 호출합니다
        service.getIntroImageUrl().enqueue(object : Callback<IntroImage> {

            // 호출
            override fun onResponse(call: Call<IntroImage>, response: Response<IntroImage>) {
                // 호출 결과를 IntroImage 객체에 저장합니다
                var model = response.body()
                // 성공시
                if (response.isSuccessful) {
                    Log.d("intro", model?.url)

                    // 인트로 이미지 url을 이용하여 이미지를 설정합니다
                    Glide.with(this@Intro).load(model?.url).into(introImage)

                    var handler = Handler()
                    // 3초 후에 run 함수를 실행합니다
                    handler.postDelayed(object : Runnable{
                        override fun run() {
                            // 메인 액티비티를 생성 ( 현재 액티비티, 메인 액티비티의 클래스)
                            val mainActivity = Intent(this@Intro, MainActivity::class.java)
                            startActivity(mainActivity)     // 메인 실행
                            finish()                        // 뒤로가기 못하게 엑티비티 종료
                        }

                    }, 3000)
                }
            }

            // api 호출 실패시
            override fun onFailure(call: Call<IntroImage>, t: Throwable) {
                Log.d("fail", "imageStart")
                Log.d("fail", t.message)
            }
        })
    }
}
