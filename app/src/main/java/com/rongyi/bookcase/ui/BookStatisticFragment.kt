package com.rongyi.bookcase.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rongyi.bookcase.R
import com.rongyi.bookcase.dao.BookDaoBeanDao
import com.rongyi.bookcase.dao.DaoHelper
import com.rongyi.bookcase.ui.fragment.BaseFragment
import com.rongyi.bookcase.ui.view.PieChartBean
import kotlinx.android.synthetic.main.fragment_book_statistics.*
import java.util.*


class BookStatisticFragment : BaseFragment() {


    var isViewvisible = false
    var isVisibleTo = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_book_statistics, null)

        Log.d("fragment====", "two onCreateView")


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("fragment====", "two onViewCreated")


        isViewvisible = true

        lazyData()

        initListener()


    }

    private fun initListener() {
        bookBestLoveAuthor.setOnClickListener {

            pie_view.startAnimator()

            val list = DaoHelper.getInstance().dao().queryBuilder()
                    .orderAsc(BookDaoBeanDao.Properties.Id)
                    .build()
                    .list()

            val authorMap = HashMap<String, Int>()

            list.forEach {
                val bookAuthor = it.bookAuthor
                if (authorMap.containsKey(bookAuthor)) {
                    authorMap.put(bookAuthor, authorMap[bookAuthor]!! + 1)
                } else {
                    authorMap.put(bookAuthor, 1)
                }
            }

            val lists = ArrayList<PieChartBean>()

            for ((key, value) in authorMap) {
                list.clear()
                lists.add(PieChartBean(Color.parseColor(getRGB()), value.toFloat(), key))
            }

            pie_view.setData(lists)

        }

        bookBestLovePublisher.setOnClickListener {
            initChat()
        }


    }

    private fun lazyData() {

        if (isViewvisible && isVisibleTo) {
            initChat()
        }

    }


    override fun onPause() {
        super.onPause()

        Log.d("fragment====", "Two onResume")

        isViewvisible = false


    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)


        isVisibleTo = isVisibleToUser

        lazyData()

        Log.d("fragment====", "two setVisibleToUser: " + isVisibleToUser)

        Log.d("fragment====", "two setVisibleToUser1: " + userVisibleHint)

    }


    private fun initChat() {

        pie_view.startAnimator()

        val list = DaoHelper.getInstance().dao().queryBuilder()
                .orderAsc(BookDaoBeanDao.Properties.Id)
                .build()
                .list()

        if (list.size != 0) {
            val reduce = list.map { it.bookPrice }.reduce { acc, fl -> acc + fl }

            bookCountPrice.text = "¥${reduce}元"
        }


        val publisherMap = HashMap<String, Int>()


        list.forEach {
            val bookPublisher = it.bookPublisher
            if (publisherMap.containsKey(bookPublisher)) {
                publisherMap.put(bookPublisher, publisherMap[bookPublisher]!! + 1)
            } else {
                publisherMap.put(bookPublisher, 1)
            }

        }


        val lists = ArrayList<PieChartBean>()

        for ((key, value) in publisherMap) {
            Log.d("this====", "$key:$value")

            lists.add(PieChartBean(Color.parseColor(getRGB()), value.toFloat(), key))
        }

        pie_view.setData(lists)


    }


    fun getRGB(): String {

        var r: String
        var g: String
        var b: String

        val random = Random()
        r = Integer.toHexString(random.nextInt(256)).toUpperCase()
        g = Integer.toHexString(random.nextInt(256)).toUpperCase()
        b = Integer.toHexString(random.nextInt(256)).toUpperCase()

        r = if (r.length == 1) "0" + r else r
        g = if (g.length == 1) "0" + g else g
        b = if (b.length == 1) "0" + b else b


        return "#7F$r$g$b"

    }
}