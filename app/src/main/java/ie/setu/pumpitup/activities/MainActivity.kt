package ie.setu.pumpitup.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ie.setu.pumpitup.R
import ie.setu.pumpitup.models.PumpModel
import ie.setu.pumpitup.databinding.PumpActivityMainBinding
import ie.setu.pumpitup.helpers.resIdByName
import ie.setu.pumpitup.main.MainApp
import ie.setu.pumpitup.models.Users

import timber.log.Timber.i


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: PumpActivityMainBinding
    var pump = PumpModel()
    lateinit var app: MainApp

    var courses = arrayOf<String?>("Select Station","Amber", "Apple Green",
        "Circle K", "Inver",
        "Morris", "Shell","Texaco","Top Oil","Emo")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit = false

        binding = PumpActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        i("Main Activity started...")
        ImageHashMap.loadImages()

        if (intent.hasExtra("station_edit")) {
            edit=true
            pump = intent.extras?.getParcelable("station_edit")!!
            binding.station.setText(pump.station)
            binding.petrol.setText(pump.petrol.toString())
            binding.diesel.setText(pump.diesel.toString())
            binding.eircode.setText(pump.eircode)
            binding.address.setText(pump.address)
            binding.btnAdd.setText(R.string.edit_activity)
            //    binding.image.getImage(pump.image)
        }

        // val stations = app.stations.loadStations(this)

        binding.btnAdd.setOnClickListener() {
            pump.station = binding.station.text.toString()
            pump.petrol = binding.petrol.text.toString().toDouble()
            pump.diesel = binding.diesel.text.toString().toDouble()
            pump.eircode = binding.eircode.text.toString()
            pump.address = binding.address.text.toString()
            pump.image= ImageHashMap.getImage(binding.coursesspinner.selectedItem.toString()).toString()



            if (pump.station.isNotEmpty()  && pump.eircode.isNotEmpty() && binding.petrol.text.toString().isNotEmpty() && binding.diesel.text.toString().isNotEmpty() ) {
                if (edit) {
                    app.stations.update(pump.copy())
                } else {
                    app.stations.create(pump.copy())
                }
                app.stations.saveStations(this, app.stations.findAll())
                i("add Button Pressed: $pump")
                setResult(RESULT_OK)
                finish()

            } else {
                Snackbar
                    .make(it, R.string.enter_station_title, Snackbar.LENGTH_LONG)
                    .show()
            }




        }


        // Get reference to the button
        val cancelButton = findViewById<Button>(R.id.activity_cancelAddPump)
        cancelButton.background = ContextCompat.getDrawable(this, R.drawable.button_background)

        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.setColor(Color.WHITE)
        shape.setStroke(10, Color.WHITE)
        shape.cornerRadius = 50f
        cancelButton.background = shape


        // Set an OnClickListener for the button
        cancelButton.setOnClickListener {
            // Code to return to the main menu
            val mainMenuIntent = Intent(this, PumpListActivity::class.java)
            startActivity(mainMenuIntent)
        }

//        SPINNER FOR STATION NAMES THAT WILL
//        ALSO ASSIGN CORRECT PETROL STATION IMAGE TO THAT ITEM ADDED

        val spin = findViewById<Spinner>(R.id.coursesspinner)
        spin.onItemSelectedListener = this

        // Create the instance of ArrayAdapter
        // having the list of courses
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses)

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spin.adapter = ad
        spin.setSelection(0)

        // app.stations.saveStations(this, app.stations.findAll())
    }

    override fun onItemSelected(parent: AdapterView<*>?,
                                view: View, position: Int,
                                id: Long) {
        // make toastof name of course
        // which is selected in spinner
        Toast.makeText(applicationContext,
            courses[position],
            Toast.LENGTH_LONG)
            .show()
        binding.station.setText(courses[position])
        //val drawableResId = this.resIdByName(imageName, "drawable")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}



}


