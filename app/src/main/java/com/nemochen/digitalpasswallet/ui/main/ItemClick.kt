package com.nemochen.digitalpasswallet.ui.main

import android.view.View

interface ItemClick<T> {
    fun onItemClicked(view: View, item: T)
}