package by.ssrlab.NASB

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.ssrlab.NASB.app.App
import by.ssrlab.NASB.app.ContextWrapper
import by.ssrlab.NASB.data.Language
import by.ssrlab.NASB.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var model: MainActivityViewModel

    private val PREFS_NAME = "NASB_PREFS"
    private val LANGUAGE = "Language"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //set to viewModel
        model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        model.app = Application()
        model.languageManager = LanguageManager(model)
        model.language = model.languageManager?.readLanguage()!!
       //model.languageManager?.writeLocale(model.language!!)
        model.repo?.setLanguage(model.language!!)
        model.loadData()



        val lang: Language= when (getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getInt(LANGUAGE, 1)) {
            2 -> Language.ENG
            else -> Language.BEL
        }
        val locale = Locale(lang.name)
        Locale.setDefault(locale)
// Create a new configuration object
        val config = Configuration()
// Set the locale of the new configuration
        config.locale = locale
// Update the configuration of the Accplication context
        resources.updateConfiguration(
            config,
            resources.displayMetrics
        )

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        model.navController=navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_engineering, R.id.navigation_history,
                R.id.navigation_settings,R.id.navigation_map,
                R.id.aboutUsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



    }

    override fun onBackPressed() {
        if(findNavController(R.id.nav_host_fragment_activity_main).currentDestination?.id==R.id.aboutUsFragment)
            findNavController(R.id.nav_host_fragment_activity_main).navigateUp()
        super.onBackPressed()
    }
   /* override fun onRestart() {
        super.onRestart()
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_engineering, R.id.navigation_history,
                R.id.navigation_settings,R.id.navigation_map,
                R.id.aboutUsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }*/


   /*override fun onPause() {
        super.onPause()
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_engineering, R.id.navigation_history,
                R.id.navigation_settings,R.id.navigation_map,
                R.id.aboutUsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }*/

    fun setLanguage(lang : Language){
        model.language = lang
        recreate()
    }

    fun changeLang(lang: Locale) {
        Locale.setDefault(lang)
        val config = Configuration()
        config.locale = lang
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        saveLocale(lang)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //recreate()
        overridePendingTransition(0, 0)
    }

    private fun saveLocale(lang: Locale) {
        val langPref = LANGUAGE
        val prefs = getSharedPreferences(PREFS_NAME, 0)
        val editor = prefs.edit()
        editor.putInt(langPref,when (lang.language){"be"->Language.BEL.value
        else -> Language.ENG.value})
        editor.apply()
    }

  /* override fun attachBaseContext(newBase: Context) {
            var context = ContextWrapper.wrap(newBase, LanguageManager(App.INSTANSE!!).readLanguage().locale)
            super.attachBaseContext(context)
    }*/
}