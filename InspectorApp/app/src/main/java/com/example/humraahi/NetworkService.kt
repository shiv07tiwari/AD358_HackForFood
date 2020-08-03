package com.example.humraahi
import com.example.humraahi.DataModels.*
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    @POST("/complaint")
    fun getComplaints(@Body query: AllReq): Call<ArrayList<SchServer>>

    @POST("/verification")
    fun addComplaint(@Body query: VerificationAdd): Call<String>

    @POST("/noti")
    fun getNotifications(@Body query: NotReq) : Call<ArrayList<NotRes>>
}