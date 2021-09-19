package com.peteralexbizjak.ljbikes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peteralexbizjak.ljbikes.api.models.stations.StationModel
import com.peteralexbizjak.ljbikes.databinding.ItemStationBinding

internal class SearchFragmentAdapter : RecyclerView.Adapter<SearchFragmentAdapter.ViewHolder>() {
    private val stationsList = mutableListOf<StationModel>()

    inner class ViewHolder(
        private val binding: ItemStationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: StationModel) {
            binding.apply {
                stationInfoTitle = "${model.stationName} - No. ${model.stationID}"
                stationAddress = model.stationAddress
                availableBikes = model.availableBikes.toString()
                availableParking = (model.totalStands - model.availableBikes).toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStationBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stationsList[position])
    }

    override fun getItemCount(): Int {
        return stationsList.size
    }

    fun setNewStationsList(newStationsList: List<StationModel>) {
        if (stationsList.isEmpty()) {
            stationsList.addAll(newStationsList.sortedBy { it.stationName })
            notifyItemRangeChanged(0, newStationsList.size)
        } else {
            val oldStationListLength = stationsList.size
            stationsList.clear()
            notifyItemRangeRemoved(0, oldStationListLength)

            stationsList.addAll(newStationsList.sortedBy { it.stationName })
            notifyItemRangeChanged(0, newStationsList.size)
        }
    }
}