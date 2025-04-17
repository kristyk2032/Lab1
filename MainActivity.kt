package com.example.lab1

import android.os.Bundle
import android.app.Activity
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.AdapterView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var listAdapter: NoteAdapter
    private val notes = mutableListOf<Note>()

    private val addNoteLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val newNoteTitle = data?.getStringExtra("new_note_title")
                val newNoteContent = data?.getStringExtra("new_note_content")
                if (!newNoteTitle.isNullOrEmpty() && !newNoteContent.isNullOrEmpty()) {
                    val newNote = Note(newNoteTitle, newNoteContent)
                    notes.add(newNote)
                    listAdapter.notifyDataSetChanged()
                }
            }
        }

    private val editNoteLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val editedNoteTitle = data?.getStringExtra("edited_note_title")
                val editedNoteContent = data?.getStringExtra("edited_note_content")
                val position = data?.getIntExtra("position", -1) ?: -1

                if (!editedNoteTitle.isNullOrEmpty() && !editedNoteContent.isNullOrEmpty() && position != -1) {
                    val editedNote = Note(editedNoteTitle, editedNoteContent)
                    notes[position] = editedNote
                    listAdapter.notifyDataSetChanged()
                }
            } else if (result.resultCode == Activity.RESULT_FIRST_USER) {
                val data = result.data
                val position = data?.getIntExtra("position", -1) ?: -1
                if (position != -1) {
                    notes.removeAt(position)
                    listAdapter.notifyDataSetChanged()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listViewNotes)
        listAdapter = NoteAdapter(this, notes)
        listView.adapter = listAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _,position, _ ->
            val selectedNote = notes[position]

            val intent = Intent(this, NoteViewActivity::class.java)
            intent.putExtra("noteTitle", selectedNote.title)
            intent.putExtra("noteContent", selectedNote.content)
            intent.putExtra("position", position)
            editNoteLauncher.launch(intent)
        }

        val buttonAddNote: Button = findViewById(R.id.buttonAddNote)
        buttonAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            addNoteLauncher.launch(intent)
        }
    }


}