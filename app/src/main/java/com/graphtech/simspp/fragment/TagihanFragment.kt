package com.graphtech.simspp.fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.graphtech.simspp.R
import com.graphtech.simspp.adapter.SiswaAdapter
import com.graphtech.simspp.model.SiswaItem
import com.graphtech.simspp.network.SharedPrefManager
import com.graphtech.simspp.presenter.TagihanPresenter
import com.graphtech.simspp.view.TagihanView
import kotlinx.android.synthetic.main.fragment_tagihan.*

/**
 * A simple [Fragment] subclass.
 */
class TagihanFragment : Fragment(), TagihanView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tagihan, container, false)
    }

    private var siswa: MutableList<SiswaItem> = mutableListOf()
    private lateinit var presenter: TagihanPresenter
    private lateinit var adapter: SiswaAdapter

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showSiswa(data: List<SiswaItem>) {
        siswa.clear()
        val user = SharedPrefManager.getInstance(this.requireContext()).user
        data.forEach {
            if (it.rayon.equals(user.rayon)) {
                siswa.add(it)
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set text user welcome
        val user = SharedPrefManager.getInstance(this.requireContext()).user
        nameUser.text = user.username
        txtRayon.text = user.rayon?.toUpperCase()

        rvSiswa.layoutManager = GridLayoutManager(context, 2)
        adapter = SiswaAdapter(this.requireContext(), siswa)
        rvSiswa.adapter = adapter
        presenter = TagihanPresenter(this)
        presenter.getSiswa()
    }


    companion object {
        fun newInstance(): TagihanFragment {
            val fragment = TagihanFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
