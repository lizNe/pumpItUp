package ie.setu.pumpitup.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import ie.setu.pumpitup.R
import ie.setu.pumpitup.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var activityDashboardBinding: ActivityDashboardBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(activityDashboardBinding.root)


        // Set up the toolbar
        setSupportActionBar(activityDashboardBinding.toolbar)


// Set up the navigation drawer toggle
        val toggle = ActionBarDrawerToggle(
            this, activityDashboardBinding.drawerLayout, activityDashboardBinding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.setDrawerIndicatorEnabled(true)
        toggle.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.black)
        activityDashboardBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

// Set up the navigation view
        activityDashboardBinding.navView.setNavigationItemSelectedListener(this)
        drawerLayout = activityDashboardBinding.drawerLayout
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.first_item -> {
                // Handle main screen
                val newI = Intent(this, MainActivity::class.java )
                startActivity(newI)
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.second_item -> {
                // Handle settings screen
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            // Handle other menu items
            else -> return false
        }
    }
}

//https://www.youtube.com/watch?v=pRieCkF1Yts