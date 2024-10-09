package com.ibml.noteit.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import com.ibml.noteit.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel(){

}