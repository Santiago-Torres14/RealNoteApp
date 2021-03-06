package santiago.academy.realnoteapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import santiago.academy.realnoteapp.R
import santiago.academy.realnoteapp.databinding.FragmentHomeBinding
import santiago.academy.realnoteapp.db.Note
import santiago.academy.realnoteapp.ui.adapters.NoteAdapter
import santiago.academy.realnoteapp.viewmodels.NoteViewModel

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : NoteViewModel by viewModels()
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.notes.observe(viewLifecycleOwner,{
            noteAdapter.submitList(it)
        })
    }

    private fun getNoteFromAdapter(note: Note){
        val bundle = Bundle()
        bundle.putLong("noteId", note.noteId)
        findNavController().navigate(R.id.nav_to_create_note, bundle)
    }


    private fun setupRecyclerView() = binding.noteRv.apply{
        noteAdapter = NoteAdapter(this@HomeFragment::getNoteFromAdapter)
        adapter = noteAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

}