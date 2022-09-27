package binar.academy.challenge_chapter4.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import binar.academy.challenge_chapter4.data.model.User
import binar.academy.challenge_chapter4.data.room.NotesDatabase
import binar.academy.challenge_chapter4.data.room.repository.UserRepository
import kotlinx.coroutines.launch

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {

    var getAllDataUser : LiveData<List<User>>
    private var userRepository : UserRepository

    init {
        val userDao = NotesDatabase.getInstance(application)?.userDao()
        userRepository = UserRepository(userDao!!)
        getAllDataUser = userRepository.getUser
    }

    fun registerUser(user: User){
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    fun verifyUser(username : String, password : String) : LiveData<User> =
        userRepository.getUser(username, password)
}