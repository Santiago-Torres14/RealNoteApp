package santiago.academy.realnoteapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(
    tableName = "note_table",
    foreignKeys = [ForeignKey(entity = Folder::class,
        parentColumns = arrayOf("folder_id"),
        childColumns = arrayOf(
        "folder_owner"))]
)
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var noteId: Long = 0,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @NotNull
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "created_at") var created_at: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "folder_owner") var folderId: Long? = null
)
