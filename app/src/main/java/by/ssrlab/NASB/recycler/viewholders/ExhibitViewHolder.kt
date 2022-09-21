package by.krokam.biarescie.ui.recycler.viewholders


import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import by.ssrlab.NASB.R
import by.ssrlab.NASB.data.items.Exhibit
import com.bumptech.glide.Glide


class ExhibitViewHolder(parent : ViewGroup) : BaseViewHolder<Exhibit>(parent, R.layout.item_exhibit){

    override fun bind(item: Exhibit) {
        super.bind(item)
        itemView.run {

            itemView.findViewById<TextView>(R.id.tvName).text = item.name
            Glide.with(context).load(item.logo).into(itemView.findViewById<ImageView>(R.id.photo))
        }
    }
}