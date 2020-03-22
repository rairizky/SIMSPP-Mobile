package com.graphtech.simspp.network

object ApiURL {
    private val BASE_API = "http://192.168.137.83:8000/api/"
    private val BASE_API_TEST = "http://simspp.herokuapp.com/api/"

    fun getLogin(email: String, password: String): String {
        return BASE_API_TEST+"signin?email=${email}&password=${password}"
    }

    fun getSiswa(): String {
        return BASE_API_TEST+"siswa/daftarsiswa"
    }
}