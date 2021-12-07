package santiago.academy.realnoteapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import santiago.academy.realnoteapp.db.Note
import santiago.academy.realnoteapp.db.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    val note : LiveData<Note> get() = _note
    private var _note: MutableLiveData<Note> = MutableLiveData()

    fun insertNote(vararg notes: Note) = viewModelScope.launch{
        noteRepository.insertNotes(*notes)
    }

    val notes : LiveData<List<Note>> get() = noteRepository.getNotes()

    fun getNote(noteId: Long) = noteRepository.getNote(noteId)

    fun getNote(noteTitle: String) = noteRepository.getNote(noteTitle)

    fun updateNote(vararg notes: Note) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.updateNotes(*notes)
    }

    fun getNoteValues(note: Note){
        _note.value = note
    }
}