package santiago.academy.realnoteapp.ui.fragments.folder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import santiago.academy.realnoteapp.R
import santiago.academy.realnoteapp.databinding.FragmentFolderBinding
import santiago.academy.realnoteapp.ui.adapters.FolderAdapter
import santiago.academy.realnoteapp.viewmodels.FolderViewModel

@AndroidEntryPoint
class FolderFragment : Fragment(R.layout.fragment_folder) {

    private var _binding : FragmentFolderBinding? = null
    private val binding get() = _binding!!
    private val folderViewModel : FolderViewModel by viewModels()
    private lateinit var folderAdapter: FolderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFolderBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        folderViewModel.folders.observe(viewLifecycleOwner, {
            folderAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() = binding.folderRv.apply{
        folderAdapter = FolderAdapter()
        adapter = folderAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

}