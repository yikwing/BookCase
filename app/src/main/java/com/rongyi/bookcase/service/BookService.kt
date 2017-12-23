package com.rongyi.bookcase.service

import com.rongyi.bookcase.bean.BookBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/20
 */
interface BookService {
    @GET("book/isbn/{isbnId}")
    fun getBookInfo(@Path("isbnId") id: String): Observable<BookBean>
}