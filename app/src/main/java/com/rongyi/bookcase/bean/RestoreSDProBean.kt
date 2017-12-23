package com.rongyi.bookcase.bean

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/23
 */


data class RestoreSDProBean(
        val collected: Boolean, //true
        val id: Int, //4098318
        val image: String, //https://img1.doubanio.com/mpic/s27905679.jpg
        val isReaded: Boolean, //false
        val isbn10: String, //7508386981
        val isbn13: String, //9787508386980
        val pages: String, //469
        val price: String, //58.00元
        val pubdate: String, //2009-9-1
        val publisher: String, //中国电力出版社
        val saveAuthor: String, //布里泰恩(Jason Brittain),达尔文(Ian F.Darwin)
        val saveTag: String, //tomcat
        val saveTranslator: String, //吴豪,刘运成,杨前凤,王洁
        val summary: String,
        val title: String //Tomcat权威指南
)