package com.nextory.testapp.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.nextory.testapp.data.Book
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    val bookRepository: BookRepository
) : ViewModel() {
    val pagedBooks = bookRepository.observePagedBooks(PAGING_CONFIG)

    private fun search(search: String): LiveData<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false,
                maxSize = 200
            )
        ) {
            searchWithPaging(search)
        }.liveData.cachedIn(viewModelScope)
    }

    private fun searchWithPaging(search: String): PagingSource<Int, Book> {
        return bookRepository.searchWithPaging(search)
    }

    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 12,
            enablePlaceholders = false
        )
    }
}