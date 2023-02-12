package ie.setu.pumpitup.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.setu.pumpitup.databinding.PumpCardBinding
import ie.setu.pumpitup.models.PumpModel

class PumpAdapters constructor(private var stations: List<PumpModel>) :
    RecyclerView.Adapter<PumpAdapters.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = PumpCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val station = stations[holder.adapterPosition]
        holder.bind(station)
    }

    override fun getItemCount(): Int = stations.size

    class MainHolder(private val binding : PumpCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(station: PumpModel) {
            binding.stationName.text = station.station
            binding.petrolPrice.text = station.petrol
            binding.dieselPrice.text = station.diesel
            binding.eircode.text = station.eircode
            binding.address.text = station.address
        }
    }
}