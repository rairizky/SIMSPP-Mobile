package com.graphtech.simspp.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.graphtech.simspp.R
import com.graphtech.simspp.network.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        fun newInstance(): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = SharedPrefManager.getInstance(this.requireContext()).user
        profileName.text = user.username
        profileDataName.text = user.username
        profileDataRayon.text = user.rayon?.toUpperCase()
        profileDataEmail.text = user.email

        btnLogout.setOnClickListener {
            SharedPrefManager.getInstance(this.requireContext()).logout()
        }
    }
}
