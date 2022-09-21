package by.ssrlab.NASB.data

import android.app.Application
import bby.ssrlab.NASB.retrofit.ApiService
import bby.ssrlab.NASB.retrofit.ServiceCreator
import by.ssrlab.NASB.data.Language
import by.ssrlab.NASB.data.items.Exhibit
import by.ssrlab.NASB.data.items.Section


import by.ssrlab.tibo2019.util.addTo
import by.ssrlab.tibo2019.util.executeInBack
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class Repository2(app: Application, private var lang: Language) {
    private val apiService = ServiceCreator.createService(ApiService::class.java)
    private val subscriptions = CompositeDisposable()
    private var allSections = listOf<Section>()
    val sections = BehaviorSubject.create<List<Section>>().apply { onNext(listOf()) }
    private var allExhibits = listOf<Exhibit>()
    val exhibits = BehaviorSubject.create<List<Exhibit>>().apply { onNext(listOf()) }
    val selectedSection = BehaviorSubject.create<Section>()
    val selectedExhibit = BehaviorSubject.create<Exhibit>()

    var selectedSectionID = ""
        set(value) {
            field = value
            refreshData()
        }

    var selectedExhibitID = ""
        set(value) {
            field = value
            refreshData()
        }

    init {
        loadData()
    }

    private fun refreshData() {
        sections.onNext(allSections.filter { it.lang == lang.value })
        allExhibits.find { it.idPoint.toString() == selectedExhibitID && it.lang == lang.value }?.let {
            selectedExhibit.onNext(it)
        }
        allSections.find { it.lang == lang.value && it.id == selectedSectionID }?.let {sec ->
            selectedSection.onNext(sec)
            exhibits.onNext(allExhibits.filter { it.cityId == sec.id && it.lang == lang.value })
        }
    }

    fun setLanguage(language: Language) {
        lang = language
        refreshData()
    }

    fun loadData() {
        execute(apiService.getSections()){secs ->
            execute(apiService.getExhibits()){exs ->
                allSections = secs
                allExhibits = exs
                refreshData()
            }
        }
    }

    private fun<T> execute(request : Observable<T>, onNext : (T) -> Unit){
        request.executeInBack().subscribe({
            onNext.invoke(it)
        }, {
            it.printStackTrace()
        }).addTo(subscriptions)
    }

    fun clear() {
        subscriptions.clear()
    }
}