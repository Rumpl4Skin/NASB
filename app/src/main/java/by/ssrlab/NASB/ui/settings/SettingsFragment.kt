package by.ssrlab.NASB.ui.settings

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.ssrlab.NASB.MainActivityViewModel
import by.ssrlab.NASB.R
import by.ssrlab.NASB.SettingsItemsRecyclerAdapter
import by.ssrlab.NASB.data.Language
import by.ssrlab.NASB.databinding.FragmentSettingsBinding
import by.ssrlab.NASB.ui.language.ChooseLanguageDialog
import java.util.*


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val PREFS_NAME = "NASB_PREFS"
    private val LANGUAGE = "Language"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                SettingsViewModel::class.java
            )

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.settingsRV
        recyclerView.layoutManager = LinearLayoutManager(context)


        val stateClickListener: SettingsItemsRecyclerAdapter.OnStateClickListener = object : SettingsItemsRecyclerAdapter.OnStateClickListener {
            override fun onStateClick(settingsName: String?, position: Int) {
                val navController = activity?.let {
                    Navigation.findNavController(
                        it,
                        R.id.nav_host_fragment_activity_main
                    )
                }
                when(position){
                    0->{
                        val newFragment = ChooseLanguageDialog()
                        activity?.let { newFragment.show(it.supportFragmentManager, "lang dialog") }
                    }
                    1-> navController?.navigate(R.id.action_navigation_settings_to_aboutUsFragment)
                }

            }
        }
        var imgList= listOf(R.drawable.ic_baseline_language_24,R.drawable.ic_baseline_info_24)
        recyclerView.adapter = SettingsItemsRecyclerAdapter(getSettingsNameList(), imgList,stateClickListener)

        //activity Model
      /*  val model = activity?.let { ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }

        settingsViewModel.language= model?.language?.locale!!
        //change lang
        var lang=settingsViewModel.language
        Locale.setDefault(lang)
        val config = Configuration()
        config.locale = lang

        activity?.baseContext?.resources?.updateConfiguration(config, activity?.baseContext!!.resources.displayMetrics)*/

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val lang: Language= when (activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)?.getInt(LANGUAGE, 1)) {
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
    }

    override fun onResume() {

        val lang: Language = when (activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)?.getInt(LANGUAGE, 1)) {
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
        super.onResume()
    }
   override fun onPause() {
        super.onPause()

    /*    //activity Model
        val model = activity?.let { ViewModelProviders.of(it).get(MainActivityViewModel::class.java) }

        //change lang
        var lang=model?.language?.locale
        Locale.setDefault(lang)
        val config = Configuration()
        config.locale = lang

        activity?.baseContext?.resources?.updateConfiguration(config, activity?.baseContext!!.resources.displayMetrics)*/
    }

    private fun getSettingsNameList(): List<String> {
        return this.resources.getStringArray(R.array.settings_item).toList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}