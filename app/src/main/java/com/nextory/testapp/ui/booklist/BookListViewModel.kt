package com.nextory.testapp.ui.booklist

import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.nextory.testapp.data.Book
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    val bookRepository: BookRepository
) : ViewModel() {

    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 12,
            enablePlaceholders = false
        )
    }

    fun searchBook(queryString: String): Flow<PagingData<Book>> {
        return bookRepository.searchBooksByAuthorAndTitle(queryString)
    }

    fun booksLists(): Flow<PagingData<Book>> {
        return bookRepository.observePagedBooks(PAGING_CONFIG)
    }
}