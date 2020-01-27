package com.graphtech.simspp.view

import com.graphtech.simspp.model.SiswaItem

interface TagihanView {
    fun showLoading()
    fun hideLoading()
    fun showSiswa(data: List<SiswaItem>)
}