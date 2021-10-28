package santiago.academy.realnoteapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "folder_table"
)
data class Folder(
    @ColumnInfo(name = "folder_id") @PrimaryKey(autoGenerate = true) val folderId : Long = 0,
    @ColumnInfo(name = "name") val folderName: String,
    @ColumnInfo(name = "created_at") val created_at: Calendar = Calendar.getInstance()
)