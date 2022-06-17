package com.nextory.testapp.ui.bookdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextory.testapp.data.Book
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailedListViewModel @Inject constructor(
    val bookRepository: BookRepository
) : ViewModel() {

    fun updateBook(book: Book) {
        viewModelScope.launch {
            bookRepository.updateBook(book)
        }
    }
}