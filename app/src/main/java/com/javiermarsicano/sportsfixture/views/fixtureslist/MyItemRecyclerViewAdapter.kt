package com.javiermarsicano.sportsfixture.views.fixtureslist

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.javiermarsicano.sportsfixture.R
import com.javiermarsicano.sportsfixture.data.models.Fixture
import kotlinx.android.synthetic.main.entry_item.view.*

class MyItemRecyclerViewAdapter(private var mValues: MutableList<Fixture> = mutableListOf())
    : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>()  {

    private lateinit var mContext: Context

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
        val item = mValues[position]
        holder.mName.text = item.state
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mName: TextView = mView.entry_title

        override fun toString(): String {
            return super.toString() + " '" + mName.text + "'"
        }
    }
}