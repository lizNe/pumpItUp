package ie.setu.pumpitup.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.navigation.NavigationView
import ie.setu.pumpitup.R
import ie.setu.pumpitup.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var activityDashboardBinding: ActivityDashboardBinding

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

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation item selected events here
        return true
    }
}

//https://www.youtube.com/watch?v=pRieCkF1Yts