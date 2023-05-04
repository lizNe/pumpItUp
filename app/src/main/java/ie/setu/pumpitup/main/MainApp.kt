package ie.setu.pumpitup.main

import android.app.Application
import android.content.Intent
import android.util.Log.i
import com.google.firebase.FirebaseApp
import ie.setu.pumpitup.activities.LoginActivity
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

    fun logout() {
        // Clear the current user session here
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}