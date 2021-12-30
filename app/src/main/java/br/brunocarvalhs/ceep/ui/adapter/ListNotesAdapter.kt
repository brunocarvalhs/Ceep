package br.brunocarvalhs.ceep.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.brunocarvalhs.ceep.R
import br.brunocarvalhs.ceep.databinding.ItemNoteBinding
import br.brunocarvalhs.ceep.model.Note

class ListNotesAdapter(private val notes: ArrayList<Note>) :
    RecyclerView.Adapter<ListNotesAdapter.ViewHolder>() {

    private lateinit var viewBinding: ItemNoteBinding

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = ItemNoteBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = notes[position].title
        viewHolder.describe.text = notes[position].describe
    }

    class ViewHolder(view: ItemNoteBinding) : RecyclerView.ViewHolder(view.root) {
        val title: TextView = view.itemNoteCardTitle
        val describe: TextView = view.itemNoteCardDescribe
    }

    fun add(note: Note) {
        notes.add(note)
        notifyDataSetChanged()
    }
}