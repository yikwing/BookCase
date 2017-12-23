package com.rongyi.bookcase.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rongyi.bookcase.R
import com.rongyi.bookcase.bean.BookBean
import com.rongyi.bookcase.dao.BookDaoBean
import com.rongyi.bookcase.dao.DaoHelper
import com.rongyi.bookcase.http.HttpManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_restore.*

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/23
 */
class RestoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore)


        btn_restore.setOnClickListener {
            val trim = et_restore.text.toString().trim()
            val split = trim.split(",")

            split.forEach {
                initGetBook(it)
            }
        }
    }


    private fun initGetBook(qrcode: String) {
        val bookOb = object : Observer<BookBean> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: BookBean) {
                val bookDaoBean = BookDaoBean()
                bookDaoBean.bookName = t.title
                bookDaoBean.bookAuthor = t.author[0]
                bookDaoBean.bookImg = t.images.medium
                val numPattern = "\\d+(\\.\\d{2})?".toRegex()
                for (matchResult in numPattern.findAll(t.price)) {
                    bookDaoBean.bookPrice = matchResult.value.toFloat()
                }
                bookDaoBean.bookPubdate = t.pubdate
                bookDaoBean.isbn13 = t.isbn13
                bookDaoBean.bookPublisher = t.publisher
                DaoHelper.getInstance().dao().insert(bookDaoBean)
            }

            override fun onError(e: Throwable) {

            }
        }

        HttpManager.getInstance().getBookInfo(bookOb, qrcode)
    }
}