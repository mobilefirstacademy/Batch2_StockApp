package time

class TimeService {
    fun getTime(): Long {
        // Сходили на сервер, узнали...
        return System.currentTimeMillis()
    }
}