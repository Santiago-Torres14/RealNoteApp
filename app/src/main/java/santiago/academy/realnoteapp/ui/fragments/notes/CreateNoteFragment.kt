package santiago.academy.realnoteapp.ui.fragments.notes

import android.os.Bundle
import android.text.Editable
import android.util.Log
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = arguments?.let { noteViewModel.getNote(it.getLong("noteId")) }

        note?.let{
            it.observe(viewLifecycleOwner, {
                binding.tiTitle.text = Editable.Factory.getInstance().newEditable(it.title)
                binding.tiContent.text = Editable.Factory.getInstance().newEditable(it.content)
                binding.tiDescription.text = Editable.Factory.getInstance().newEditable(it.description)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.check_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.check_badge -> {
                val mNoteId = arguments?.getLong("noteId")
                val title = binding.tiTitle.text.toString()
                val description = binding.tiDescription.text.toString()
                val content = binding.tiContent.text.toString()
                if(mNoteId != null){
                    val note = Note(noteId = mNoteId, title = title, description = description, content = content)
                    noteViewModel.getNoteValues(note)
                    noteViewModel.note.value?.let { noteViewModel.updateNote(it) }
                    findNavController().navigate(R.id.nav_to_home)
                } else{
                    Log.d("SantiagoId", "NEW OBJECT")
                    val note = Note(title = title, description = description, content = content)
                    noteViewModel.getNoteValues(note)
                    noteViewModel.note.value?.let{ noteViewModel.insertNote(it) }
                    findNavController().navigate(R.id.nav_to_home)
                }
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