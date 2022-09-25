package binar.academy.challenge_chapter4.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id_notes : Int,
    var title : String,
    var content : String
) : Parcelable
