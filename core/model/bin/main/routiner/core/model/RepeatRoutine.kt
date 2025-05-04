package routiner.core.model

data class RepeatRoutine(
    var text : String = "",
    val dayOfWeekList: List<String>,
    val category : String = ""
) {
    fun containsDayOfWeek(dayOfWeek : String) : Boolean{
        return dayOfWeekList.contains(dayOfWeek)
    }
}