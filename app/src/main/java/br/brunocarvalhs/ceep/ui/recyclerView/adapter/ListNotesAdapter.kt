package br.brunocarvalhs.ceep.ui.recyclerView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.brunocarvalhs.ceep.databinding.ItemNoteBinding
import br.brunocarvalhs.ceep.model.Note
import br.brunocarvalhs.ceep.ui.recyclerView.interfaces.OnItemClickListener
import br.brunocarvalhs.ceep.ui.recyclerView.viewHolder.NoteViewHolder

class ListNotesAdapter(private val notes: ArrayList<Note>) :
    RecyclerView.Adapter<NoteViewHolder>() {

    private lateinit var viewBinding: ItemNoteBinding
    private var onItemClickListener: OnItemClickListener? = null

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NoteViewHolder {
        viewBinding = ItemNoteBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return NoteViewHolder(viewBinding, onItemClickListener)
    }

    override fun onBindViewHolder(viewHolder: NoteViewHolder, position: Int) {
        viewHolder.title.text = notes[position].title
        viewHolder.describe.text = notes[position].describe
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    fun add(note: Note) {
        notes.add(note)
        notifyDataSetChanged()
    }
}