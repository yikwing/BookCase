package com.rongyi.bookcase.cache

import com.rongyi.bookcase.bean.BookBean
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider
import io.rx_cache2.LifeCache
import java.util.concurrent.TimeUnit

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/20
 */
interface CacheProvider {
    @LifeCache(duration = 30, timeUnit = TimeUnit.DAYS)
    fun getDatas(oRepos: Observable<BookBean>,
                 key: DynamicKey,
                 evictDynamicKey: EvictProvider): Observable<BookBean>
}