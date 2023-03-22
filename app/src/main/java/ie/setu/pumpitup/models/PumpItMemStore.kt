package ie.setu.pumpitup.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber.i
import java.io.File
import java.io.IOException

var lastId = 0L
private const val STATIONS_FILE = "stations.json"

internal fun getId(): Long{
    return lastId++
}

class PumpItMemStore  : PumpItStore {
    var stations = ArrayList<PumpModel>()

    override fun findAll(): List<PumpModel> {
        return stations
    }

    override fun create(station: PumpModel) {
        station.id = getId()
        stations.add(station)
        logAll()
    }

    override fun update(station: PumpModel) {
        var foundStation: PumpModel? = stations.find { s -> s.id == station.id }
        if (foundStation != null) {
            foundStation.station = station.station
            foundStation.petrol = station.petrol
            foundStation.diesel = station.diesel
            foundStation.eircode = station.eircode
            foundStation.address = station.address
            foundStation.image = station.image
            foundStation.lat = station.lat
            foundStation.lng = station.lng
            foundStation.zoom = station.zoom
            logAll()
        }
    }

    override fun delete(station: PumpModel) {
        stations.remove(station)
        logAll()
    }

    // Load the list of stations from the JSON file
    fun loadStations(context: Context): MutableList<PumpModel> {
        val file = File(context.filesDir, STATIONS_FILE)
        if (!file.exists()) {
            return mutableListOf()
        }
        val json = file.readText()
        val type = object : TypeToken<List<PumpModel>>() {}.type

        //   need to call station and assign the gson and json file to it to actually display the stations
        stations = Gson().fromJson(json, type)
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

    fun logAll() {
        stations.forEach{ i("$it") }
    }
}

