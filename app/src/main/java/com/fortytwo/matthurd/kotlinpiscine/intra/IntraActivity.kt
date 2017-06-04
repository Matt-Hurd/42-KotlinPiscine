package com.fortytwo.matthurd.kotlinpiscine.intra

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import butterknife.OnClick
import com.fortytwo.matthurd.kotlinpiscine.R

class IntraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intra)
    }

    @OnClick(R.id.search_intra_login)
    fun searchForUser() {
    }
}
