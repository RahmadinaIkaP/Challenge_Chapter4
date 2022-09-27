package binar.academy.challenge_chapter4.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id_user : Int,
    val username : String,
    val email : String,
    val password : String
) : Parcelable
