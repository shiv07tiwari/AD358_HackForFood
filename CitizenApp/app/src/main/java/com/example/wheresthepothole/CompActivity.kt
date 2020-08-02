package com.example.wheresthepothole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheresthepothole.networking.APIClient
import com.example.wheresthepothole.networking.NetworkService
import com.example.wheresthepothole.objects.CompBody
import com.example.wheresthepothole.objects.DataReading
import com.example.wheresthepothole.objects.NewComp
import com.example.wheresthepothole.objects.Pothole
import kotlinx.android.synthetic.main.activity_comp.*
import retrofit2.Call
import retrofit2.Response

class CompActivity : AppCompatActivity() {
    val apiService = APIClient.getClient().create<NetworkService>(NetworkService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp)

        submit.setOnClickListener {
            val data = CompBody((300..400).random(), (1..50).random(), defect_type.text.toString(),
                is_verified = false,
                is_resolved = false,
                assigned_insp_id = 3,
                insp_rep_id = "",
                reported_month = 3,
                reported_year = 2020,
                resolved_month = -1,
                resolved_year = -1
            )
            val comp = NewComp("add", data)
            val call = apiService.createComp(comp)
            Log.e("log", "KEY KEY: "+data.complaint_id)
            call.enqueue(object : retrofit2.Callback<String> {
                override fun onFailure(call: Call<String>?, t: Throwable?) {
                    Log.e("log",t!!.message)
                }

                override fun onResponse(
                    call: Call<String>?,
                    response: Response<String>?
                ) {
                    Toast.makeText(applicationContext, response?.body(), Toast.LENGTH_SHORT).show()
                    val i = Intent(baseContext,ProfileActivity::class.java)
                    startActivity(i)
                }
            })
        }

    }
}