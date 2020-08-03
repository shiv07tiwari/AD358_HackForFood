package com.example.humraahi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.humraahi.DataModels.NotReq
import com.example.humraahi.DataModels.NotRes
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Response

class NotiActivity : AppCompatActivity() {
    val apiService = APIClient.getClient().create<NetworkService>(NetworkService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noti)

        val t = findViewById<TextView>(R.id.noti_content)
        val _a = NotReq("get", 3)
        val call = apiService.getNotifications(_a)
        call.enqueue(object : retrofit2.Callback<ArrayList<NotRes>> {
            override fun onFailure(call: Call<ArrayList<NotRes>>?, t: Throwable?) {
                Log.e("log",t!!.message)
            }

            override fun onResponse(
                    call: Call<ArrayList<NotRes>>?,
                    response: Response<ArrayList<NotRes>>?
            ) {
               //Toast.makeText(applicationContext, response?.body(), Toast.LENGTH_SHORT).show()
                Log.e("log", response?.body().toString());
                var str = "";
                response?.body()?.forEach {

                    str += "** " + it.remark+"\n"+"\n"
                }
                t.text = str
            }
        })



    }
}