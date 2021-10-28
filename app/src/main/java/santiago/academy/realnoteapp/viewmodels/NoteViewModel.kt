package santiago.academy.realnoteapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import santiago.academy.realnoteapp.db.Note
import santiago.academy.realnoteapp.db.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val noteRepository: NoteRepository) : ViewModel() {
    fun insertNote(vararg notes: Note) = viewModelScope.launch{
        noteRepository.insertNotes(*notes)
    }

    val notes : LiveData<List<Note>> get() = noteRepository.getNotes()

    fun getNote(noteId: Long) = noteRepository.getNote(noteId)

    fun getNote(noteTitle: String) = noteRepository.getNote(noteTitle)

}