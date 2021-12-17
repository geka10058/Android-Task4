package com.example.android_task4.presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.android_task4.data.Book
import com.example.android_task4.data.BookRepository
import com.example.android_task4.data.SettingsManager
import com.example.android_task4.data.SortMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    application: Application,
    private val repository: BookRepository,
    private val settingsManager: SettingsManager,
): AndroidViewModel(application) {

    @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
    val allBooks: LiveData<List<Book>> = Transformations.switchMap(
        settingsManager.sortModeFlow.asLiveData()
    ) { mode ->
        when (mode) {
            SortMode.SORT_BY_TITLE -> repository.booksSortedByTitle
            SortMode.SORT_BY_AUTHOR -> repository.booksSortedByAuthor
            SortMode.SORT_BY_PAGE_NUMBERS -> repository.booksSortedByNumber
        }
    }

    fun addBook(book: Book) = viewModelScope.launch {
        repository.add(book)
    }

    fun upgradeBook(book: Book) = viewModelScope.launch {
        repository.upgrade(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        repository.delete(book)
    }
}