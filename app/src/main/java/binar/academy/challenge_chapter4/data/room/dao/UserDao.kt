package binar.academy.challenge_chapter4.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import binar.academy.challenge_chapter4.data.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllDataUser() : LiveData<List<User>>

    @Query("SELECT * FROM user WHERE email=:username AND password=:password")
    fun getUser(username : String, password : String) : LiveData<User>

}