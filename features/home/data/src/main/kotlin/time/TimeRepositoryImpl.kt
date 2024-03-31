package time

import repositories.TimeRepository
import javax.inject.Inject

class TimeRepositoryImpl @Inject constructor(
    val timeService: TimeService
): TimeRepository {
    override fun getUnixTime(): Long {
        return timeService.getTime()
    }
}