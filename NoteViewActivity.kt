package com.example.lab1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.content.Intent


class NoteViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_view)

        val editTextNoteTitle:EditText = findViewById(R.id.editTextNoteTitle)
        val editTextNoteContent:EditText = findViewById(R.id.editTextNoteContent)
        val buttonBack:Button = findViewById(R.id.buttonBack)
        val buttonSave:Button = findViewById(R.id.buttonSave)
        val buttonDelete:Button = findViewById(R.id.buttonDelete)
        var notePosition: Int = -1

        val noteTitle = intent.getStringExtra("noteTitle")
        val noteContent = intent.getStringExtra("noteContent")
        notePosition = intent.getIntExtra("position", -1)

        editTextNoteTitle.setText(noteTitle)
        editTextNoteContent.setText(noteContent)

        buttonBack.setOnClickListener {
            finish()
        }

        buttonSave.setOnClickListener {
            val editedNoteTitle = editTextNoteTitle.text.toString()
            val editedNoteContent = editTextNoteContent.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("edited_note_title", editedNoteTitle)
            resultIntent.putExtra("edited_note_content", editedNoteContent)
            resultIntent.putExtra("position", notePosition)

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        buttonDelete.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("position", notePosition)
            setResult(RESULT_FIRST_USER, resultIntent) 
            finish()
        }
    }
}