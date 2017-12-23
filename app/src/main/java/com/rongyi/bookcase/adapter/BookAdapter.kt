package com.rongyi.bookcase.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rongyi.bookcase.R
import com.rongyi.bookcase.dao.BookDaoBean
import kotlinx.android.synthetic.main.item_dao_book.view.*

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/20
 */
class BookAdapter(private val mData: List<BookDaoBean>, val itemClickListener: (String) -> Unit) :
        RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: BookViewHolder?, position: Int) {
        (holder as BookViewHolder).onbind(mData[position], mData[position].isbn13)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookViewHolder {
        val inflate = View.inflate(parent!!.context, R.layout.item_dao_book, null)
        return BookViewHolder(inflate, itemClickListener)
    }


    class BookViewHolder(itemView: View, private val itemClickListener: (String) -> Unit) :
            RecyclerView.ViewHolder(itemView) {
        fun onbind(bookDaoBean: BookDaoBean, isbn13: String) {
            itemView.itemTitle.text = bookDaoBean.bookName
            itemView.itemAuthor.text = bookDaoBean.bookAuthor
            itemView.itemPubdate.text = bookDaoBean.bookPubdate
            itemView.itemPrice.text = "${bookDaoBean.bookPrice}"

            Glide.with(itemView).load(bookDaoBean.bookImg).into(itemView.itemImg)

            itemView.setOnClickListener {
                itemClickListener(isbn13)
            }
        }

    }
}