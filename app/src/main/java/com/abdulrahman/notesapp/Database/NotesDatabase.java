package com.abdulrahman.notesapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abdulrahman.notesapp.Dao.NotesDao;
import com.abdulrahman.notesapp.Model.Notes;

@Database(entities = {Notes.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

        public abstract NotesDao notesDao();

        private static volatile NotesDatabase INSTANCE;
        public static NotesDatabase getDataBaseInstance(Context context){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                                , NotesDatabase.class,"Notes_DataBase").allowMainThreadQueries().build();
            }
            return INSTANCE;
        }
}
