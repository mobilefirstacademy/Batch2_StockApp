package repositories

interface TimeRepository {
    fun getUnixTime(): Long
}
