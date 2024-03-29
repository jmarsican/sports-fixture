package com.javiermarsicano.sportsfixture.views.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
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
import com.javiermarsicano.sportsfixture.views.models.Fixture
import com.javiermarsicano.sportsfixture.views.models.STATE
import com.javiermarsicano.sportsfixture.views.models.Score
import kotlinx.android.synthetic.main.divisor.view.*
import kotlinx.android.synthetic.main.entry_item.view.*
import java.text.SimpleDateFormat
import java.util.*

private const val USER_FRIENDLY_DATE_FORMAT = "MMM dd, YYYY 'at' HH:mm"
private const val WEEKDAY_FORMAT = "EEE" //Wednesday

class MyItemRecyclerViewAdapter(private var mValues: MutableList<Fixture> = mutableListOf())
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mContext: Context

    private val daysPostponed = 3 // This info should be provided with fixture api web services

    private val typeDivisor = "DIVISOR"

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

        mValues = getListGroupedByMonth(newValues)

        result.dispatchUpdatesTo(this)
    }

    private fun getListGroupedByMonth(newValues: MutableList<Fixture>): MutableList<Fixture> {
        val otherList = mutableListOf<Fixture>()

        mValues = newValues.sortedWith(compareBy {
            it.formatedDate
        }).toMutableList()

        if (mValues.size > 0) {
            otherList.add(Fixture(id = 0, type = typeDivisor, formatedDate = mValues[0].formatedDate))
            otherList.add(mValues[0])

            for (index in 1 until mValues.size - 1) {
                otherList.add(mValues[index])

                val month1 = Calendar.getInstance().apply {
                    time = mValues[index].formatedDate
                }.get(Calendar.MONTH)
                val month2 = Calendar.getInstance().apply {
                    time = mValues[index + 1].formatedDate
                }.get(Calendar.MONTH)

                if (month1 != month2) {
                    otherList.add(Fixture(id = 0, type = typeDivisor, formatedDate = mValues[index + 1].formatedDate))
                }
            }
        }
        return otherList
    }

    override fun getItemViewType(position: Int): Int {
        return if (mValues[position].type == typeDivisor) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.entry_item, parent, false)

            mContext = view.context

            return FixtureViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.divisor, parent, false)

            mContext = view.context

            return DivisorViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mValues[position]

        if (holder.itemViewType == 1) {
            holder as DivisorViewHolder
            holder.label.text = SimpleDateFormat("MMMM YYYY", Locale.US).format(item.formatedDate) //September 2018

        } else {
            holder as FixtureViewHolder

            if (item.state != STATE.FINISHED.value) {
                showPreMatchViews(holder)
                hideResultsView(holder)
            }

            holder.competition.text = item.competitionStage?.competition?.name
            holder.matchData.text = item.date
            holder.awayTeam.text = item.awayTeam?.name
            holder.homeTeam.text = item.homeTeam?.name

            holder.postponed.visibility = if (item.state == STATE.POS.value) VISIBLE else GONE

            holder.matchData.text = getMatchDataLabel(item)

            if (item.state != STATE.FINISHED.value) {
                showPreMatchDateInfo(holder, item)
            } else {
                showTeamsScores(item.score, holder)
            }
        }
    }

    private fun showPreMatchDateInfo(holder: FixtureViewHolder, item: Fixture) {
        holder.weekDay.text = SimpleDateFormat(WEEKDAY_FORMAT, Locale.US).format(item.formatedDate)

        val cal = Calendar.getInstance().apply {
            time = item.formatedDate
            if (item.state == STATE.POS.value) add(Calendar.DATE, -daysPostponed)
        }
        holder.date.text = cal.get(Calendar.DAY_OF_MONTH).toString()
    }

    private fun showTeamsScores(score: Score?, holder: FixtureViewHolder) {
        holder.setIsRecyclable(false)
        holder.awayResult.text = score?.away.toString()
        holder.homeResult.text = score?.home.toString()

        val winnerColor = ContextCompat.getColor(mContext, R.color.colorPrimary)
        score?.let {
            if (score.away > score.home) {
                holder.awayResult.setTextColor(winnerColor)
            } else {
                holder.homeResult.setTextColor(winnerColor)
            }
        }
    }

    private fun getMatchDataLabel(item: Fixture): SpannableStringBuilder {
        val dateLabel = SimpleDateFormat(USER_FRIENDLY_DATE_FORMAT, Locale.US).format(item.formatedDate)
        val matchInfo = item.venue?.name + " | " + dateLabel
        val span = SpannableStringBuilder(matchInfo)
        if (item.state == STATE.POS.value) {
            span.setSpan(ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.alert)),
                    matchInfo.length - dateLabel.length,
                    matchInfo.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
        return span
    }

    private fun hideResultsView(holder: FixtureViewHolder) {
        holder.awayResult.visibility = GONE
        holder.homeResult.visibility = GONE
    }

    private fun showPreMatchViews(holder: FixtureViewHolder) {
        holder.divisor.visibility = VISIBLE
        holder.weekDay.visibility = VISIBLE
        holder.date.visibility = VISIBLE
    }

    override fun getItemCount(): Int = mValues.size

    fun filter(text: String) {
        setItemsList(mValues.filter {
            it.competitionStage?.competition?.name?.toUpperCase() == text.toUpperCase()
        }.toMutableList())
    }

    inner class FixtureViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
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
    inner class DivisorViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val label = mView.label

        override fun toString(): String {
            return super.toString() + " '" + label.text + "'"
        }
    }
}