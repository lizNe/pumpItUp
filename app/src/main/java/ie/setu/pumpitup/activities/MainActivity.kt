package ie.setu.pumpitup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ie.setu.pumpitup.R
import ie.setu.pumpitup.models.PumpModel
import ie.setu.pumpitup.databinding.PumpActivityMainBinding

import ie.setu.pumpitup.main.MainApp

import timber.log.Timber
import timber.log.Timber.i

val imagesList: ArrayList<Pair<String, Int>> = arrayListOf(
    Pair("Amber",R.drawable.amber),
    Pair("AppleGreen",R.drawable.apple),
    Pair("Circle-K",R.drawable.circlek),
    Pair("Inver",R.drawable.inver),
    Pair("Morris",R.drawable.morris),
    Pair("Shell",R.drawable.shell),
    Pair("Texaco",R.drawable.texaco),
    Pair("Top Oil", R.drawable.top),
    // Add more images as needed
)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: PumpActivityMainBinding
    var pump = PumpModel()
    lateinit var app: MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PumpActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        i("Main Activity started...")

        binding.btnAdd .setOnClickListener() {
            pump.station = binding.station.text.toString()
            pump.petrol = binding.petrol.text.toString()
            pump.diesel = binding.diesel.text.toString()
            pump.eircode = binding.eircode.text.toString()
            pump.address = binding.address.text.toString()

            if (pump.station.isNotEmpty()&& pump.petrol.isNotEmpty() && pump.diesel.isNotEmpty() && pump.eircode.isNotEmpty()) {
                app.stations.add(pump.copy())
                i("add Button Pressed: $pump")
                for (i in app.stations.indices) {
                    i("Station[$i]:${this.app.stations[i]}")
                }
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                    .make(it,"Please enter the name of the Station", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        // Get reference to the button
        val cancelButton = findViewById<Button>(R.id.activity_cancelAddPump)
            cancelButton.background = ContextCompat.getDrawable(this, R.drawable.button_background)

// Set an OnClickListener for the button
        cancelButton.setOnClickListener {
            // Code to return to the main menu
            val mainMenuIntent = Intent(this, PumpListActivity::class.java)
            startActivity(mainMenuIntent)
        }

    }

    fun getImage(title: String): Int {
        for (pair in imagesList) {
            if (pair.first == title) {
                return pair.second
            }
        }
        return 0
    }

}
