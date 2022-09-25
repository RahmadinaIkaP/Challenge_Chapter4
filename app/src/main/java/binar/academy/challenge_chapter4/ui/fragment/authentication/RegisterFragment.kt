package binar.academy.challenge_chapter4.ui.fragment.authentication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import binar.academy.challenge_chapter4.R
import binar.academy.challenge_chapter4.data.model.User
import binar.academy.challenge_chapter4.databinding.FragmentRegisterBinding
import binar.academy.challenge_chapter4.ui.Constant.Companion.EMAIL
import binar.academy.challenge_chapter4.ui.Constant.Companion.NAMA
import binar.academy.challenge_chapter4.ui.Constant.Companion.PASSWORD

class RegisterFragment : Fragment() {
    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        binding.apply {
            val username = etUsernameRegis.text.toString()
            val email = etEmailRegister.text.toString().lowercase()
            val password = etPassRegister.text.toString()
            val konfirmPass = etKonfirPass.text.toString()

            if (etUsernameRegis.text!!.isEmpty() || etEmailRegister.text!!.isEmpty() || etPassRegister.text!!.isEmpty() || etKonfirPass.text!!.isEmpty()){
                Toast.makeText(context, "Tidak boleh kosong!!", Toast.LENGTH_SHORT).show()
            }else{
                if (konfirmPass != password){
                    Toast.makeText(context, "Password tidak sama", Toast.LENGTH_SHORT).show()
                }else{
                    // masih pake shared preferences :(
                    insertToSharedPreferences(User(username, email, password))
                    Toast.makeText(context, "Register berhasil!", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun insertToSharedPreferences(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString(NAMA, user.username)
        editor.putString(EMAIL, user.email)
        editor.putString(PASSWORD, user.password)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}