package com.graphtech.simspp.model


import com.google.gson.annotations.SerializedName

data class SiswaItem(

	@field:SerializedName("tagihan")
	val tagihan: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("kelas")
	val kelas: String? = null,

	@field:SerializedName("nis")
	val nis: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("rayon")
	val rayon: String? = null
)