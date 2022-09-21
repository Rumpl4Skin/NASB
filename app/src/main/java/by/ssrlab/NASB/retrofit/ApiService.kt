package bby.ssrlab.NASB.retrofit

import by.ssrlab.NASB.data.items.Exhibit
import by.ssrlab.NASB.data.items.Section
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("get_sections/0/")
    fun getSections(): Observable<List<Section>>

    @GET("get_exhibits/0/")
    fun getExhibits(): Observable<List<Exhibit>>
}