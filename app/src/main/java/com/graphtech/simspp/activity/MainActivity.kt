package com.graphtech.simspp.activity

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.graphtech.simspp.R
import com.graphtech.simspp.adapter.SiswaAdapter
import com.graphtech.simspp.fragment.ProfileFragment
import com.graphtech.simspp.fragment.TagihanFragment
import com.graphtech.simspp.model.SiswaItem
import com.graphtech.simspp.network.SharedPrefManager
import com.graphtech.simspp.presenter.TagihanPresenter
import com.graphtech.simspp.view.TagihanView
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import com.yarolegovich.slidingrootnav.SlidingRootNavLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_side_nav.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //check current user if true
        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            val user = SharedPrefManager.getInstance(this).user

            if (user.role != "rayon") {
                SharedPrefManager.getInstance(this).logout()
            }

        } else {
            startActivity<LoginActivity>()
            finish()
        }

        //set item first
        val fragment = TagihanFragment.newInstance()
        addFragment(fragment)

        val menuDrawer = SlidingRootNavBuilder(this)
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(false)
            .withSavedState(savedInstanceState)
            .withMenuLayout(R.layout.menu_side_nav)
            .inject()

        //menu click
        btnMenu.setOnClickListener {
            menuDrawer.openMenu()
        }

        intentTagihan.setOnClickListener {
            val fragment = TagihanFragment.newInstance()
            addFragment(fragment)
            menuDrawer.closeMenu()
            intentTagihan.isClickable = false
            intentProfile.isClickable = true
            txtMenuTagihan.setTypeface(null, Typeface.BOLD)
            txtMenuProfile.setTypeface(null, Typeface.NORMAL)
        }

        intentProfile.setOnClickListener {
            val fragment = ProfileFragment.newInstance()
            addFragment(fragment)
            menuDrawer.closeMenu()
            intentTagihan.isClickable = true
            intentProfile.isClickable = false
            txtMenuProfile.setTypeface(null, Typeface.BOLD)
            txtMenuTagihan.setTypeface(null, Typeface.NORMAL)
        }
    }
    
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containFragment, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}
