package binar.academy.challenge_chapter4.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import binar.academy.challenge_chapter4.data.room.dao.NoteDao
import binar.academy.challenge_chapter4.data.model.Notes
import binar.academy.challenge_chapter4.data.model.User
import binar.academy.challenge_chapter4.data.model.UserAndNotes
import binar.academy.challenge_chapter4.data.room.dao.UserDao

@Database( entities = [
    User::class,
    Notes::class],
    version = 1 )
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao
    abstract fun userDao() : UserDao

    companion object{

        @Volatile
        private var INSTANCE : NotesDatabase? = null

        fun getInstance(context : Context): NotesDatabase? {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "note_app.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}