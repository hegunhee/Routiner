package routiner.core.data.db

import android.content.Context
import androidx.room.Room

object InMemoryDBProvider {

    fun provideInMemoryDB(context: Context): RoutinerDatabase {
        return Room.inMemoryDatabaseBuilder(
            context, RoutinerDatabase::class.java
        ).build()
    }

}
