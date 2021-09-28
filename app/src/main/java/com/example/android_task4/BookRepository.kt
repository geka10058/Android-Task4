package com.example.android_task4

import androidx.lifecycle.LiveData

class BookRepository(private val booksDao: BooksDao) {
    val allBooks: LiveData<List<Book>> = booksDao.getAllData()

    suspend fun add(book: Book){
        booksDao.addBook(book)
    }

    suspend fun delete(book: Book){
        booksDao.deleteBook()
    }

    suspend fun upgade(book: Book){
        booksDao.upgradeBook()
    }
}