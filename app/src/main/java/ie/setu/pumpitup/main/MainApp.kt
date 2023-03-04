package ie.setu.pumpitup.main

import android.app.Application
import android.util.Log.i
import ie.setu.pumpitup.models.PumpItMemStore
import ie.setu.pumpitup.models.PumpModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val stations = PumpItMemStore()


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("PumpItUp started")
        // stations.add(PumpModel("One", "About one..."))
        //stations.add(PumpModel("Two", "About two..."))
        //stations.add(PumpModel("Three", "About three..."))

    }
}