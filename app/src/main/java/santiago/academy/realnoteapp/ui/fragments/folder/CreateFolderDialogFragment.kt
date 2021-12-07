package santiago.academy.realnoteapp.ui.fragments.folder

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import santiago.academy.realnoteapp.R
import santiago.academy.realnoteapp.db.Folder
import santiago.academy.realnoteapp.viewmodels.FolderViewModel

@AndroidEntryPoint
class CreateFolderDialogFragment() : DialogFragment() {

    private val folderViewModel: FolderViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater.inflate(R.layout.dialog_create_folder, null)

            builder.setView(inflater)
                .setPositiveButton("create"
                ) { dialog, id ->
                    val folderName = inflater.findViewById<EditText>(R.id.et_create_folder).text.toString()
                    val folder = Folder(folderName = folderName)
                    folderViewModel.insertFolders(folder)
                }
                .setNegativeButton("cancel"
                ) { dialog, id ->
                    getDialog()?.cancel()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}