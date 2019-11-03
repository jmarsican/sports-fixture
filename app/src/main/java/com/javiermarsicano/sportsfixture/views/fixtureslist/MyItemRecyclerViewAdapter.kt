package com.javiermarsicano.sportsfixture.views.fixtureslist

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.os.ConfigurationCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.javiermarsicano.sportsfixture.R
import com.javiermarsicano.sportsfixture.data.models.Fixture
import com.javiermarsicano.sportsfixture.data.models.STATE
import kotlinx.android.synthetic.main.entry_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class MyItemRecyclerViewAdapter(private var mValues: MutableList<Fixture> = mutableListOf())
    : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>()  {

    private lateinit var mContext: Context

    val DAYS_POSTPONED = 3 // This info should be provided with fixture api web services

    fun setItemsList(newValues: MutableList<Fixture>) {
        val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return mValues.get(oldItemPosition).id == newValues[newItemPosition].id
            }

            override fun getOldListSize() = mValues.size

            override fun getNewListSize() = newValues.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val newItem = newValues[newItemPosition]
                val oldItem = mValues[oldItemPosition]
                return (newItem.id == oldItem.id
                        && newItem.date == oldItem.date)
            }

        })

        mValues = newValues
        result.dispatchUpdatesTo(this)
    }

    fun clear() {
        val count = itemCount
        mValues.clear()
        notifyItemRangeRemoved(0, count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.entry_item, parent, false)

        mContext = view.context

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.divisor.visibility = VISIBLE
        holder.weekDay.visibility = VISIBLE
        holder.date.visibility = VISIBLE
        holder.awayResult.visibility = GONE
        holder.homeResult.visibility = GONE


        val item = mValues[position]
        holder.competition.text = item.competitionStage?.competition?.name
        holder.matchData.text = item.date
        holder.awayTeam.text = item.awayTeam?.name
        holder.homeTeam.text = item.homeTeam?.name


        holder.postponed.visibility = if (item.state == STATE.POS.value) VISIBLE else GONE

        val locale = ConfigurationCompat.getLocales(mContext.resources.configuration)[0]
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", locale).parse(item.date)

        holder.weekDay.text = SimpleDateFormat("EEE", locale).format(date)

        val cal = Calendar.getInstance().apply {
            time = date
            if (item.state == STATE.POS.value) add(Calendar.DATE, DAYS_POSTPONED)
        }
        holder.date.text = cal.get(Calendar.DAY_OF_MONTH).toString()

        val dateLabel = SimpleDateFormat("MMM dd, YYYY 'at' HH:mm", locale).format(date)
        val matchInfo = item.venue?.name + " | " + dateLabel
        val span = SpannableStringBuilder(matchInfo)
        if (item.state == STATE.POS.value) {
            span.setSpan(ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.alert)),
                    matchInfo.length - dateLabel.length,
                    matchInfo.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
        holder.matchData.text = span
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val competition = mView.competition
        val matchData = mView.match_place_time
        val awayTeam = mView.away_team
        val homeTeam = mView.home_team
        val date = mView.date_day
        val weekDay = mView.week_day
        val homeResult = mView.home_result
        val awayResult = mView.away_result
        val divisor = mView.division2
        val postponed = mView.postponed

        override fun toString(): String {
            return super.toString() + " '" + matchData.text + "'"
        }
    }
}