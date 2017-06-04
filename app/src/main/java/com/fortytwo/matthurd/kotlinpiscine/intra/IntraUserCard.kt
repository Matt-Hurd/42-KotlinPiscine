package com.fortytwo.matthurd.kotlinpiscine.intra

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.fortytwo.matthurd.kotlinpiscine.R
import com.fortytwo.matthurd.kotlinpiscine.intra.api.IntraUser
import com.squareup.picasso.Picasso

class IntraUserCard : RelativeLayout {

    @BindView(R.id.user_image) lateinit var userImage: ImageView
    @BindView(R.id.text_intra_name) lateinit var userName: TextView
    @BindView(R.id.text_wallet) lateinit var userWallet: TextView
    @BindView(R.id.text_correction_points) lateinit var userCorrection: TextView
    @BindView(R.id.text_level) lateinit var userLevel: TextView
    @BindView(R.id.level_progress) lateinit var userProgress: ProgressBar

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onFinishInflate() {
        super.onFinishInflate()
        ButterKnife.bind(this)
    }

    fun setUserData(user: IntraUser) {
        this.visibility = View.VISIBLE
        userName.text = user.displayname
        Picasso.
                with(context).
                load(user.imageUrl).
                into(userImage)
    }

    fun handleInvalidUser() {

    }
}