package com.ibml.noteit.feature_note.presentation.notes

import com.ibml.noteit.feature_note.domain.model.Note
import com.ibml.noteit.feature_note.domain.util.NoteOrder

/*
*
* data class within a sealed class is for events that carry data.
* object within a sealed class is for simple, data-less event signals.
* */

sealed class NotesEvent {
    data class NotesOrder(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection: NotesEvent()
}

