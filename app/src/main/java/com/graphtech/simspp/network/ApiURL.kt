package com.graphtech.simspp.network

object ApiURL {
    private val BASE_API = "http://192.168.137.98:8000/api/"

    fun getLogin(email: String, password: String): String {
        return BASE_API+"signin?email=${email}&password=${password}"
    }

    fun getSiswa(): String {
        return BASE_API+"siswa/daftarsiswa"
    }
}