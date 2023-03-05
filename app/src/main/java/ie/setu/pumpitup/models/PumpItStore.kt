package ie.setu.pumpitup.models

interface PumpItStore {
    fun findAll(): List<PumpModel>
    fun create(station:PumpModel)
    fun update(station: PumpModel)
    fun delete(station: PumpModel)


}