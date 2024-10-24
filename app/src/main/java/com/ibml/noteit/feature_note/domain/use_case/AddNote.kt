package com.ibml.noteit.feature_note.domain.use_case

import com.ibml.noteit.feature_note.domain.model.InvalidNoteException
import com.ibml.noteit.feature_note.domain.model.Note
import com.ibml.noteit.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isEmpty()){
            throw InvalidNoteException("Title cannot be empty")
        }
        if(note.content.isEmpty()){
            throw InvalidNoteException("Content cannot be empty")
        }

        repository.insertNote(note)
    }
}