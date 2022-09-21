package by.ssrlab.NASB

import android.app.Application
import android.content.Context
import by.ssrlab.NASB.data.Language

class LanguageManager(private val model: MainActivityViewModel) {

    private val PREFS_NAME = "NASB_PREFS"
    private val LANGUAGE = "Language"

    fun readLanguage(): Language {
        try {
        return when (model.app?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)?.getInt(LANGUAGE, 1)) {
            2 -> Language.ENG
            else -> Language.BEL
        }
        }
        catch (e:NullPointerException){
            return Language.BEL
        }
    }

    fun writeLocale(lang: Language) {
        try{
        model.app?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)?.edit()?.putInt(LANGUAGE, lang.value)?.commit()
        }
        catch (e:NullPointerException){}
    }
}