package com.example.android_task4

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book)

    @Update
    suspend fun upgradeBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("Select * from booksTable order by id ASC")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("Select * from booksTable order by title ASC")
    fun sortedByTitle(): LiveData<List<Book>>

    @Query("Select * from booksTable order by author ASC")
    fun sortedByAuthor(): LiveData<List<Book>>

    @Query("Select * from booksTable order by releaseYear ASC")
    fun sortedByReleaseYear(): LiveData<List<Book>>

}