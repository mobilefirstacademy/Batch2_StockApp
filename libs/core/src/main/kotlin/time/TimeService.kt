package time

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeService @Inject constructor(){
    fun getTime(): Long {
        // Сходили на сервер, узнали...
        return System.currentTimeMillis()
    }
}