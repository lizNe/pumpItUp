package ie.setu.pumpitup.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.pumpitup.R
import ie.setu.pumpitup.adapters.PumpAdapters
import ie.setu.pumpitup.adapters.PumpItListener
import ie.setu.pumpitup.databinding.PumpActivityListBinding
import ie.setu.pumpitup.main.MainApp
import ie.setu.pumpitup.models.PumpModel



class PumpListActivity: AppCompatActivity(), PumpItListener {

    lateinit var app: MainApp
    private lateinit var binding: PumpActivityListBinding
    var stations = ArrayList<PumpModel>()



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, MainActivity::class.java)
                getResult.launch(launcherIntent)
            }
            R.id.logout_button -> {
                // Logout
                app.logout()
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }


    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(
                    0,
                    app.stations.findAll().size
                )
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
        binding.recyclerView.adapter = PumpAdapters(app.stations.findAll(), this)

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

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
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(
                    0,
                    app.stations.findAll().size
                )
            }
        }


    private fun deleteStation(station: PumpModel, position: Int) {
        app.stations.delete(station)
        app.stations.saveStations(this,app.stations.findAll())
        (binding.recyclerView.adapter as PumpAdapters).notifyItemRemoved(position)

    }


    val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            deleteStation(app.stations.findAll()[position], position)
        }
    }
//https://github.com/mitchtabian/SQLite-for-Beginners-2019/blob/master/app/src/main/java/com/codingwithmitch/notes/NotesListActivity.java



}







