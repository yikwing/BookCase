package com.rongyi.bookcase.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rongyi.bookcase.R
import com.rongyi.bookcase.adapter.BookAdapter
import com.rongyi.bookcase.dao.BookDaoBeanDao
import com.rongyi.bookcase.dao.DaoHelper
import com.rongyi.bookcase.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_viewpager.*


class BookMainFragment : BaseFragment() {


    var isViewvisible = false
    var isVisibleTo = false


    lateinit var mAdapter: BookAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_viewpager, null)

        Log.d("fragment====", "One onCreateView")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("fragment====", "One onViewCreated")


        isViewvisible = true

        lazyData()

    }

    private fun lazyData() {
        if (isViewvisible && isVisibleTo) {
            initData()
        }
    }


    override fun onPause() {
        super.onPause()

        Log.d("fragment====", "One onPause")


        isViewvisible = false

    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        isVisibleTo = isVisibleToUser

        lazyData()

        Log.d("fragment====", "One setVisibleToUser: " + isVisibleToUser)

        Log.d("fragment====", "One setVisibleToUser1: " + userVisibleHint)

    }


    fun initData() {

        val list = DaoHelper.getInstance().dao().queryBuilder()
                .orderAsc(BookDaoBeanDao.Properties.Id)
                .build()
                .list()

        if (list.size == 0) {
            return
        }

        recycler.layoutManager = LinearLayoutManager(activity)

        mAdapter = BookAdapter(list, itemClickListener = {

            val intent = Intent(activity, BookInfoActivity::class.java)
            intent.putExtra("isbn", it)
            activity!!.startActivity(intent)
        })

        recycler.adapter = mAdapter

        /*val mCallback = object :
                ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or
                        ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition = viewHolder.adapterPosition//得到拖动ViewHolder的position
                val toPosition = target.adapterPosition//得到目标ViewHolder的position
                if (fromPosition < toPosition) {
                    //分别把中间所有的item的位置重新交换
                    for (i in fromPosition until toPosition) {
                        Collections.swap(list, i, i + 1)
                    }
                } else {
                    for (i in fromPosition downTo toPosition + 1) {
                        Collections.swap(list, i, i - 1)
                    }
                }
                mAdapter.notifyItemMoved(fromPosition, toPosition)
                //返回true表示执行拖动
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val id = list[position].id
                DaoHelper.getInstance().dao().deleteByKey(id)

                list.removeAt(position)
                mAdapter.notifyItemRemoved(position)
            }

            override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    //滑动时改变Item的透明度
                    val alpha = 1 - Math.abs(dX) / viewHolder.itemView.width
                    viewHolder.itemView.alpha = alpha

                    //删除数据

                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(mCallback)
        itemTouchHelper.attachToRecyclerView(recycler)*/

    }


}
