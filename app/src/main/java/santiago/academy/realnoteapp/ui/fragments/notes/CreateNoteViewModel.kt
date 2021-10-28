package santiago.academy.realnoteapp.ui.fragments.notes

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import santiago.academy.realnoteapp.databinding.FragmentCreateNoteBinding
import santiago.academy.realnoteapp.db.Note

class CreateNoteViewModel : ViewModel(){

    val title : MutableLiveData<String> = MutableLiveData()
    val description : MutableLiveData<String> = MutableLiveData()
    val content : MutableLiveData<String> = MutableLiveData()

    fun getNoteValues(binding: FragmentCreateNoteBinding){
        title.value = binding.tiTitle.text.toString()
        description.value = binding.tiDescription.text.toString()
        content.value = binding.tiContent.text.toString()
    }

    fun assignNoteValues(mViewLifecycleOwner: LifecycleOwner, note: Note){
        title.observe(mViewLifecycleOwner, {
            note.title = it
        })

        description.observe(mViewLifecycleOwner, {
            note.description = it
        })

        content.observe(mViewLifecycleOwner, {
            note.content = it
        })
    }

}