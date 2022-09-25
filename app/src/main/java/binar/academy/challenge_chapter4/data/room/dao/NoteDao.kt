package binar.academy.challenge_chapter4.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import binar.academy.challenge_chapter4.data.model.Notes

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY id_notes DESC")
    fun getAllDataNotes() : LiveData<List<Notes>>

    @Update
    suspend fun editNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)
}