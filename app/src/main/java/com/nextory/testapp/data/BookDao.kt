package com.nextory.testapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun observePagedBooks(): PagingSource<Int, Book>

    @Update
    suspend fun updateBook(book: Book)

    @Query("SELECT * FROM book WHERE title LIKE '%' || :queryString || '%' OR author LIKE '%' || :queryString || '%'")
    fun searchWithTitleAndAuthor(queryString: String): PagingSource<Int, Book>
}