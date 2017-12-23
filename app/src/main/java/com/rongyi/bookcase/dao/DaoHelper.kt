package com.rongyi.bookcase.dao

import com.rongyi.bookcase.MyApplication
import com.rongyi.bookcase.http.HttpManager

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/20
 */
class DaoHelper private constructor() {

    companion object {
        fun getInstance(): DaoHelper {
            return SingletonHolder.INSTANCE
        }
    }

    private object SingletonHolder {
        val INSTANCE = DaoHelper()
    }

    fun dao(): BookDaoBeanDao {
        val devOpenHelper = DaoMaster.DevOpenHelper(MyApplication.instance, "book.db", null)
        val daoMaster = DaoMaster(devOpenHelper.writableDb)
        val mDaoSession = daoMaster.newSession()
        return mDaoSession.bookDaoBeanDao
    }

}