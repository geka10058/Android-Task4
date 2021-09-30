package com.example.android_task4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {
    var allBooks: LiveData<List<Book>>
    var booksSortedByTitle: LiveData<List<Book>>
    var booksSortedByAuthor: LiveData<List<Book>>
    var booksSortedByYear: LiveData<List<Book>>
    var repository: BookRepository

    init {
        val dao = BookDatabase.getDatabase(application).getBooksDao()
        repository = BookRepository(dao)
        allBooks = repository.allBooks
        booksSortedByTitle = repository.booksSortedByTitle
        booksSortedByAuthor = repository.booksSortedByAuthor
        booksSortedByYear = repository.booksSortedByYear
    }

    fun addBook(book: Book) = viewModelScope.launch(Dispatchers.IO){
        repository.add(book)
    }

    fun upgradeBook(book: Book) = viewModelScope.launch (Dispatchers.IO){
        repository.upgrade(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch ( Dispatchers.IO){
        repository.delete(book)
    }
}