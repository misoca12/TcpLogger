package com.misoca.tcplogger

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misoca.tcplogger.databinding.ItemTcpLogBinding

class TcpLogAdapter(): RecyclerView.Adapter<TcpLogAdapter.BindingHolder>(){

    var logs: List<TcpLog> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TcpLogAdapter.BindingHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTcpLogBinding.inflate(inflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val log = logs[position]
        holder.binding.log = log
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return logs.size
    }

    class BindingHolder(var binding: ItemTcpLogBinding): RecyclerView.ViewHolder(binding.root){
    }
}