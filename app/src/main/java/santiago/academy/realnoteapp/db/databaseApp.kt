package santiago.academy.realnoteapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Note::class, Folder::class], version = 1)
@TypeConverters(Converters::class)
abstract class DatabaseApp : RoomDatabase(){
    abstract fun getNoteDao(): NoteDAO
    abstract fun getFolderDao(): FolderDAO
}