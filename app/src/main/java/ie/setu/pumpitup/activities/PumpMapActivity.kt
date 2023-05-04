package ie.setu.pumpitup.activities

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ie.setu.pumpitup.R

import ie.setu.pumpitup.databinding.ActivityPumpMapBinding
import ie.setu.pumpitup.databinding.ContentPumpMapBinding
import ie.setu.pumpitup.helpers.resIdByName
import ie.setu.pumpitup.main.MainApp

class PumpMapActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

    private lateinit var binding: ActivityPumpMapBinding
    private lateinit var contentBinding: ContentPumpMapBinding
    lateinit var map: GoogleMap
    lateinit var app: MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        app = application as MainApp

        binding = ActivityPumpMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        contentBinding = ContentPumpMapBinding.bind(binding.root)
        contentBinding.mapView.onCreate(savedInstanceState)

        contentBinding.mapView.getMapAsync {
            map = it
            configureMap()
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        contentBinding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        contentBinding.mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        contentBinding.mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        contentBinding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        contentBinding.mapView.onSaveInstanceState(outState)
    }

    private fun configureMap() {
        map.uiSettings.isZoomControlsEnabled = true
        app.stations.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions()
                .title(it.station)
                .position(loc)
                .snippet("Petrol: ${it.petrol}, Diesel: ${it.diesel}, Eircode: ${it.eircode}")
            map.addMarker(options)?.tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))

        }
        map.setOnMarkerClickListener(this)

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        contentBinding.currentStation.text = marker.title

        // get the petrol and diesel prices from the marker's snippet
        val prices = marker.snippet?.split(",")?.map { it.trim() }
        val petrolPrice = prices?.get(0)?.substringAfter(":")?.toDoubleOrNull()
        val dieselPrice = prices?.get(1)?.substringAfter(":")?.toDoubleOrNull()
        val eircode = prices?.get(2)?.substringAfter(":")?.trim()


        // set the petrol and diesel prices to the respective TextView
        contentBinding.currentDiesel.text = dieselPrice?.toString() ?: "N/A"
        contentBinding.currentPetrol.text = petrolPrice?.toString() ?: "N/A"
        contentBinding.currentEircode.text = eircode ?: "N/A"

        val station = app.stations.findById(marker.tag as Long)
        if (station != null) {
            val imageResId = resIdByName(station.image, "drawable")
            contentBinding.imageView3.setImageResource(imageResId)
        }


        return false
    }


}