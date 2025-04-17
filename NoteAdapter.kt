package com.example.lab1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class NoteAdapter  (context: Context, notes: List<Note>) :
    ArrayAdapter<Note>(context, R.layout.list_item_note, notes) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_note, parent, false)

        val note = getItem(position)!!

        val textViewNoteTitle: TextView = itemView.findViewById(R.id.textViewNoteTitle)
        val textViewNoteContent: TextView = itemView.findViewById(R.id.textViewNoteContent)

        textViewNoteTitle.text = note.title
        textViewNoteContent.text = note.content

        return itemView
    }
}