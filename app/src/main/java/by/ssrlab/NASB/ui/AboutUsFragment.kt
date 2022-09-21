package by.ssrlab.NASB.ui


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import by.ssrlab.NASB.MainActivityViewModel
import by.ssrlab.NASB.R
import by.ssrlab.NASB.data.Language
import by.ssrlab.NASB.databinding.FragmentAboutUsBinding
import java.util.*

class AboutUsFragment : Fragment() {

    companion object {
        fun newInstance() = AboutUsFragment()
    }

    private val PREFS_NAME = "NASB_PREFS"
    private val LANGUAGE = "Language"

    private lateinit var viewModel: AboutUsViewModel

    private var _binding: FragmentAboutUsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val root: View = binding.root


//        viewModel= ViewModelProviders.of(this).get(AboutUsViewModel::class.java)
//
//        viewModel.lang = when (activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)?.getInt(LANGUAGE, 0)) {
//            2 -> Language.ENG
//            else -> Language.BEL
//        }
//        val locale = Locale(viewModel.lang.name)
//        Locale.setDefault(locale)
//// Create a new configuration object
//        val config = Configuration()
//// Set the locale of the new configuration
//        config.locale = locale
//// Update the configuration of the Accplication context
//        resources.updateConfiguration(
//            config,
//            resources.displayMetrics
//        )

        val htmlTaggedString = this.getString(R.string.about_us)
        val textSpan = Html.fromHtml(htmlTaggedString)
        binding.txtAboutUs.text=textSpan



        return root
    }

}