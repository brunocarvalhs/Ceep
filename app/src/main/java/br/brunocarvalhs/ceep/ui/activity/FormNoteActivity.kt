package br.brunocarvalhs.ceep.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.brunocarvalhs.ceep.R
import br.brunocarvalhs.ceep.databinding.ActivityFormNoteBinding
import br.brunocarvalhs.ceep.model.Note

class FormNoteActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityFormNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFormNoteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form_note_save, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        validationMenuOptions(item)
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun validationMenuOptions(item: MenuItem) {
        when (item.itemId) {
            R.id.menu_form_note_ic_save -> this.returnForm()
        }
    }

    private fun returnForm() {
        val note = createNote()
        val intent = Intent()
        intent.putExtra(ARG_NOTE, note)
        setResult(RESULT_CODE_CREATE_NOTE, intent)
    }

    private fun createNote(): Note = Note(
        title = viewBinding.formNoteTitle.text.toString(),
        describe = viewBinding.formNoteDescribe.text.toString()
    )

    companion object {
        const val ARG_NOTE = "nota"
        const val RESULT_CODE_CREATE_NOTE = 2
    }
}