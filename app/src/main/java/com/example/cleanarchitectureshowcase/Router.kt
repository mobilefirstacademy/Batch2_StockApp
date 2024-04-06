package com.example.cleanarchitectureshowcase

import androidx.fragment.app.FragmentManager
import com.example.letsgo.LetsGoFragment
import routing.TempRouter

object Router: TempRouter {
    private lateinit var fragmentManager: FragmentManager

    fun addFragmentManager(manager: FragmentManager) {
        fragmentManager = manager
    }
    override fun goTo_letsGo(name: String) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment, LetsGoFragment.newInstance(name))
            .addToBackStack(null)
            .commit()
    }
}