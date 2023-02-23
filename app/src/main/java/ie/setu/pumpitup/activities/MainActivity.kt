package ie.setu.pumpitup.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ie.setu.pumpitup.R
import ie.setu.pumpitup.databinding.PumpActivityListBinding
import ie.setu.pumpitup.models.PumpModel
import ie.setu.pumpitup.databinding.PumpActivityMainBinding
import ie.setu.pumpitup.helpers.resIdByName
import ie.setu.pumpitup.main.MainApp
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

        binding = PumpActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        binding = PumpActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        i("Main Activity started...")
        ImageHashMap.loadImages()
        binding.btnAdd.setOnClickListener() {
            pump.station = binding.station.text.toString()
            pump.petrol = binding.petrol.text.toString().toDouble()
            pump.diesel = binding.diesel.text.toString().toDouble()
            pump.eircode = binding.eircode.text.toString()
            pump.address = binding.address.text.toString()
            pump.image =
                ImageHashMap.getImage(binding.coursesspinner.selectedItem.toString()).toString()



            if (pump.station.isNotEmpty() && pump.eircode.isNotEmpty() && binding.petrol.text.toString()
                    .isNotEmpty() && binding.diesel.text.toString().isNotEmpty()
            ) {
                app.stations.add(pump.copy())
                i("add Button Pressed: $pump")
                for (i in app.stations.indices) {
                    i("Station[$i]:${this.app.stations[i]}")
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar
                    .make(it, "Please enter the name of the Station", Snackbar.LENGTH_LONG)
                    .show()
            }
        }





//            val shape = GradientDrawable()
//            shape.shape = GradientDrawable.RECTANGLE
//            shape.setColor(Color.WHITE)
//            shape.setStroke(10, Color.WHITE)
//            shape.cornerRadius = 50f
//            cancelButton.background = shape




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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }






}






