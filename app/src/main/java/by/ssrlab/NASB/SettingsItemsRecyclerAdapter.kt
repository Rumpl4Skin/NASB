package by.ssrlab.NASB

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SettingsItemsRecyclerAdapter(private val names: List<String>, private val img: List<Int>, onClickListener: OnStateClickListener) : RecyclerView
    .Adapter<SettingsItemsRecyclerAdapter.MyViewHolder>() {

        var onClickListener : OnStateClickListener=onClickListener

        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var settingsName: TextView = itemView.findViewById(R.id.txt_settings)
            var settingsImg: ImageView = itemView.findViewById(R.id.img_settings)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.settings_item, parent, false)
            return MyViewHolder(itemView)
        }

        interface OnStateClickListener {
            fun onStateClick(game: String?, position: Int)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.settingsName.text = names[position]
            holder.settingsImg.setImageResource(img[position])
            holder.itemView.setOnClickListener {
                onClickListener.onStateClick(names[position], position)
            }
        }

        override fun getItemCount() = names.size

    }
