package com.example.appviewproject

// intro image 주소를 불러오는 api 결과 값을 저장하는 클래스
data class IntroImage
(
    // http 코드 (200, 404 등)
    val code: String,
    // intro image url
    val url: String
)