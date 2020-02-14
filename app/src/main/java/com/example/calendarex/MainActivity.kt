package com.example.calendarex

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calender_recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        val list: MutableList<LinearLayout> = emptyList<LinearLayout>().toMutableList()

        val monthArr = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        monthArr.forEach { month ->
            list.add(makeCalendar(2019, month, this@MainActivity))
        }
        calender_recyclerView.adapter = CalendarAdapter(list)
    }

    private fun getDayToString(today: Int): String? {
        var todayString: String? = null
        when (today) {
            1 -> todayString = "일"
            2 -> todayString = "월"
            3 -> todayString = "화"
            4 -> todayString = "수"
            5 -> todayString = "목"
            6 -> todayString = "금"
            7 -> todayString = "토"
        }
        return todayString
    }

    private fun makeCalendar(year: Int, mon: Int, con: Context): LinearLayout {

        val linearParam = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val param = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.MATCH_PARENT,
            1f
        )

        val frame = LinearLayout(con)
        frame.orientation = LinearLayout.VERTICAL

//        frame.addView(monthText)
        var lineNum = 0
        val layoutList = arrayListOf(
            LinearLayout(con),
            LinearLayout(con),
            LinearLayout(con),
            LinearLayout(con),
            LinearLayout(con),
            LinearLayout(con),
            LinearLayout(con),
            LinearLayout(con)
        )
        /////////////////////////////////////copy
        val c = Calendar.getInstance()
        print("Enter month  (quit : enter 0): ")
        val month = mon - 1
        Log.e("month", month.toString())
        c.set(year, month, 1)
        val maxDate: Int = c.getActualMaximum(Calendar.DAY_OF_MONTH)
        var saturday: Int? = null
        // 달력 그림
        for (i in 1..maxDate) {
            val firstDay = c.get(Calendar.DAY_OF_WEEK)
            val blank = StringBuilder()
            if (i == 1) {
                val monthText = TextView(con)
                monthText.layoutParams = ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
//                monthText.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
//                monthText.setBackgroundColor(Color.GRAY)
                monthText.gravity = Gravity.CENTER
                monthText.text = "#$mon#"
                layoutList[lineNum].layoutParams = linearParam
//                layoutList[lineNum].setBackgroundColor(Color.BLUE)
                layoutList[lineNum].addView(monthText)
                lineNum++

                println((firstDay))
                println("일\t월\t화\t수\t목\t금\t토")
                for (k in 1..7) {
                    val days = TextView(con)
                    days.text = getDayToString(k)
                    days.layoutParams = param
                    layoutList[lineNum].addView(days)
                }
                lineNum++
//                val days = TextView(con)
//                days.text = con.getString(R.string.days)

                for (j in 0 until firstDay - 1) {
                    blank.append(" \t")
                    val blankText = TextView(con)
                    blankText.layoutParams = param
                    blankText.text = " "
                    layoutList[lineNum].addView(blankText)
                    if (j == firstDay - 2) {
                        print(blank.toString())
                        break
                    }
                }
                saturday = if (firstDay < 7) 8 - firstDay
                else 1
                Log.e("saturday", saturday.toString())
            }
            print("$i\t")
            val date = TextView(con)
            date.layoutParams = param
            date.text = "$i "
            layoutList[lineNum].addView(date)
            if (i == saturday) {
                println()
                saturday += 7
                lineNum++
            }
        }
        println()
        ///////////////////////////////copy
        layoutList.forEach {
//            Log.e("childCount", it.childCount.toString())
            if (it.childCount != 0) {
                if (it.childCount == 7)
                    frame.addView(it)
                else {
                    for (i in 0 until (7 - it.childCount)) {
                        val blank = TextView(con)
                        blank.text = " "
                        blank.layoutParams = param
                        it.addView(blank)
                    }
                    frame.addView(it)
                }
            }
        }
        val view = View(con)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            1
        )
        view.setBackgroundColor(Color.BLACK)

        frame.addView(view)

        return frame
    }

}
