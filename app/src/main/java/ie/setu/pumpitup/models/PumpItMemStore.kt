package ie.setu.pumpitup.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long{
    return lastId++
}

class PumpItMemStore : PumpItStore {
    val stations = ArrayList<PumpModel>()

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
            logAll()
        }
    }

    fun logAll() {
        stations.forEach{ i("$it") }
    }
}

