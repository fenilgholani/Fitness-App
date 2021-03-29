package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var bottomNavigationView : BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar))
//
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.action_profile -> {
//                val intent = Intent(this@MainActivity, SignInActivity::class.java)
//                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_history -> {
//                val intent = Intent(this@MainActivity, SignInActivity::class.java)
//                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_workout -> {
                val intent = Intent(this@MainActivity, TodayExerciseActivity::class.java)
                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.action_information -> {
                val intent = Intent(this@MainActivity, InfoActivity::class.java)
                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}