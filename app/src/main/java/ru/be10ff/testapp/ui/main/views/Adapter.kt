package ru.be10ff.testapp.ui.main.views

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.be10ff.testapp.R
import ru.be10ff.testapp.domain.model.Item

class Adapter(val listener: (Bitmap) -> Unit) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    val data = mutableListOf<Item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)

        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(data[position]){
            holder.textView.text = source
            img?.let{ bitmap ->
                holder.imageView.setImageBitmap(bitmap)
                holder.imageView.setOnClickListener {
                    listener(bitmap)
                }
            } ?: run{
                holder.imageView.setImageBitmap(null)
                holder.imageView.setOnClickListener(null)
            }
        }

    }

    override fun getItemCount() = data.size

    fun dismiss(position :Int) : Item? {
        return if(position in 0..data.size-1) {
            val deletedItem = data[position]
            data.removeAt(position)
            notifyItemRemoved(position)
            deletedItem
        } else null
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            textView = view.findViewById(R.id.tvSource)
            imageView = view.findViewById(R.id.imageView)
        }
    }


}