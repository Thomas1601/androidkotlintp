package com.example.premiertp

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.premiertp.databinding.ActivityMainBinding
import com.example.premiertp.fragments.FavouritesFragment
import com.example.premiertp.fragments.HomeFragment
import com.example.premiertp.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ActivityMainBinding
    private lateinit var username: String

    fun viewNoteDetail(userName:String) {
        val intent = Intent(this, MainTweet::class.java)
        intent.putExtra("userName", userName)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userTab = arrayOf("Test", "Jules", "Thomas", "Yo", "test", "jules", "JUL")
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        // get reference to button
        val button = binding.button
        val button2 = binding.button2
        val homeFragment = HomeFragment()
        val favouritesFragment = FavouritesFragment()
        val settingsFragment = SettingsFragment()

        makeCurrentFragment(homeFragment)
        var bot_nav : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bot_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> {
                    makeCurrentFragment(homeFragment)
                    val mainTitle = binding.textView
                    mainTitle.visibility = View.VISIBLE
                    mainTitle.setTextColor(Color.parseColor("#FFFFFF"))
                    binding.button.visibility = View.VISIBLE
                    binding.button2.visibility = View.VISIBLE
                    binding.editText.visibility = View.VISIBLE
                    mainTitle.text = "Accueil"
                    //val tweetDao = TweetDatabase.getInstance(application).tweetDao()
                    //val newTweet = Tweet(title = "#6200EE", description = "purple")
                    //tweetDao.insert(newTweet)

                }
                R.id.ic_favorite -> {
                    makeCurrentFragment(favouritesFragment)
                    val mainTitle = binding.textView
                    mainTitle.visibility = View.VISIBLE
                    mainTitle.setTextColor(Color.parseColor("#000000"))
                    binding.button.visibility = View.INVISIBLE
                    binding.button2.visibility = View.INVISIBLE
                    binding.editText.visibility = View.INVISIBLE
                    mainTitle.text = "Mes favs"
                    //val intent = Intent(Intent.ACTION_WEB_SEARCH)
                    //intent.putExtra(SearchManager.QUERY, "Dominos")
                    //if (intent.resolveActivity(packageManager) != null) {
                    //startActivity(intent)
                }
                R.id.ic_settings -> {
                    makeCurrentFragment(settingsFragment)
                    val mainTitle = binding.textView
                    mainTitle.visibility = View.VISIBLE
                    mainTitle.setTextColor(Color.parseColor("#FF0000"))
                    binding.button.visibility = View.INVISIBLE
                    binding.button2.visibility = View.INVISIBLE
                    binding.editText.visibility = View.INVISIBLE
                    mainTitle.text = "Params"
                }
            }
            true
        }


        // set on-click listener
        button.setOnClickListener {
            val mainText = binding.editText
            val textSortie = mainText.text.toString()
            if(textSortie in userTab){
                Toast.makeText(this@MainActivity, "Yo $textSortie !!", 5).show()
                viewNoteDetail(textSortie)
            }else{
                Toast.makeText(this@MainActivity, "User not in DB!", 5).show()
            }
        }

        button2.setOnClickListener {
            var mainText = binding.editText
            mainText.setText("")
        }

        //bPizza.setOnClickListener {
         //   val intent = Intent(Intent.ACTION_WEB_SEARCH)
          //  intent.putExtra(SearchManager.QUERY, "Pizza")
            //startActivity(intent)
        //}

        //val bKebab = findViewById<Button>(R.id.button4)
        //bKebab.setOnClickListener {
          //  val intent = Intent(Intent.ACTION_WEB_SEARCH)
            //intent.putExtra(SearchManager.QUERY, "Kebab")
            //startActivity(intent)
        //}

        //val bBurger = findViewById<Button>(R.id.button5)
        //bBurger.setOnClickListener {
         //   val intent = Intent(Intent.ACTION_WEB_SEARCH)
          //  intent.putExtra(SearchManager.QUERY, "Burger")
           // startActivity(intent)
        //}
    }

    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper, fragment)
            commit()

        }
    suspend fun get(url: String) {

        // Start on Dispatchers.Main

        withContext(Dispatchers.IO) {
            // Switches to Dispatchers.IO
            // Perform blocking network IO here
        }

        // Returns to Dispatchers.Main
    }



}