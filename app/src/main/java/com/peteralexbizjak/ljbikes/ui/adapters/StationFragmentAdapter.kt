package com.peteralexbizjak.ljbikes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peteralexbizjak.ljbikes.api.models.bikes.BikeModel
import com.peteralexbizjak.ljbikes.databinding.ItemBikeBinding
import java.time.Duration
import java.time.LocalDateTime

internal class StationFragmentAdapter(
    private val bikesList: List<BikeModel>,

    ) : RecyclerView.Adapter<StationFragmentAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: ItemBikeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BikeModel) {
            binding.itemBikeRatingBar.rating = ((model.bikeRating.score * 3) / 100).toFloat()
            binding.apply {
                standNumber = model.standNumber.toString()
                bikeNumber = model.bikeNumber.toString()
                ratingsNumber = model.bikeRating.numberOfRatings.toString()
                ratingsDate = formatLastRatingDate(model.bikeRating.lastRating)
            }
        }

        private fun formatLastRatingDate(rawDate: String): String {
            val parsedDate = LocalDateTime.parse(rawDate)
            val currentDate = LocalDateTime.now()

            val difference = Duration.between(parsedDate, currentDate)
            val days = difference.toDays()
            return if (days == 0L) {
                "Last ${difference.toHours()} hours ago"
            } else {
                if (days <= 7) "Last $days days ago" else "Last week+ ago"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBikeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bikesList[position])
    }

    override fun getItemCount(): Int {
        return bikesList.size
    }
}