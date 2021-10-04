package com.example.android_task4

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class BookRepository(private val booksDao: BookDao) {
    val getallBooks: LiveData<List<Book>> = booksDao.getAllBooks()
    val booksSortedByTitle: LiveData<List<Book>> = booksDao.sortedByTitle()
    val booksSortedByAuthor: LiveData<List<Book>> = booksDao.sortedByAuthor()
    val booksSortedByNumber: LiveData<List<Book>> = booksDao.sortedByPageNumber()

    @WorkerThread
    suspend fun add(book: Book){
        booksDao.addBook(book)
    }

    @WorkerThread
    suspend fun delete(book: Book){
        booksDao.deleteBook(book)
    }

    @WorkerThread
    suspend fun upgrade(book: Book){
        booksDao.upgradeBook(book)
    }

    @WorkerThread
    fun sortByTitle(): LiveData<List<Book>>{
        return booksDao.sortedByTitle()
    }

    @WorkerThread
    fun sortByAuthor(): LiveData<List<Book>>{
        return booksDao.sortedByAuthor()
    }

    @WorkerThread
    fun sortByNumber(): LiveData<List<Book>>{
        return booksDao.sortedByPageNumber()
    }
}