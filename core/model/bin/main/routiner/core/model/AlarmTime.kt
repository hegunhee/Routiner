package routiner.core.model

data class AlarmTime(
    val hour : String,
    val minute : String,
    val enableAlarm : Boolean = hour.isNotEmpty() || hour.isNotEmpty()
) {
    fun toTimeStamp() : String {
        return if(!enableAlarm) {
            ""
        }else {
            "${hour}:${minute}"
        }
    }

    companion object {
        val DEFAULT = AlarmTime(hour = "00", minute = "00", enableAlarm = false)
    }
}