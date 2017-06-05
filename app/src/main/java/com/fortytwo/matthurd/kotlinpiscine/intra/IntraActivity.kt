package com.fortytwo.matthurd.kotlinpiscine.intra

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.fortytwo.matthurd.kotlinpiscine.PiscineApplication
import com.fortytwo.matthurd.kotlinpiscine.R
import com.fortytwo.matthurd.kotlinpiscine.intra.api.IntraApiServer
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraProject
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraProjectUser
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IntraActivity : AppCompatActivity() {

    @Inject lateinit var mIntraApiServer: IntraApiServer

    @BindView(R.id.search_intra_login) lateinit var nameField: EditText
    @BindView(R.id.search_button) lateinit var searchButton: Button
    @BindView(R.id.user_card) lateinit var userCard: IntraUserCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intra)
        ButterKnife.bind(this)
        (application as PiscineApplication).piscineComponent.inject(this)
    }

    @OnClick(R.id.search_button)
    fun searchForUser() {
        val userData = mIntraApiServer
                .realm
                .where(IntraUser::class.java)
                .equalTo("login", nameField.text.toString())
                .findFirst()

        if (userData != null) {
            loadProjects(userData)
            userCard.setUserData(userData)
        } else {
            mIntraApiServer
                    .apiServer
                    .getUser(nameField.text.toString())
                    .doOnError { throwable -> Log.w("retrofit", throwable.message) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onError = { throwable ->
                                Log.w("retrofit", throwable)
                                userCard.handleInvalidUser()
                            },
                            onNext = {
                                userData ->
                                mIntraApiServer.realm.beginTransaction()
                                mIntraApiServer.realm.copyToRealmOrUpdate(userData)
                                mIntraApiServer.realm.commitTransaction()
                                userCard.setUserData(userData)
                                loadProjects(userData)
                            })
        }
    }

    fun loadProjects(intraUser: IntraUser) {
        intraUser.projectsUsers
                ?.filterNotNull()
                ?.forEach { projectUser -> loadProject(projectUser) }
    }

    fun loadProject(intraProjectUser: IntraProjectUser)
    {
        if (intraProjectUser.project == null)
            return
        val projectData = mIntraApiServer
                .realm
                .where(IntraProject::class.java)
                .equalTo("id", intraProjectUser.project?.id )
                .findFirst()

        if (projectData != null && projectData.tier != null) {
            Log.i("ayy cache", projectData.name)
            return //saved
        } else {
            mIntraApiServer
                    .apiServer
                    .getProject(intraProjectUser.project?.id ?: -1)
                    .doOnError { throwable -> Log.w("retrofit", throwable.message) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onError = { throwable ->
                                Log.w("retrofit", throwable)
                                userCard.handleInvalidUser()
                            },
                            onNext = {
                                projectData ->
                                mIntraApiServer.realm.beginTransaction()
                                mIntraApiServer.realm.copyToRealmOrUpdate(projectData.first())
                                mIntraApiServer.realm.commitTransaction()
                                Log.i("ayy", projectData.first().name) //we loaded it
                            })
        }


    }
}
