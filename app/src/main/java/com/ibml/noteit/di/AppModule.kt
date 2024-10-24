package com.ibml.noteit.di

import android.app.Application
import androidx.room.Room
import com.ibml.noteit.feature_note.data.data_source.NoteDatabase
import com.ibml.noteit.feature_note.data.data_source.NoteRepositoryImpl
import com.ibml.noteit.feature_note.domain.repository.NoteRepository
import com.ibml.noteit.feature_note.domain.use_case.AddNote
import com.ibml.noteit.feature_note.domain.use_case.DeleteNote
import com.ibml.noteit.feature_note.domain.use_case.GetNotes
import com.ibml.noteit.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @Singleton
    @Provides
    fun providesNoteDatabase(app: Application):NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNotesUseCases(repository: NoteRepository): NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository)
        )

    }

}