package santiago.academy.realnoteapp.db

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDAO: NoteDAO ){

    fun getNote(noteId: Long) = noteDAO.getNote(noteId)

    fun getNote(noteTitle: String) = noteDAO.getNote(noteTitle)

    fun getNotes() = noteDAO.getNotes()

    suspend fun insertNotes(vararg notes: Note) = noteDAO.insertNotes(*notes)

    suspend fun updateNotes(vararg notes: Note) = noteDAO.updateNotes(*notes)
}