package com.nemochen.digitalpasswallet.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nemochen.digitalpasswallet.databinding.StreampassElementBinding
import com.nemochen.digitalpasswallet.model.StreamPass
import com.nemochen.digitalpasswallet.util.TimeDisplayUtil

class StreamPassAdapter: ListAdapter<StreamPass, RecyclerView.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<StreamPass>() {
        override fun areItemsTheSame(oldItem: StreamPass, newItem: StreamPass): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: StreamPass, newItem: StreamPass): Boolean {
            return (oldItem.serialNumber == newItem.serialNumber)
        }
    }

    class ViewHolder(var binding: StreampassElementBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(streamPass: StreamPass) {
            binding.streampassElement = streamPass
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val streamPassBinding = StreampassElementBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(streamPassBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(getItem(position).apply {
                    if (insertionTime == Long.MIN_VALUE) {
                        insertionTime = System.currentTimeMillis()
                    }

                    if (durationDisplayString.isEmpty()) {
                        durationDisplayString = TimeDisplayUtil.getDurationDisplayString(duration)
                    }

                    statusDisplayString = getStatusDisplayString(activeTime, expirationTime)

                } as StreamPass)
            }
        }
    }

    private fun getStatusDisplayString(activeTime: Long, expirationTime: Long): String {
        if (activeTime == Long.MIN_VALUE) {
            return "Swipe to active"
        }

        return if (isExpired(expirationTime)) {
            "Expired"
        } else {
            "Expired at: " + TimeDisplayUtil.dataTimeFormatter.format(expirationTime)
        }
    }

    private fun isExpired(time: Long): Boolean {
        return time < System.currentTimeMillis()
    }



    @BindingAdapter("streamPassElements")
    fun bindRecyclerViewWithSteamPassList(recyclerView: RecyclerView, elementList: MutableList<StreamPass>) {
        elementList.let {
            recyclerView.adapter?.apply {
                when (this) {
                    is StreamPassAdapter -> {
                        submitList(it)
                        notifyItemChanged(0, it.size)
                    }
                }
            }
        }
    }

}