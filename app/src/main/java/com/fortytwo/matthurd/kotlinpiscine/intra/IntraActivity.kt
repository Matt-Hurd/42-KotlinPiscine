package com.fortytwo.matthurd.kotlinpiscine.intra

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.fortytwo.matthurd.kotlinpiscine.R

class IntraActivity : AppCompatActivity() {


    @BindView(R.id.search_intra_login) lateinit var nameField: EditText
    @BindView(R.id.search_button) lateinit var searchButton: Button
    @BindView(R.id.user_image) lateinit var userImage: ImageView
    @BindView(R.id.text_intra_name) lateinit var userName: TextView
    @BindView(R.id.text_wallet) lateinit var userWallet: TextView
    @BindView(R.id.text_correction_points) lateinit var userCorrection: TextView
    @BindView(R.id.text_level) lateinit var userLevel: TextView
    @BindView(R.id.level_progress) lateinit var userProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intra)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.search_intra_login)
    fun searchForUser() {
    }
}
