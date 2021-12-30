package br.brunocarvalhs.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.brunocarvalhs.ceep.dao.NoteDAO
import br.brunocarvalhs.ceep.databinding.ActivityListNoteBinding
import br.brunocarvalhs.ceep.model.Note
import br.brunocarvalhs.ceep.ui.adapter.ListNotesAdapter

class ListNoteActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityListNoteBinding
    private lateinit var adapter: ListNotesAdapter
    private var list: ArrayList<Note> = notesExample()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityListNoteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        this.setupFormNote()
        this.setupRecycleView(list)
    }

    private fun notesExample(): ArrayList<Note> {
        val dao = NoteDAO()
        return dao.all()
    }

    private fun setupRecycleView(notes: ArrayList<Note>) {
        adapter = ListNotesAdapter(notes)
        viewBinding.listItem.adapter = adapter
        setupLinearLayoutManager()
    }

    private fun setupLinearLayoutManager() {
        viewBinding.listItem.layoutManager = LinearLayoutManager(this)
    }

    private fun setupFormNote() {
        viewBinding.listNoteAdd.setOnClickListener {
            val initFormNote = Intent(this, FormNoteActivity::class.java)
            startActivityForResult(initFormNote, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (isResultNote(requestCode, resultCode, data)) data?.let { saveNote(it) }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun saveNote(data: Intent) {
        val note = data.getSerializableExtra(FormNoteActivity.ARG_NOTE) as Note
        adapter.add(note)
        NoteDAO().add(note)
    }

    private fun isResultNote(requestCode: Int, resultCode: Int, data: Intent?) : Boolean =
        requestCode == RESULT_CODE_READ_NOTE &&
        resultCode == FormNoteActivity.RESULT_CODE_CREATE_NOTE &&
        data?.hasExtra(FormNoteActivity.ARG_NOTE) == true

    companion object {
        const val RESULT_CODE_READ_NOTE = 1
    }
}