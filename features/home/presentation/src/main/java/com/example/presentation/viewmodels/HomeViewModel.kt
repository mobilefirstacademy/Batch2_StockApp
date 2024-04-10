package com.example.presentation.viewmodels

import androidx.fragment.app.FragmentManager
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

    fun letsGo(name: String?, fragmentManager: FragmentManager) {
        // If ... and ...
        router.goTo_letsGo(name ?: "Anonymous", fragmentManager)
    }
}
