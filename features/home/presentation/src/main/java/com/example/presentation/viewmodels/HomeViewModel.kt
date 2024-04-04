package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.presentation.viewmodels.time.Time
import com.example.presentation.viewmodels.time.TimeLivedata
import dagger.hilt.android.lifecycle.HiltViewModel
import interactors.HomeInteractor
import routing.TempRouter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val router: TempRouter,
    interactor: HomeInteractor,
): ViewModel() {

    private val _timeLivedata = TimeLivedata(interactor, Time("Push to refresh ->"))
    val timeLivedata: LiveData<Result<Time>> = _timeLivedata

    fun refreshTime() {
        _timeLivedata.refresh()
    }

    fun letsGo(name: String?) {
        // If ... and ...
        router.goTo_letsGo(name ?: "Anonymous")
    }

//    class Factory(
//        private val router: TempRouter,
//        private val interactor: HomeInteractor,
//    ) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//                return HomeViewModel(router, interactor) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }
}
