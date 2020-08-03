package com.example.humraahi.DataModels


class SchServer (val complaint_id:Int, val road_id:Int,
                val defect_type:String,val is_verified:Boolean,
                val is_resolved:Boolean, val assigned_insp_id:Int,
                val insp_rep_id:String, val reported_month:Int,
                val reported_year:Int, val resolved_month:Int,
                val resolved_year:Int, val lat:Double, val lng:Double, val remark:String) {
    // lat, long, time, road_id, text
}