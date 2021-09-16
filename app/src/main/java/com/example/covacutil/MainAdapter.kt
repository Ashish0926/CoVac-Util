package com.example.covacutil

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(private val context: Context, private val sessions: List<Session>): RecyclerView.Adapter<MainAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val center: TextView = itemView.findViewById(R.id.centerName)
        val address: TextView = itemView.findViewById(R.id.addressText)
        val state_dist: TextView = itemView.findViewById(R.id.state_dist_text)
        val date: TextView = itemView.findViewById(R.id.dateText)
        val capacity_dose1: TextView = itemView.findViewById(R.id.dose1)
        val capacity_dose2: TextView = itemView.findViewById(R.id.dose2)
        val vaccine: TextView = itemView.findViewById(R.id.vaccineName)
        val minAgeLim: TextView = itemView.findViewById(R.id.minAgeLimit)
        val feeType: TextView = itemView.findViewById(R.id.vaccinationFeeText)
        val vaccineFee: TextView = itemView.findViewById(R.id.vaccinePrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.center.text = sessions[position].name
        holder.address.text = sessions[position].address
        (sessions[position].state_name + ", " + sessions[position].district_name).also { holder.state_dist.text = it }
        ("Date : "+ sessions[position].date).also { holder.date.text = it }
        (sessions[position].min_age_limit.toString() + "+").also { holder.minAgeLim.text = it }
        ("Dose 1 : " + sessions[position].available_capacity_dose1.toString()).also { holder.capacity_dose1.text = it }
        ("Dose 2 : " + sessions[position].available_capacity_dose2.toString()).also { holder.capacity_dose2.text = it }
        holder.vaccine.text = sessions[position].vaccine

        if(sessions[position].fee_type == "Paid" || sessions[position].fee_type == "paid"){
            (sessions[position].fee_type + " : ").also { holder.feeType.text = it }
            ("₹" + sessions[position].fee).also { holder.vaccineFee.text = it }
            holder.feeType.setTextColor(Color.parseColor("#bf2123"))
        }else{
            "free".also { holder.feeType.text = it }
            holder.feeType.setTextColor(Color.parseColor("#0f9100"))
            holder.vaccineFee.text = ""
        }
    }

    override fun getItemCount(): Int {
        return sessions.size
    }
}