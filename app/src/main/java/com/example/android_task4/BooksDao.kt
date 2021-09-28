package com.example.android_task4

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BooksDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book)

    @Update
    suspend fun upgradeBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("Select * from booksTable order by id ASC")
    suspend fun sorte():LiveData<List<Book>>

    @Query("Select * from booksTable order by title ASC")
    suspend fun sortedByTitle():LiveData<List<Book>>

    @Query("Select * from booksTable order by author ASC")
    suspend fun sortedByAuthor():LiveData<List<Book>>

    @Query("Select * from booksTable order by releaseYear ASC")
    suspend fun sortedByReleaseYear():LiveData<List<Book>>

}