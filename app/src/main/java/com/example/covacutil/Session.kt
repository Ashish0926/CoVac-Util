package com.example.covacutil

data class Session(
        val name: String,
        val address: String,
        val state_name:String,
        val district_name: String,
        val fee_type: String,
        val date: String,
        val available_capacity_dose1:Int,
        val available_capacity_dose2: Int,
        val fee: String,
        val min_age_limit: Int,
        val vaccine: String
        )
