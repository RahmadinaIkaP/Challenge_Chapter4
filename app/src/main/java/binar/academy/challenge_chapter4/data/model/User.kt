package binar.academy.challenge_chapter4.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class User(
    val username : String,
    val email : String,
    val password : String
)
