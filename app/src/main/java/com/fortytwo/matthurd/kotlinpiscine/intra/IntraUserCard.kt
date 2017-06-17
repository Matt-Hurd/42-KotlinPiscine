package com.fortytwo.matthurd.kotlinpiscine.intra

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.fortytwo.matthurd.kotlinpiscine.R
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraProjectUser
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraUser
import com.squareup.picasso.Picasso
import java.util.*

class IntraUserCard : RelativeLayout {

    @BindView(R.id.user_image) lateinit var userImage: ImageView
    @BindView(R.id.text_intra_name) lateinit var userName: TextView
    @BindView(R.id.text_wallet) lateinit var userWallet: TextView
    @BindView(R.id.text_correction_points) lateinit var userCorrection: TextView
    @BindView(R.id.text_level) lateinit var userLevel: TextView
    @BindView(R.id.level_progress) lateinit var userProgress: ProgressBar
    @BindView(R.id.projects_container) lateinit var projectUserView: RecyclerView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    var projectUsers: MutableList<IntraProjectUser> = mutableListOf()

    override fun onFinishInflate() {
        super.onFinishInflate()
        ButterKnife.bind(this)
        projectUserView.adapter = IntraProjectUserRecyclerViewAdapter(projectUsers)
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        projectUserView.layoutManager = llm
    }

    fun setUserData(user: IntraUser) {
        this.visibility = View.VISIBLE
        userName.text = user.displayname
        userWallet.text = resources.getString(R.string.wallet_points, user.wallet)
        userCorrection.text = resources.getString(R.string.correction_points, user.correctionPoint)
        Picasso.
                with(context).
                load(user.imageUrl)
                .placeholder(R.drawable.abc_btn_check_material)
                .into(userImage)
        val cursus = user.cursusUsers?.filter {
            intraCursusUser ->
            intraCursusUser?.cursusId == 1.toLong()
        }?.first()
        userLevel.text = resources.getString(R.string.level, (java.lang.String.format(Locale.ROOT, "%.2f", cursus?.level ?: 0.0)))
        userProgress.progress = cursus?.level?.times(100)?.rem(100)?.toInt() ?: 0
//        updateProjects(user.projectsUsers?.toList()?.filterNotNull() ?: listOf())
        projectUserView.adapter = IntraProjectUserRecyclerViewAdapter(user.projectsUsers?.toList()?.filterNotNull() ?: listOf())
    }

    fun updateProjects(newProjects: List<IntraProjectUser>) {
        projectUsers.forEach { item -> projectUsers.remove(item) }
        newProjects.forEach { item -> projectUsers.add(item) }
    }

    fun handleInvalidUser() {
    }
}