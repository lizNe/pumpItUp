package ie.setu.pumpitup.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.pumpitup.R
import ie.setu.pumpitup.adapters.PumpAdapters
import ie.setu.pumpitup.adapters.PumpItListener
import ie.setu.pumpitup.databinding.PumpActivityListBinding
import ie.setu.pumpitup.main.MainApp
import ie.setu.pumpitup.models.PumpModel


class PumpListActivity: AppCompatActivity(), PumpItListener {

    lateinit var app: MainApp
    private lateinit var binding: PumpActivityListBinding


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, MainActivity::class.java)
                getResult.launch(launcherIntent)
            }

            R.id.logout_button -> {
                var id: Int = item.getItemId()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                getResult.launch(intent)
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
        }



    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.stations.findAll().size)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PumpActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        app.stations.loadStations(this)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = PumpAdapters(app.stations.findAll(),this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPumpClick(station: PumpModel) {
        val launcherIntent = Intent(this, MainActivity::class.java)
        launcherIntent.putExtra("station_edit", station as Parcelable)
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.stations.findAll().size)
            }
        }




}


