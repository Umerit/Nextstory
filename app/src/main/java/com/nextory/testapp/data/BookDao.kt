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

    @Query("select * from book where book.author = :searchKey")
    fun searchBooks(searchKey: String): PagingSource<Int, Book>

}