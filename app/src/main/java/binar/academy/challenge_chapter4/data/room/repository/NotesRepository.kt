package binar.academy.challenge_chapter4.data.room.repository

import androidx.lifecycle.LiveData
import binar.academy.challenge_chapter4.data.room.dao.NoteDao
import binar.academy.challenge_chapter4.data.model.Notes

class NotesRepository(private val noteDao: NoteDao) {

    val getAllDataNotes : LiveData<List<Notes>> = noteDao.getAllDataNotes()

    suspend fun addNote(notes: Notes) = noteDao.insertNote(notes)

    suspend fun editNote(notes: Notes) = noteDao.editNote(notes)

    suspend fun deleteNote(notes: Notes) = noteDao.deleteNote(notes)

}