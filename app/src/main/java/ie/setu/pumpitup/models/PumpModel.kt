package ie.setu.pumpitup.models

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import android.content.Context
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException

@Parcelize
data class PumpModel( var id: Long = 0,
                      var station: String = "",
                      var petrol: Double = 0.0,
                      var diesel: Double = 0.0,
                      var eircode: String = "",
                      var address: String = "",
                      var image: String = ""

) : Serializable, Parcelable

private const val STATIONS_FILE = "stations.json"

// Load the list of stations from the JSON file
fun loadStations(context: Context): MutableList<PumpModel> {
    val file = File(context.filesDir, STATIONS_FILE)
    if (!file.exists()) {
        return mutableListOf()
    }
    val json = file.readText()
    val type = object : TypeToken<List<PumpModel>>() {}.type
    return Gson().fromJson(json, type)
}

// Save the list of stations to the JSON file
fun saveStations(context: Context, stations: List<PumpModel>) {
    val file = File(context.filesDir, STATIONS_FILE)
    val json = Gson().toJson(stations)
    try {
        file.writeText(json)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

