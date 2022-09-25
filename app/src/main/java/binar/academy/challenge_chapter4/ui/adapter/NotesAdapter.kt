package binar.academy.challenge_chapter4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import binar.academy.challenge_chapter4.databinding.ItemNotesBinding
import binar.academy.challenge_chapter4.data.model.Notes
import binar.academy.challenge_chapter4.ui.fragment.notes.HomeFragmentDirections

class NotesAdapter(private var onClick : NotesAdapter.NotesInterface) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var diffCallback = object : DiffUtil.ItemCallback<Notes>(){
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id_notes == newItem.id_notes
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class ViewHolder (private val binding: ItemNotesBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(notes: Notes){
                binding.apply {
                    dataNotes = notes

                    btnRvEdit.setOnClickListener {
                        onClick.editNote(notes)
                    }

                    btnRvDelete.setOnClickListener {
                        onClick.deleteNote(notes)
                    }
                }
            }
    }

    interface NotesInterface {
        fun editNote(notes: Notes)
        fun deleteNote(notes: Notes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    fun setData(data : List<Notes>){
        differ.submitList(data)
    }
}