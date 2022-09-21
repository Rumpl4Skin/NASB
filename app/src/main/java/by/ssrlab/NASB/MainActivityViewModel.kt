package by.ssrlab.NASB

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import by.ssrlab.NASB.data.Language
import by.ssrlab.NASB.data.Repository2


class MainActivityViewModel : ViewModel() {
    var app: Application? =null
    var languageManager = LanguageManager(this)
    var language = languageManager.readLanguage()
        set(value) {
            field = value
            if (value != null) {
                languageManager?.writeLocale(value)
            }
            if (value != null) {
                repo?.setLanguage(value)
            }
        }

    var navController: NavController? =null
    val repo = app?.let { language?.let { it1 -> Repository2(it, it1) } }

    open fun loadData(){
        repo?.loadData()
    }
    override fun onCleared() {
        super.onCleared()
    }
}