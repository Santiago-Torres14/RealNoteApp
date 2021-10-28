package santiago.academy.realnoteapp.ui.fragments.notes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import santiago.academy.realnoteapp.R
import santiago.academy.realnoteapp.databinding.FragmentCreateNoteBinding
import santiago.academy.realnoteapp.db.Note
import santiago.academy.realnoteapp.viewmodels.NoteViewModel

@AndroidEntryPoint
class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {

    private var _binding : FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel: NoteViewModel by viewModels()
    private val createNoteViewModel: CreateNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNoteViewModel.getNoteValues(binding)
    }

    private fun saveNoteToDb(note: Note){
        noteViewModel.insertNote(note)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.check_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.check_badge -> {
                createNoteViewModel.getNoteValues(binding)
                var note = Note(title="", description = "", content = "")
                createNoteViewModel.assignNoteValues(viewLifecycleOwner, note)
                saveNoteToDb(note)
                findNavController().navigate(R.id.nav_to_home)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}