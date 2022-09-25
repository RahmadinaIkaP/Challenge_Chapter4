package binar.academy.challenge_chapter4.ui.fragment.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import binar.academy.challenge_chapter4.R
import binar.academy.challenge_chapter4.databinding.FragmentAddNotesBinding
import binar.academy.challenge_chapter4.data.model.Notes
import binar.academy.challenge_chapter4.ui.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNotesFragment : BottomSheetDialogFragment() {
    private var _binding : FragmentAddNotesBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_notes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveNotes.setOnClickListener {
            saveDataNote()
        }
    }

    private fun saveDataNote() {
        binding.apply {
            val title = etAddJudul.text.toString()
            val content = etAddContent.text.toString()

            if (etAddJudul.text.isEmpty() || etAddContent.text.isEmpty()){
                Toast.makeText(context, "Judul/Content kosong, mohon diisi", Toast.LENGTH_SHORT).show()
            }else{
                notesViewModel.addNote(Notes(0, title, content))
                Toast.makeText(context, "Berhasil menyimpan note", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}