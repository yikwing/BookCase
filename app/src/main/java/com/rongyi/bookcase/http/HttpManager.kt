package com.rongyi.bookcase.http

import android.util.Log
import com.rongyi.bookcase.MyApplication
import com.rongyi.bookcase.bean.BookBean
import com.rongyi.bookcase.service.BookService
import com.rongyi.bookcase.cache.CacheProvider
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider
import io.rx_cache2.internal.RxCache
import io.victoralbertos.jolyglot.GsonSpeaker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/9
 */
class HttpManager private constructor() {

    private val DEFAULT_TIMEOUT = 5L

    private val BASE_URL = "https://api.douban.com/v2/"

    private var bookService: BookService

    private var cacheProvider: CacheProvider

    private val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
        Log.d("RetrofitLog", "retrofitBack = " + message)
    })

    init {

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        //手动创建一个OkHttpClient并设置超时时间
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)


        val retrofit = Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        cacheProvider = RxCache.Builder()
                .persistence(MyApplication.instance!!.filesDir, GsonSpeaker())
                .using(CacheProvider::class.java)


        bookService = retrofit.create(BookService::class.java)

    }

    companion object {
        fun getInstance(): HttpManager {
            return SingletonHolder.INSTANCE
        }
    }

    private object SingletonHolder {
        val INSTANCE = HttpManager()
    }

    fun getBookInfo(observer: Observer<BookBean>, isbn: String) {
        cacheProvider.getDatas(bookService.getBookInfo(isbn),
                DynamicKey("y${isbn}"),
                EvictProvider(false))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}