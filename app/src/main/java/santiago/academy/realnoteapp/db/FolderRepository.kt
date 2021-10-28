package santiago.academy.realnoteapp.db

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FolderRepository @Inject constructor(private val folderDAO: FolderDAO){

    fun getFolder(folderId: Long) = folderDAO.getFolder(folderId)

    fun getFolders() = folderDAO.getFolders()

    suspend fun updateFolders(vararg folders: Folder) = folderDAO.updateFolders(*folders)

    suspend fun deleteFolders(vararg folders: Folder) = folderDAO.deleteFolders(*folders)

    suspend fun insertFolders(vararg folders: Folder) = folderDAO.insertFolders(*folders)
}