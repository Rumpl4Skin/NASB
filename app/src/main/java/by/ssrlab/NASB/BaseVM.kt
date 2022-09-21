package by.ssrlab.NASB

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import by.ssrlab.NASB.data.Repository2
import io.reactivex.disposables.CompositeDisposable

abstract class BaseVM : ViewModel() {
    val toolbarTitle = MutableLiveData<String>()
    private lateinit var mainVM : MainActivityViewModel
    protected val subs = CompositeDisposable()

    protected val repo : Repository2?
        get() = mainVM.repo

    protected val nav : NavController?
        get() = mainVM.navController

    fun setMainVM(mvm : MainActivityViewModel){
        mainVM = mvm
        onCreate()
    }

    protected abstract fun onCreate()

    override fun onCleared() {
        super.onCleared()
        subs.clear()
    }
}