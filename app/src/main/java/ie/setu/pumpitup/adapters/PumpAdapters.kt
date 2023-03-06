package ie.setu.pumpitup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ie.setu.pumpitup.R
import ie.setu.pumpitup.databinding.PumpCardBinding
import ie.setu.pumpitup.helpers.resIdByName
import ie.setu.pumpitup.models.PumpModel
import java.util.*
import kotlin.collections.ArrayList


interface PumpItListener {
    fun onPumpClick(station: PumpModel)
}


class PumpAdapters constructor(private var stations: List<PumpModel>, private val listener: PumpItListener) :
    RecyclerView.Adapter<PumpAdapters.MainHolder>(), Filterable{

    private var filteredStations: List<PumpModel> = stations

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = PumpCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val station = filteredStations[holder.adapterPosition]
        holder.bind(station, listener)
        
    }

    fun setData(newData: List<PumpModel>) {
        stations = newData
    }


    override fun getItemCount(): Int = filteredStations.size


    class MainHolder(private val binding: PumpCardBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(station: PumpModel, listener: PumpItListener) {
            binding.stationName.text = station.station
            binding.petrolPrice.text = station.petrol.toString()
            binding.dieselPrice.text = station.diesel.toString()
            binding.eircode.text = station.eircode
            binding.address.text = station.address
            binding.root.setOnClickListener { listener.onPumpClick(station) }

            val imageName = station.image

            try {
                val drawableResId = context.resIdByName(imageName, "drawable")
                if(drawableResId!= 0)
                binding.imageView2.setImageResource(drawableResId)
                else
                    binding.imageView2.setImageResource(R.drawable.pump)

            } catch(e: Exception){
                binding.imageView2.setImageResource(R.drawable.pump)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<PumpModel>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(stations)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                    for (station in stations) {
                        if (station.station.toLowerCase(Locale.ROOT).contains(filterPattern)
                            || station.address.toLowerCase(Locale.ROOT).contains(filterPattern)
                            || station.eircode.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            filteredList.add(station)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredStations = results?.values as List<PumpModel>
                notifyDataSetChanged()
            }
        }
    }
}
//Filter / Search reference
// https://www.youtube.com/watch?v=K5YnTvsVPRk

