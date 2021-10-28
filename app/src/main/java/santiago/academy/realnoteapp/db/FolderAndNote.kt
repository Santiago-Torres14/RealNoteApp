package santiago.academy.realnoteapp.db

import androidx.room.Embedded
import androidx.room.Relation

data class FolderAndNote(
    @Embedded val folder: Folder,
    @Relation(
        parentColumn = "folder_id",
        entityColumn = "folder_owner"
    )
    val notes: List<Note>
)