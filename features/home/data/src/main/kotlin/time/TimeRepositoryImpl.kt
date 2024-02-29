package time

import repositories.TimeRepository

class TimeRepositoryImpl(
    val timeService: TimeService
): TimeRepository {
    override fun getUnixTime(): Long {
        return timeService.getTime()
    }
}