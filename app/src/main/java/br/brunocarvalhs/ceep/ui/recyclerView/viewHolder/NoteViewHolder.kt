package br.brunocarvalhs.ceep.ui.recyclerView.viewHolder

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.brunocarvalhs.ceep.databinding.ItemNoteBinding
import br.brunocarvalhs.ceep.ui.recyclerView.interfaces.OnItemClickListener

class NoteViewHolder(
    view: ItemNoteBinding,
    onItemClickListener: OnItemClickListener?
) : RecyclerView.ViewHolder(view.root) {
    val title: TextView = view.itemNoteCardTitle
    val describe: TextView = view.itemNoteCardDescribe
    init {
        view.root.setOnClickListener { onItemClickListener?.onItemClick() }
    }
}