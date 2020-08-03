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
        holder.bind("$position Hello", "World")
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