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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import binar.academy.challenge_chapter4.R
import binar.academy.challenge_chapter4.data.model.User
import binar.academy.challenge_chapter4.databinding.FragmentLoginBinding
import binar.academy.challenge_chapter4.ui.Constant.Companion.EMAIL
import binar.academy.challenge_chapter4.ui.Constant.Companion.PASSWORD
import binar.academy.challenge_chapter4.ui.Constant.Companion.USER
import binar.academy.challenge_chapter4.ui.Constant.Companion.USER_SEDANG_LOGIN

class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences(USER, Context.MODE_PRIVATE)

        binding.btnToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            login()
        }

    }

    private fun login() {
        binding.apply {
            val email = etEmailLogin.text.toString().lowercase()
            val password = etPassLogin.text.toString()

            if (etEmailLogin.text!!.isEmpty() || etPassLogin.text!!.isEmpty()){
                Toast.makeText(context, "Tidak boleh kosong!!", Toast.LENGTH_SHORT).show()
            }else{
                if (sharedPreferences.getString(EMAIL, "") == email &&
                    sharedPreferences.getString(PASSWORD, "") == password) {

                    getSharedPreferences()
                    Toast.makeText(context, "Login berhasil!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                } else{
                    Toast.makeText(context, "Email/Password salah!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getSharedPreferences(){
        val editor = sharedPreferences.edit()
        editor.putBoolean(USER_SEDANG_LOGIN, true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
