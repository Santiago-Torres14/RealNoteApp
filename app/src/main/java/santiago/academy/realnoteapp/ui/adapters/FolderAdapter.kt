package santiago.academy.realnoteapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import santiago.academy.realnoteapp.databinding.FolderItemBinding
import santiago.academy.realnoteapp.db.Folder

class FolderAdapter : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>(){

    inner class FolderViewHolder(val binding: FolderItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<Folder>(){
        override fun areContentsTheSame(oldItem: Folder, newItem: Folder): Boolean {
            return oldItem.folderId == newItem.folderId
        }

        override fun areItemsTheSame(oldItem: Folder, newItem: Folder): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    fun submitList(list: List<Folder>) = differ.submitList(list)

    override fun onBindViewHolder(holder: FolderAdapter.FolderViewHolder, position: Int) {
        val folder = differ.currentList[position]

        holder.itemView.apply{
            val title = folder.folderName
            holder.binding.titleFolderTv.text = title

            val createdAt = folder.created_at.time
            holder.binding.timeFolderTv.text = createdAt.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderAdapter.FolderViewHolder {
        val binding = FolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FolderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}