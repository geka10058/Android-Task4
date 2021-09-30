package com.example.android_task4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Book::class), version = 1, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {
    abstract fun getBooksDao(): BooksDao

    companion object{

        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context):BookDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "book_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }


    }
}