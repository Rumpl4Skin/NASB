package by.ssrlab.tibo2019.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import by.ssrlab.NASB.data.items.Exhibit
import by.ssrlab.NASB.data.items.Section
import by.ssrlab.NASB.data.room.AppDao


@Database(
        entities = [Section::class, Exhibit::class],
        version = 1, exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract val appDao: AppDao

    companion object {
        var name = "bible_db_v2"
    }
}
