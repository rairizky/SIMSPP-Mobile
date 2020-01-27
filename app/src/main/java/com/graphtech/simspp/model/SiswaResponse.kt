package com.graphtech.simspp.model


import com.google.gson.annotations.SerializedName


data class SiswaResponse(

	@field:SerializedName("siswa")
	val siswa: List<SiswaItem>
)