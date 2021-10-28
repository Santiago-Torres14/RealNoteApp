package santiago.academy.realnoteapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import santiago.academy.realnoteapp.db.Folder
import santiago.academy.realnoteapp.db.FolderRepository
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor(val folderRepository: FolderRepository): ViewModel() {

    fun insertFolders(vararg folder: Folder) = viewModelScope.launch { folderRepository.insertFolders(*folder) }

    val folders : LiveData<List<Folder>> get() = folderRepository.getFolders()

    fun getFolder(folderId: Long) = folderRepository.getFolder(folderId)

}