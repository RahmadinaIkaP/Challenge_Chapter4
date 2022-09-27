package binar.academy.challenge_chapter4.ui.fragment.notes

import android.content.Context
import android.content.SharedPreferences
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
import binar.academy.challenge_chapter4.data.model.User
import binar.academy.challenge_chapter4.ui.Constant
import binar.academy.challenge_chapter4.ui.Constant.Companion.NAMA
import binar.academy.challenge_chapter4.ui.Constant.Companion.USER
import binar.academy.challenge_chapter4.ui.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNotesFragment : BottomSheetDialogFragment() {
    private var _binding : FragmentAddNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences : SharedPreferences
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

        sharedPreferences = requireActivity().getSharedPreferences(USER, Context.MODE_PRIVATE)

        binding.btnSaveNotes.setOnClickListener {
            saveDataNote()
        }
    }

    private fun saveDataNote() {
        binding.apply {
            val title = etAddJudul.text.toString()
            val content = etAddContent.text.toString()
            val editor = sharedPreferences.getString(Constant.NAMA, "")

            if (etAddJudul.text.isEmpty() || etAddContent.text.isEmpty()){
                Toast.makeText(context, "Judul/Content kosong, mohon diisi", Toast.LENGTH_SHORT).show()
            }else{
                notesViewModel.addNote(Notes(0, title, content, editor.toString()))
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