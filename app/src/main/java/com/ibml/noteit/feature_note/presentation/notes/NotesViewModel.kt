package com.ibml.noteit.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibml.noteit.feature_note.domain.model.Note
import com.ibml.noteit.feature_note.domain.use_case.NoteUseCases
import com.ibml.noteit.feature_note.domain.util.NoteOrder
import com.ibml.noteit.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {


    /*
    state: The read-only state object that is exposed to composable and
           triggers recomposition when its value changes.

    _state: The private mutable state holder that is used to modify the state value,
            but does not directly trigger recomposition.
     */

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeleteNote: Note? = null


    private var getNotesJob: Job? = null

    // we're calling it in init block
    // because initially we may require some notes at start
    init{
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.NotesOrder -> {

                // here if we omit the ::class then it would check the referential equality
                // that would never be equal
                // other method would be to make the every object and class in the sealed class NoteOrder
                // to be data class, but that adds more to the complexity
                if (state.value.noteOrder::class == event.noteOrder::class
                    && state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeleteNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeleteNote ?: return@launch)
                    recentlyDeleteNote = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()


        // every time we call this function
        // we call a new instance
        // hence we need to cancel the previous instance
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach{notes->
                _state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}