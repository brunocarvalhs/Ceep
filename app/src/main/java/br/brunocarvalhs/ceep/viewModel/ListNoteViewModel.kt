package br.brunocarvalhs.ceep.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.brunocarvalhs.ceep.model.Note
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ListNoteViewModel : ViewModel() {

    private var notes: MutableLiveData<ArrayList<Note>> = MutableLiveData()

    fun all(): ArrayList<Note> {
        val result: ArrayList<Note> = ArrayList()
        notes.value?.forEach(result::add)
        return result
    }

    fun add(vararg value: Note) {
        notes.postValue("")
    }

    fun update(index: Int, nota: Note) {
        viewModelScope.launch {
            notes.value?.set(index, nota)
        }
    }

    fun remove(index: Int) {
        viewModelScope.launch {
            notes.value?.removeAt(index)
        }
    }

    fun replace(start: Int, end: Int) {
        viewModelScope.launch { Collections.swap(notes.value, start, end) }
    }

    fun del() {
        viewModelScope.launch { notes.value?.clear() }
    }

}