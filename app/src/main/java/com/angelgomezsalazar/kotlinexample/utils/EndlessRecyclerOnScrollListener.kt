package com.angelgomezsalazar.kotlinexample.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by angelgomez on 8/18/16.
 */
abstract class EndlessRecyclerOnScrollListener(private val linearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    var previousTotal = 0
    var loading = true
    var firstVisibleItem: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0

    var currentPage: Int = 1

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView!!.childCount
        totalItemCount = linearLayoutManager.itemCount
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        val visibleThreshold = 5
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {

            currentPage++

            onLoadMore(currentPage)

            loading = true
        }
    }

    abstract fun onLoadMore(current_page: Int)
}
