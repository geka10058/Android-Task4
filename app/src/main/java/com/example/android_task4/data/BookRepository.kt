package com.example.android_task4.data

import androidx.lifecycle.LiveData
import com.example.android_task4.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(
    private val booksDao: BookDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    val allBooks: LiveData<List<Book>> = booksDao.getAllBooks()
    val booksSortedByTitle: LiveData<List<Book>> = booksDao.sortedByTitle()
    val booksSortedByAuthor: LiveData<List<Book>> = booksDao.sortedByAuthor()
    val booksSortedByNumber: LiveData<List<Book>> = booksDao.sortedByPageNumber()

    suspend fun add(book: Book) = withContext(ioDispatcher) {
        booksDao.addBook(book)
    }

    suspend fun delete(book: Book) = withContext(ioDispatcher) {
        booksDao.deleteBook(book)
    }

    suspend fun upgrade(book: Book) = withContext(ioDispatcher) {
        booksDao.upgradeBook(book)
    }
}