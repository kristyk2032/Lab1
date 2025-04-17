package com.example.lab1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

class AddNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val editTextNoteTitle:EditText = findViewById(R.id.editTextNoteTitle)
        val editTextNoteContent:EditText = findViewById(R.id.editTextNoteContent)
        val buttonSaveNote:Button = findViewById(R.id.buttonSaveNote)

        buttonSaveNote.setOnClickListener {
            val newNoteTitle = editTextNoteTitle.text.toString()
            val newNoteContent = editTextNoteContent.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("new_note_title", newNoteTitle)
            resultIntent.putExtra("new_note_content", newNoteContent)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}