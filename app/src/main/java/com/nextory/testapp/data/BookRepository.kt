package com.nextory.testapp.data

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao
) {
    fun observePagedBooks(pagingConfig: PagingConfig): Flow<PagingData<Book>> {
        return Pager(config = pagingConfig) {
            bookDao.observePagedBooks()
        }.flow
    }

    fun searchWithPaging(searchKey: String) : PagingSource<Int, Book>{
        return bookDao.searchBooks(searchKey)
    }

    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }
}