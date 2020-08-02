package com.example.wheresthepothole.networking

import com.example.wheresthepothole.objects.*
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    @POST("/ispothole")
    fun checkPothole(@Body potholeQuery: DataReading): Call<String>

    @GET("/userpothole")
    fun getUserPotholes (@Query("user_id") userId : String,
                         @Query("type") type:String) : Call<ArrayList<Pothole>>
    @GET("/counter")
    fun getHomeCounters(@Path("city") city : String) : Call<Counter>

    @POST("/complaint")
    fun createComp(@Body comp:NewComp) : Call<String>
}
