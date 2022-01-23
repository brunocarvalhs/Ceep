package br.brunocarvalhs.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.brunocarvalhs.ceep.databinding.ActivityListNoteBinding
import br.brunocarvalhs.ceep.model.Note
import br.brunocarvalhs.ceep.ui.recyclerView.adapter.ListNotesAdapter
import br.brunocarvalhs.ceep.ui.recyclerView.interfaces.OnItemClickListener
import br.brunocarvalhs.ceep.viewModel.ListNoteViewModel

class ListNoteActivity : AppCompatActivity() {


    private lateinit var adapter: ListNotesAdapter
    private val list: ArrayList<Note> by lazy { notesExample() }
    private val viewBinding: ActivityListNoteBinding by lazy { ActivityListNoteBinding.inflate(layoutInflater) }
    private val viewModel: ListNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        this.setupFormNote()
        this.setupRecycleView(list)
    }

    private fun notesExample(): ArrayList<Note> {
        return viewModel.all()
    }

    private fun setupRecycleView(notes: ArrayList<Note>) {
        adapter = ListNotesAdapter(notes)
        viewBinding.listItem.adapter = adapter
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick() {
                Toast.makeText(
                    this@ListNoteActivity,
                    "viewHolder na Activity",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
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
        viewModel.add(note)
    }

    private fun isResultNote(requestCode: Int, resultCode: Int, data: Intent?): Boolean =
        requestCode == RESULT_CODE_READ_NOTE &&
                resultCode == FormNoteActivity.RESULT_CODE_CREATE_NOTE &&
                data?.hasExtra(FormNoteActivity.ARG_NOTE) == true

    companion object {
        const val RESULT_CODE_READ_NOTE = 1
    }
}