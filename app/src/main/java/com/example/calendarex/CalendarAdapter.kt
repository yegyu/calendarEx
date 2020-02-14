package com.example.calendarex

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*
import org.w3c.dom.Text
import java.lang.StringBuilder
import java.util.*

class CalendarAdapter(
    private val list: List<LinearLayout>
) :
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    private val isBindingMap: MutableMap<Int, Boolean> = hashMapOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        for (i in 0 until list.size) {
            isBindingMap[i] = false
        }

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.calendar_frame.removeAllViews()
        holder.itemView.calendar_frame.addView(list[position])


    }


}