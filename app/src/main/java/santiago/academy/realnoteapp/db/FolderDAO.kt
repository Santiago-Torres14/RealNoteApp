package santiago.academy.realnoteapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FolderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolders(vararg folders: Folder)

    @Delete
    suspend fun deleteFolders(vararg folders: Folder)

    @Update
    suspend fun updateFolders(vararg folders: Folder)

    @Transaction
    @Query("SELECT * FROM folder_table")
    fun getFoldersAndNote(): LiveData<List<FolderAndNote>>

    @Query("SELECT * FROM folder_table ORDER BY created_at DESC")
    fun getFolders(): LiveData<List<Folder>>

    @Transaction
    @Query("SELECT * FROM folder_table WHERE folder_id = :folderId")
    fun getFolder(folderId: Long): LiveData<FolderAndNote>
}