package com.hsbc.techtest.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("samkirton/fb18814a155a4fe1034b6491420f344c/raw/af099d747837a2014aa8f6623c22ff8bb1899143/cv.json")
    fun getCv(): Single<Response<CvJson>>
}