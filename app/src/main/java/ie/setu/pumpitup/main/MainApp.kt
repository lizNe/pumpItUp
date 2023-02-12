package ie.setu.pumpitup.main

import android.app.Application
import android.util.Log.i
import ie.setu.pumpitup.models.PumpModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

        val stations = ArrayList<PumpModel>()

        override fun onCreate() {
            super.onCreate()
            Timber.plant(Timber.DebugTree())
            i("Placemark started")
            // placemarks.add(PlacemarkModel("One", "About one..."))
            //placemarks.add(PlacemarkModel("Two", "About two..."))
            //placemarks.add(PlacemarkModel("Three", "About three..."))

        }
}