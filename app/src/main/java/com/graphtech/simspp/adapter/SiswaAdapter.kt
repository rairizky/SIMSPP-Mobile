package com.graphtech.simspp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.graphtech.simspp.R
import com.graphtech.simspp.model.SiswaItem
import kotlinx.android.synthetic.main.item_siswa.view.*
import java.text.DecimalFormat

class SiswaAdapter (private val context: Context, private val siswa: List<SiswaItem>)
    : RecyclerView.Adapter<SiswaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SiswaViewHolder(LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false))


    override fun getItemCount(): Int = siswa.size

    override fun onBindViewHolder(holder: SiswaViewHolder, position: Int) {
        holder.bindItem(siswa[position])
    }

}

class SiswaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val kelasSiswa = view.tvClass
    private val nisSiswa = view.tvNis
    private val namaSiswa = view.tvNama
    private val tagihanSiswa = view.tvTagihan
    private val itemSiswa = view.itemSiswa

    fun String.capitalizeWord() : String = split(" ").map { it.capitalize() }.joinToString(" ")

    fun bindItem(siswa: SiswaItem) {
        kelasSiswa.text = siswa.kelas!!.toUpperCase()
        nisSiswa.text = siswa.nis.toString()
        namaSiswa.text = siswa.nama!!.capitalizeWord()
        if (siswa.tagihan == 0) {
            tagihanSiswa.text = "Tidak ada tagihan."
        } else {
            tagihanSiswa.text = "Rp."+siswa.tagihan.toString()
        }
        itemSiswa.setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.setType("text/plain")
            whatsappIntent.setPackage("com.whatsapp")
            if (siswa.tagihan == 0) {
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Siswa/i atas nama ${siswa.nama!!.capitalizeWord()} tidak memiliki tunggakan SPP.")
            } else {
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Siswa/i atas nama ${siswa.nama!!.capitalizeWord()} memiliki tunggakan SPP sebesar RP. ${siswa.tagihan}.")
            }
            itemView.context.startActivity(whatsappIntent)
        }
    }
}