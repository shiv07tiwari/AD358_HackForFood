package com.example.humraahi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter : RecyclerView.Adapter<PagerVH>() {

    //array of colors to change the background color of screen
    private val colors = intArrayOf(
            android.R.color.black,
            android.R.color.holo_red_light,
            android.R.color.holo_blue_dark,
            android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
            PagerVH(LayoutInflater.from(parent.context), parent)

    //get the size of color array
    override fun getItemCount(): Int = Int.MAX_VALUE

    //binding the screen with view
    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        if (position == 0) {
            holder.bind("Vehicle Density", "While calculation vehicle density, take several measurements, preferably 3 readings for 1 min each in 15 minute intervals.\n")
        } else if (position == 1) {
            holder.bind("Road Quality", "\"For road quality measurements, especially include turnings, uneven segments and take multiple readings at least 25 m of gap.\n")
        } else if (position == 2) {
            holder.bind("Additional Complaint", "In case you find additional damage which is not reported in the complaint, include that in remarks too, this would avoid double verification for the same area. However do inform the administrator regarding the same")
        } else if (position == 3) {
            holder.bind("Defect Codes", "The codes related to road defects, inspection level, and remedial methods are given in the help section on the admin portal and also in the manual, double check entries before submitting")
        } else if (position == 4) {
            holder.bind("Mechanical Tools", "For using road quality measuring tools, make sure the device is properly calibrated, battery is charged and the memory card is present to avoid losing any data. ")
        }
    }

//    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
//        if(position == 0){
//            tvTitle.text = "ViewPager2"
//            tvAbout.text = "In this application we will learn about ViewPager2"
//            ivImage.setImageResource(R.drawable.face_image)
//            container.setBackgroundResource(colors[position])
//        }
//        if(position == 1) {
//            tvTitle.text = "ViewPager2"
//            tvAbout.text = "In this application we will learn about ViewPager2"
//            ivImage.setImageResource(R.drawable.face_image)
//            container.setBackgroundResource(colors[position])
//        }
//        if(position == 2) {
//            tvTitle.text = "ViewPager2"
//            tvAbout.text = "In this application we will learn about ViewPager2"
//            ivImage.setImageResource(R.drawable.face_image)
//            container.setBackgroundResource(colors[position])
//        }
//        if(position == 3) {
//            tvTitle.text = "ViewPager2"
//            tvAbout.text = "In this application we will learn about ViewPager2"
//            ivImage.setImageResource(R.drawable.face_image)
//            container.setBackgroundResource(colors[position])
//        }
//    }
}

class PagerVH(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.tut_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mYearView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.tvAbout)
        mYearView = itemView.findViewById(R.id.tvTitle)
    }

    fun bind(t1:String, t2:String) {
        mTitleView?.text = t1
        mYearView?.text = t2
    }
}