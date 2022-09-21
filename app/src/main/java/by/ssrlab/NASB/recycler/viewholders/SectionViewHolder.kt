package by.krokam.biarescie.ui.recycler.viewholders

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import by.ssrlab.NASB.R
import by.ssrlab.NASB.data.items.Section
import com.bumptech.glide.Glide

class SectionViewHolder(parent: ViewGroup) : BaseViewHolder<Section>(parent, R.layout.item_section) {

    override fun bind(item: Section) {
        super.bind(item)
        itemView.apply {
            itemView.findViewById<TextView>(R.id.tvName).text = item.name
            Glide.with(context).load(item.logo).into( itemView.findViewById<ImageView>(R.id.photo))
        }
    }
}