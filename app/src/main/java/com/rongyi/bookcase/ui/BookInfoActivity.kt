package com.rongyi.bookcase.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.rongyi.bookcase.R
import com.rongyi.bookcase.R.id.*
import com.rongyi.bookcase.bean.BookBean
import com.rongyi.bookcase.dao.BookDaoBean
import com.rongyi.bookcase.dao.BookDaoBeanDao
import com.rongyi.bookcase.dao.DaoHelper
import com.rongyi.bookcase.http.HttpManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_bookinfo.*


/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/20
 */
class BookInfoActivity : AppCompatActivity() {

    lateinit var title: String
    lateinit var author: String
    var price: Float = 0.0f
    lateinit var pubdate: String
    lateinit var pages: String
    lateinit var publisher: String
    lateinit var isbn13: String
    lateinit var images: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookinfo)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val isbn = intent.getStringExtra("isbn")
        initGetBook(isbn)

        val list = DaoHelper.getInstance().dao().queryBuilder()
                .orderAsc(BookDaoBeanDao.Properties.Id)
                .where(BookDaoBeanDao.Properties.Isbn13.eq(isbn))
                .build()
                .list()

        bookColler.isChecked = list.size != 0

        bookColler.setOnCheckStateChangeListener { _, checked ->
            if (checked) {

                val qList = DaoHelper.getInstance().dao().queryBuilder()
                        .orderAsc(BookDaoBeanDao.Properties.Id)
                        .where(BookDaoBeanDao.Properties.Isbn13.eq(isbn))
                        .build()
                        .list()

                if (qList.size == 0) {
                    val bookDaoBean = BookDaoBean()
                    bookDaoBean.bookName = title
                    bookDaoBean.bookAuthor = author
                    bookDaoBean.bookImg = images
                    bookDaoBean.bookPrice = price
                    bookDaoBean.bookPubdate = pubdate
                    bookDaoBean.isbn13 = isbn13
                    bookDaoBean.bookPublisher = publisher
                    DaoHelper.getInstance().dao().insert(bookDaoBean)

                    Toast.makeText(this@BookInfoActivity,
                            "收藏成功", Toast.LENGTH_SHORT).show()

                }

            } else {

                val qList = DaoHelper.getInstance().dao().queryBuilder()
                        .orderAsc(BookDaoBeanDao.Properties.Id)
                        .where(BookDaoBeanDao.Properties.Isbn13.eq(isbn))
                        .build()
                        .list()

                val id = qList[0].id
                DaoHelper.getInstance().dao().deleteByKey(id)

                Toast.makeText(this@BookInfoActivity,
                        "取消收藏", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun initGetBook(qrcode: String) {
        val bookOb = object : Observer<BookBean> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {

            }

            @SuppressLint("SetTextI18n")
            override fun onNext(t: BookBean) {
                title = t.title
                bookName.text = title
                author = t.author[0]
                bookAuthor.text = "作者: ${author}"
                val numPattern = "\\d+(\\.\\d{2})?".toRegex()
                for (matchResult in numPattern.findAll(t.price)) {
                    price = matchResult.value.toFloat()
                }
                bookPrice.text = "价格: ${price}"
                pubdate = t.pubdate
                bookPubdate.text = "出版日期: ${pubdate}"
                pages = t.pages
                bookPages.text = "页码: ${pages}"
                publisher = t.publisher
                bookPublisher.text = "出版商: ${publisher}"
                isbn13 = t.isbn13
                bookIsbn.text = "ISBN: ${isbn13}"
                images = t.images.medium
                Glide.with(this@BookInfoActivity).load(images).into(bookImg)
                bookSummary.text = t.summary
            }

            override fun onError(e: Throwable) {

            }
        }

        HttpManager.getInstance().getBookInfo(bookOb, qrcode)
    }
}