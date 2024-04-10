package routing

import androidx.fragment.app.FragmentManager

interface TempRouter {
    fun goTo_letsGo(name: String, manager: FragmentManager)
}