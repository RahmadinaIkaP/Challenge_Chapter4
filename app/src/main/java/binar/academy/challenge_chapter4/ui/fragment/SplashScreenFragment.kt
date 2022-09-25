package binar.academy.challenge_chapter4.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import binar.academy.challenge_chapter4.R
import binar.academy.challenge_chapter4.databinding.FragmentSplashScreenBinding
import binar.academy.challenge_chapter4.ui.Constant.Companion.USER
import binar.academy.challenge_chapter4.ui.Constant.Companion.USER_SEDANG_LOGIN

class SplashScreenFragment : Fragment() {
    private var _binding : FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen ,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences(USER, Context.MODE_PRIVATE)
        splashScreen()
    }

    private fun splashScreen() {
        val time : Long = 3000
        val status = sharedPreferences.getBoolean(USER_SEDANG_LOGIN, false)

        Handler(Looper.getMainLooper()).postDelayed({
            if (!status){
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }
            else
                findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
        }, time)
    }

}