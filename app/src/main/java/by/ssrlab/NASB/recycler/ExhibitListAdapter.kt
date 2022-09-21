package by.krokam.biarescie.ui.recycler

import android.view.ViewGroup
import android.widget.TextView

import by.krokam.biarescie.ui.recycler.viewholders.BaseViewHolder
import by.krokam.biarescie.ui.recycler.viewholders.ExhibitViewHolder
import by.ssrlab.NASB.R
import by.ssrlab.NASB.data.items.Exhibit

class ExhibitListAdapter : BaseAdapter<Exhibit>(){
    var name = ""

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) 0 else 1
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Exhibit> {
        return if (viewType == 0) {
            object : BaseViewHolder<Exhibit>(parent, R.layout.panel_title) {}
        } else ExhibitViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Exhibit>, position: Int) {
        if(position > 0) {
            super.onBindViewHolder(holder, position - 1)
        }else{
            holder.itemView.findViewById<TextView>(R.id.tvName).text = name
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }
}