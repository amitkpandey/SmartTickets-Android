package com.ivanasen.smarttickets.ui.adapters

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ivanasen.smarttickets.R
import com.ivanasen.smarttickets.db.models.TicketType

internal class TicketTypeAdapter(val context: Context, val data: LiveData<MutableList<TicketType>>)
    : RecyclerView.Adapter<TicketTypeAdapter.ViewHolder>() {

    private val mData = mutableListOf<TicketType>()

    init {
        data.observe(context as LifecycleOwner, Observer {
            swapData(it ?: mutableListOf())
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_ticket_creation,
                parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ticketPriceTextView.text =
                String.format("%.2f", mData[position].priceInUSDCents.toDouble() / 100)
        holder.ticketSupplyTextView.text =
                String.format("%d", mData[position].initialSupply)
        holder.ticketRefundable.text =
                if (mData[position].refundable)
                    context.getString(R.string.refundable_text)
                else context.getString(R.string.not_refundable_text)
    }


    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var ticketPriceTextView = view.findViewById<TextView>(R.id.ticketPriceTextView)
        var ticketSupplyTextView = view.findViewById<TextView>(R.id.ticketSupplyTextView)
        var ticketRefundable = view.findViewById<TextView>(R.id.refundableView)
    }

    private fun swapData(@Nullable data: List<TicketType>) {
        if (mData != data) {
            mData.clear()
            mData.addAll(data)
            notifyDataSetChanged()
        }
    }

}