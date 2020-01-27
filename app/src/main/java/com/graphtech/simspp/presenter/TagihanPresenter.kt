package com.graphtech.simspp.presenter

import com.google.gson.Gson
import com.graphtech.simspp.model.SiswaResponse
import com.graphtech.simspp.network.ApiRepository
import com.graphtech.simspp.network.ApiURL
import com.graphtech.simspp.view.TagihanView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TagihanPresenter (val view: TagihanView) {
    val apiRepo = ApiRepository()
    val gson = Gson()

    fun getSiswa() {
        view.showLoading()

        doAsync {
            val siswa = gson.fromJson(apiRepo.doRequest(ApiURL.getSiswa()),
                SiswaResponse::class.java)

            uiThread {
                view.showSiswa(siswa.siswa)
                view.hideLoading()
            }
        }
    }
}