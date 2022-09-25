package binar.academy.challenge_chapter4.ui.fragment.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import binar.academy.challenge_chapter4.R
import binar.academy.challenge_chapter4.data.model.Notes
import binar.academy.challenge_chapter4.databinding.FragmentEditNotesBinding
import binar.academy.challenge_chapter4.ui.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditNotesFragment : BottomSheetDialogFragment() {
    private var _binding : FragmentEditNotesBinding? = null
    private val binding get() = _binding!!

    private val args : EditNotesFragmentArgs by navArgs()
    private val notesViewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_notes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getData = args.dataNotes

        binding.apply {
            dataEditNotes = getData
            btnEditNotes.setOnClickListener {
                val title = etEditJudul.text.toString()
                val content = etEditContent.text.toString()

                if (etEditJudul.text.isEmpty() || etEditContent.text.isEmpty()){
                    Toast.makeText(context, "Judul/Content belum diisi", Toast.LENGTH_SHORT).show()
                }else{
                    notesViewModel.editNotes(Notes(args.dataNotes.id_notes, title, content))
                    Toast.makeText(context, "Berhasil edit note", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}