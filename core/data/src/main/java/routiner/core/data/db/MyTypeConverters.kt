package routiner.core.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson

class MyTypeConverters {

    @TypeConverter
    fun fromStringList(value : List<String>?) : String = Gson().toJson(value)

    @TypeConverter
    fun toStringList(value : String) = Gson().fromJson(value,Array<String>::class.java).toList()
}