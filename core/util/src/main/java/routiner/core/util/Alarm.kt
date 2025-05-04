package routiner.core.util

object Alarm {

    fun getHourList() : List<String> {
        return (0..23).map {
            if(it < 10) "0$it" else "$it"
        }
    }

    fun getMinuteList() : List<String> {
        return listOf("00","15","30","45")
    }
}