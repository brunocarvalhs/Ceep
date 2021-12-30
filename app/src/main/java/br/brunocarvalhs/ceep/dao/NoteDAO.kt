package br.brunocarvalhs.ceep.dao

import br.brunocarvalhs.ceep.model.Note
import java.util.*
import kotlin.collections.ArrayList

class NoteDAO {

    companion object {
        private val notes: ArrayList<Note> = ArrayList()
    }

    fun all(): ArrayList<Note> = notes.clone() as ArrayList<Note>

    fun add(vararg notes: Note) {
        NoteDAO.notes.addAll(notes.asList())
    }

    fun update(index: Int, nota: Note) {
        notes[index] = nota
    }

    fun remove(index: Int) {
        notes.removeAt(index)
    }

    fun replace(start: Int, end: Int) {
        Collections.swap(notes, start, end)
    }

    fun del() {
        notes.clear()
    }
}