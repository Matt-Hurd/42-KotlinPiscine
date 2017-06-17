package com.fortytwo.matthurd.kotlinpiscine.intra

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.fortytwo.matthurd.kotlinpiscine.R
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraProjectUser

class IntraProjectUserCard : RelativeLayout {

    @BindView(R.id.text_project_name) lateinit var projectName: TextView
    @BindView(R.id.text_project_mark) lateinit var projectMark: TextView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onFinishInflate() {
        super.onFinishInflate()
        ButterKnife.bind(this)
    }

    fun setProjectData(project: IntraProjectUser) {
        projectName.text = project.project?.name ?: ""
        projectMark.text = project.finalMark.toString()
    }
}