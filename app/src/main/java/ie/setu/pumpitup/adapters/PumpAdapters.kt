package ie.setu.pumpitup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ie.setu.pumpitup.R
import ie.setu.pumpitup.databinding.PumpCardBinding
import ie.setu.pumpitup.helpers.resIdByName
import ie.setu.pumpitup.models.PumpModel


interface PumpItListener {
    fun onPumpClick(station: PumpModel)
}


class PumpAdapters constructor(private var stations: List<PumpModel>, private val listener: PumpItListener) :
    RecyclerView.Adapter<PumpAdapters.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = PumpCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val station = stations[holder.adapterPosition]
        holder.bind(station, listener)
        
    }

    fun setData(newData: List<PumpModel>) {
        stations = newData
    }


    override fun getItemCount(): Int = stations.size

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
}
