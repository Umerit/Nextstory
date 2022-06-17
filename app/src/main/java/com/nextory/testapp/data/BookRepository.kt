package com.nextory.testapp.data

import android.util.Log
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao,

) {
    fun observePagedBooks(pagingConfig: PagingConfig): Flow<PagingData<Book>> {
        return Pager(config = pagingConfig) {
            bookDao.observePagedBooks()
        }.flow
    }

    fun searchBooksByAuthorAndTitle(query: String): Flow<PagingData<Book>> {
        val pagingSourceFactory = { bookDao.searchWithTitleAndAuthor(query) }
        @OptIn(ExperimentalPagingApi::class)
        return Pager(config = PagingConfig(pageSize = 200, enablePlaceholders = false),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }
}