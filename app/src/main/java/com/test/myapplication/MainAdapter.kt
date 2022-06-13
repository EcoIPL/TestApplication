package com.test.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.MainAdapter.ViewHolder

class MainAdapter(val data: Map<String, List<DataClass>>) : RecyclerView.Adapter<ViewHolder>() {
    val keys = data.keys
    lateinit var mainData: List<DataClass>
    var listData = ""
    var qtyList = ""


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val t1 = view.findViewById(R.id.oderNumber) as TextView
        val productId = view.findViewById(R.id.productId) as TextView
        val qty = view.findViewById(R.id.qty) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            listData = ""
            qtyList = ""
            mainData = (data.get(keys.elementAt(position)))!!
            t1.text = "Order No. " + keys.elementAt(position)

            for (i in mainData.indices) {
                listData += mainData[i].prdcd + "\n"
                qtyList += mainData[i].qty + "\n"
            }
            productId.text = listData
            qty.text = qtyList

        }
    }

    override fun getItemCount(): Int {
        return keys.size
    }
}