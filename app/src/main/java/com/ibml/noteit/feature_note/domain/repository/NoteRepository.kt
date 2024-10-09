package com.ibml.noteit.feature_note.domain.repository

import com.ibml.noteit.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


/*
*
* The main goal of the defining interface is that we can create
* fake versions of this interface for testing purposes
*
*/

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun  getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}
