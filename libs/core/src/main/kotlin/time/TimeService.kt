package time

import javax.inject.Inject

class TimeService @Inject constructor(){
    fun getTime(): Long {
        // Сходили на сервер, узнали...
        return System.currentTimeMillis()
    }
}