package com.test.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.MainAdapter.ViewHolder

class MainAdapter(val data: List<String>) : RecyclerView.Adapter<ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val t1 = view.findViewById(R.id.t1) as TextView
        val t2 = view.findViewById(R.id.t2) as TextView
        val t3 = view.findViewById(R.id.t3) as TextView
        val t4 = view.findViewById(R.id.t4) as TextView
        val t5 = view.findViewById(R.id.t5) as TextView
        val t6 = view.findViewById(R.id.t6) as TextView
        val t7 = view.findViewById(R.id.t7) as TextView
        val t8 = view.findViewById(R.id.t8) as TextView
        val t9 = view.findViewById(R.id.t9) as TextView
        val t10 = view.findViewById(R.id.t10) as TextView
        val t11 = view.findViewById(R.id.t11) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mainData = data[position].split("|")
        holder.apply {
            t1.text = mainData[0]
            t2.text = mainData[1]
            t3.text = mainData[2]
            t4.text = mainData[3]
            t5.text = mainData[4]
            t6.text = mainData[5]
            t7.text = mainData[6]
            t8.text = mainData[7]
            t9.text = mainData[8]
            t10.text = mainData[9]
            t11.text = mainData[10]
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}