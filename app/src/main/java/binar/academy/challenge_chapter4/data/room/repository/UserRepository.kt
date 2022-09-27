package binar.academy.challenge_chapter4.data.room.repository

import androidx.lifecycle.LiveData
import binar.academy.challenge_chapter4.data.model.User
import binar.academy.challenge_chapter4.data.room.dao.UserDao

class UserRepository(private val userDao: UserDao) {

    val getUser : LiveData<List<User>> = userDao.getAllDataUser()

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    fun getUser(username : String, password : String) = userDao.getUser(username, password)

}