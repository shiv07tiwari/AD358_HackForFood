package com.example.wheresthepothole

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.wheresthepothole.networking.APIClient
import com.example.wheresthepothole.networking.NetworkService
import com.example.wheresthepothole.objects.CompBody
import com.example.wheresthepothole.objects.NewComp
import kotlinx.android.synthetic.main.activity_comp.*
import retrofit2.Call
import retrofit2.Response
import java.util.*

class CompActivity : AppCompatActivity() {
    val apiService = APIClient.getClient().create<NetworkService>(NetworkService::class.java)
    var latitude:Double = 0.0
    var longitude:Double = 0.0
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp)
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps()
        }

        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]

        submit.setOnClickListener {
            val data =
                CompBody((300..400).random(), (1..50).random(), defect_type.text.toString(),
                is_verified = false,
                is_resolved = false,
                assigned_insp_id = 3,
                insp_rep_id = "",
                reported_month = month,
                reported_year = year,
                resolved_month = -1,
                resolved_year = -1,
                lat = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).latitude,
                lng = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).longitude
            )
            val comp = NewComp("add", data)
            val call = apiService.createComp(comp)
            Log.e("log", "KEY KEY: "+data.complaint_id)
            Log.e("log", "KEY KEY2: "+data.lng)
            call.enqueue(object : retrofit2.Callback<String> {
                override fun onFailure(call: Call<String>?, t: Throwable?) {
                    Log.e("log",t!!.message + data.complaint_id)
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
    private fun buildAlertMessageNoGps() {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    , 11)
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
                finish()
            }
        val alert: AlertDialog = builder.create()
        alert.show()
    }
}