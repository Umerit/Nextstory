package com.nextory.testapp.ui.bookdetails

import androidx.lifecycle.ViewModel
import androidx.paging.PagingConfig
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailedListViewModel @Inject constructor(
    bookRepository: BookRepository
) : ViewModel() {

}