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
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
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
        val realmUser =
                mIntraApiServer
                        .realm
                        .where(IntraUser::class.java)
                        .equalTo("login", nameField.text.toString())
                        .findFirst()
        if (realmUser != null && realmUser.isValid) {
            val userData = mIntraApiServer
                    .realm
                    .copyFromRealm(realmUser)
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
                                mIntraApiServer.realm.executeTransactionAsync(
                                        { realm ->
                                            try {
                                                realm.copyToRealmOrUpdate(userData)
                                            } catch (e: java.io.IOException) {
                                                Log.e("IntraRealm", "Error when writing userData to realm: %s", e)
                                            }
                                        })
                                userCard.setUserData(userData)
                                loadProjects(userData)
                            })
        }
    }

    fun getProjectsFromCaches(intraUser: IntraUser): Pair<List<IntraProject>, List<Int>> {
        val realmProjects = mIntraApiServer
                .realm
                .where(IntraProject::class.java)
                .`in`("id", intraUser.projectsUsers?.map { project -> project?.project?.id }?.toTypedArray())
                .findAllAsync()

        val cachedProjects = when (realmProjects != null && realmProjects.isValid) {
            true ->
                mIntraApiServer
                        .realm
                        .copyFromRealm(realmProjects)
                        ?.filter { projectUser -> projectUser?.tier != null } ?: listOf()
            false -> listOf()
        }

        val neededProjects =
                intraUser
                        .projectsUsers
                        ?.filter {
                            projectUser ->
                            projectUser?.project?.id !in cachedProjects.map { cached -> cached.id }
                        }
                        ?.map { projectUser -> projectUser?.project?.id }
                        ?.filterNotNull() ?: listOf()
        return Pair(cachedProjects, neededProjects)
    }

    fun loadProjects(intraUser: IntraUser) {
        getProjectsFromCaches(intraUser)

        var b = object : Subscriber<List<IntraProject>> {
            override fun onNext(intraProject: List<IntraProject>) {
                mIntraApiServer.realm.executeTransactionAsync(
                        { realm ->
                            try {
                                realm.copyToRealmOrUpdate(intraProject)
                            } catch (e: java.io.IOException) {
                                Log.e("IntraRealm", "Error when writing projects to realm: %s", e)
                            }
                        })
            }

            override fun onComplete() {}

            override fun onError(t: Throwable?) {
                Log.w("retrofit", t?.message)
            }

            override fun onSubscribe(s: Subscription?) {}
        }

        val a = intraUser.projectsUsers
                ?.filterNotNull()
                ?.map { projectUser -> getProjectAsFlowable(projectUser) }

        Flowable.zip(a, { a -> a })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = { throwable ->
                            Log.w("retrofit", throwable)
                            userCard.handleInvalidUser()
                        },
                        onNext = {
                            projects ->
                            mIntraApiServer.realm.executeTransactionAsync(
                                    { realm ->
                                        try {
                                            realm.copyToRealmOrUpdate(projects.toList() as List<IntraProject>)
                                        } catch (e: java.io.IOException) {
                                            Log.e("IntraRealm", "Error when writing projects to realm: %s", e)
                                        }
                                    })
                        })
//                .subscribe(b)

    }

    fun getProjectAsFlowable(intraProjectUser: IntraProjectUser): Flowable<IntraProject> {
        val project = mIntraApiServer
                .realm
                .where(IntraProject::class.java)
                .equalTo("id", intraProjectUser.project?.id)
                .findFirst()
        if (project != null) {
            val projectData = mIntraApiServer
                    .realm
                    .copyFromRealm(
                            mIntraApiServer
                                    .realm
                                    .where(IntraProject::class.java)
                                    .equalTo("id", intraProjectUser.project?.id)
                                    .findFirst())

            if (projectData != null) {
                Log.i("Loading Cached Project", projectData.name)
                return Flowable.fromArray(projectData)
            }
        }
        return mIntraApiServer
                .apiServer
                .getProject(intraProjectUser.project?.id ?: -1)
    }
}

