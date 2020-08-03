package com.example.humraahi
import com.example.humraahi.DataModels.AllReq
import com.example.humraahi.DataModels.SchServer
import com.example.humraahi.DataModels.ScheduleDetails
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    @POST("/complaint")
    fun getComplaints(@Body query: AllReq): Call<ArrayList<SchServer>>
}