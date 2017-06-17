package com.fortytwo.matthurd.kotlinpiscine.intra

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fortytwo.matthurd.kotlinpiscine.R
import com.fortytwo.matthurd.kotlinpiscine.intra.api.models.IntraProjectUser

class IntraProjectUserRecyclerViewAdapter(val mDataset: List<IntraProjectUser>)
        : RecyclerView.Adapter<IntraProjectUserRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val v = LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_intra_project_user_card, parent, false) as IntraProjectUserCard
                val vh = ViewHolder(v)
                return vh
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                (holder.itemView as IntraProjectUserCard).setProjectData(mDataset[position])
        }

        override fun getItemCount(): Int {
                return mDataset.size
        }

        class ViewHolder(itemView: IntraProjectUserCard) : RecyclerView.ViewHolder(itemView) {}
    }