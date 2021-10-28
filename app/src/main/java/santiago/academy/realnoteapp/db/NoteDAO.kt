package santiago.academy.realnoteapp.db


import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(vararg notes: Note)

    @Delete
    suspend fun deleteNotes(vararg notes: Note)

    @Update
    suspend fun updateNotes(vararg notes: Note)

    @Query("SELECT * FROM note_table ORDER BY created_at DESC")
    fun getNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE note_id = :noteId")
    fun getNote(noteId: Long): LiveData<Note>

    @Query("SELECT * FROM note_table WHERE title LIKE :noteParameter OR description LIKE :noteParameter")
    fun getNote(noteParameter: String): LiveData<List<Note>>
}