package com.rongyi.bookcase.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/20
 */

@Entity
public class BookDaoBean {
    @Id
    private Long id;
    @Property
    private String bookImg;
    @Property
    private String bookName;
    @Property
    private String bookAuthor;
    @Property
    private String bookPubdate;
    @Property
    private float bookPrice;
    @Property
    private String isbn13;
    @Property
    private String bookPublisher;


    @Generated(hash = 1210692914)
    public BookDaoBean(Long id, String bookImg, String bookName, String bookAuthor,
            String bookPubdate, float bookPrice, String isbn13,
            String bookPublisher) {
        this.id = id;
        this.bookImg = bookImg;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPubdate = bookPubdate;
        this.bookPrice = bookPrice;
        this.isbn13 = isbn13;
        this.bookPublisher = bookPublisher;
    }

    @Generated(hash = 339726793)
    public BookDaoBean() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPubdate() {
        return bookPubdate;
    }

    public void setBookPubdate(String bookPubdate) {
        this.bookPubdate = bookPubdate;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getBookPublisher() {
        return this.bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
}
