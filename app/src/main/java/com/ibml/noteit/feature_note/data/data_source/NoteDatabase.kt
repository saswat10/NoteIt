package com.ibml.noteit.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibml.noteit.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract  val noteDao: NoteDao

    companion object{
        const val DATABASE_NAME ="notes_db"
    }
}


/*
* The repository directly accesses our data sources
* Job - to take these data sources which one to actually forward to use case
* for example if you have a api along with the database then you would
* have the caching layer
*
* The use case should not know where to get this data from
* They just need to get the data
* */